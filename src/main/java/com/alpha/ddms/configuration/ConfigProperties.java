package com.alpha.ddms.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="constants")
public class ConfigProperties {

    private static int constant_max_limit;

    public static int getConstant_max_limit() {
        return constant_max_limit;
    }
}
