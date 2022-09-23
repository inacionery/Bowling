package com.bowling;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BowlingApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(
				BowlingApplication.class);

		springApplicationBuilder.bannerMode(Mode.OFF);
		springApplicationBuilder.logStartupInfo(false);

		springApplicationBuilder.run(args);
	}
}
