package com.alpha.ddms.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="constants")
public class ConfigProperties {

    private static int CONSTANT_MAX_LIMIT;

    public static int getConstantMaxLimit() {
        return CONSTANT_MAX_LIMIT;
    }

    public static void setConstantMaxLimit(int constantMaxLimit) {
        CONSTANT_MAX_LIMIT = constantMaxLimit;
    }
}
