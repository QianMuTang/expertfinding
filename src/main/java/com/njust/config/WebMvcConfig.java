package com.njust.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.map-path}")
    private String mapPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有mapPath 访问都映射到classpath:uploadPath 目录下
        registry.addResourceHandler(mapPath).addResourceLocations("file:"+uploadPath);
        super.addResourceHandlers(registry);
    }
}
