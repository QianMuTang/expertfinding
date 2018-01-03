package com.njust.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Security配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;

    @Autowired
    private DataSource dataSource;   //是在application.properites

    @Autowired
    private UserDetailsService myuserDetailsService;

    /**
     * 记住我功能的token存取器配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .loginPage("/login")            //指定登录页是"/login"
                .permitAll()               //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .rememberMe()
                    .rememberMeParameter("remember-me").userDetailsService(myuserDetailsService)
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(600)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()   //允许所有用户访问"/"
//                .antMatchers("/whoim").hasRole("ADMIN")//这就表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
//                .anyRequest().access("@rbacService.hasPermission(request,authentication)")//必须经过认证以后才能访问
                .anyRequest().authenticated()                  //其他地址的访问均需验证权限
                .and()
                .logout()
                .logoutSuccessUrl("/")  //退出登录后的默认url是"/"
                .permitAll()
                .and()
                .csrf().disable();
    }

    /**
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(provider);
    }

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/scripts/**", "/styles/**", "/images/**");
    }
}
