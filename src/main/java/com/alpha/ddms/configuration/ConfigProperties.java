package com.alpha.ddms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class ConfigProperties {

    @Autowired
    Environment env;

    private static Integer constant_max_limit;

    public static Integer getConstant_MAX_LIMIT() {
        return Constant_MAX_LIMIT;
    }

    public static void setConstant_MAX_LIMIT(Integer constant_MAX_LIMIT) {
        Constant_MAX_LIMIT = constant_MAX_LIMIT;
    }

    private static Integer Constant_MAX_LIMIT;

    public static void setConstant_max_limit(Integer constant_max_limit) {
        ConfigProperties.constant_max_limit = constant_max_limit;
    }

    public static Integer getConstant_max_limit() {
        return constant_max_limit;
    }

    @Bean
    public void ConfigProperties(){
        Integer testBean = env.getProperty("constant_max_limit",Integer.class);
        this.setConstant_max_limit(testBean);
        Integer testBean2 = env.getProperty("Constant_MAX_LIMIT", Integer.class);
        this.setConstant_MAX_LIMIT(testBean2);
    }
}