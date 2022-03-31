package com.alpha.ddms;

import com.alpha.ddms.configuration.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class DdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdmsApplication.class, args);

		System.out.println(ConfigProperties.getConstant_max_limit());
	}

}
