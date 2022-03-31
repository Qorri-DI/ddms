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
    }
}