package com.lam.StudentManagement3.config;

import com.lam.StudentManagement3.interceptor.LoginInterceptor;
import com.lam.StudentManagement3.interceptor.RequiredLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/student/{id}");
        registry.addInterceptor(new RequiredLoginInterceptor()).addPathPatterns("/*").excludePathPatterns("" +
                "/login").excludePathPatterns("/student/{id}");
    }

}
