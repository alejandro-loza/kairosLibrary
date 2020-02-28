package com.mx.kairos

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KairosApplication {
    @Value('${spring.application.name}')
    private String applicationName

	static void main(String[] args) {
		SpringApplication.run(KairosApplication, args)
	}

}
