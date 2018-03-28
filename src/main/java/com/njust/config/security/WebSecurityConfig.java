package com.njust.config.security;

import com.njust.config.security.permission.CustomAccessDecisionManager;
import com.njust.config.security.permission.CustomFilterSecurityInterceptor;
import com.njust.config.security.permission.CustomSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.web.cors.CorsUtils;

import javax.sql.DataSource;

/**
 * Security配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecuritySettings.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecuritySettings settings;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;   //是在application-dev.yml

    /**
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
        //记住我
        auth.eraseCredentials(false);
    }

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(settings.getStaticresources().split(","));
    }

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //指定登录页是"/login"
                .formLogin().loginPage(settings.getLogin_page()).loginProcessingUrl(settings.getLogin()).usernameParameter("username").passwordParameter("password")
                .permitAll().successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailHandler)
                .and().authorizeRequests()
                //允许所有用户访问的url
                .antMatchers(settings.getPermitall().split(",")).permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                //退出登录后的默认url是"/"
                .and().logout().logoutSuccessUrl(settings.getLogoutsuccssurl()).permitAll()
                //拒绝连接
                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                //记住我功能
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(Integer.parseInt(settings.getRememberme()))
                .and().csrf().disable();
    }

    //加密算法
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTokenRepositoryImpl persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public CustomFilterSecurityInterceptor customFilter() throws Exception{
        CustomFilterSecurityInterceptor customFilter = new CustomFilterSecurityInterceptor();
        customFilter.setSecurityMetadataSource(securityMetadataSource());
        customFilter.setAccessDecisionManager(accessDecisionManager());
        customFilter.setAuthenticationManager(authenticationManager);
        return customFilter;
    }

    @Bean
    public CustomAccessDecisionManager accessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    @Bean
    public CustomSecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource(settings.getUrlroles());
    }
}
