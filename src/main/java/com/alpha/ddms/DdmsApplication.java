package com.alpha.ddms;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.configuration.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
//@ConfigurationPropertiesScan("com.alpha.ddms.configuration")
public class DdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdmsApplication.class, args);
		System.out.println(Utils.generateLatestId("0"));
		System.out.println(Utils.generateLatestId("1"));
		System.out.println(Utils.generateLatestId("12"));
		System.out.println(Utils.generateLatestId("123"));

		System.out.println(ConfigProperties.getConstantMaxLimit());

	}

}
