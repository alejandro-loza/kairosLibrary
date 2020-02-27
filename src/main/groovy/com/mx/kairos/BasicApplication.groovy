package com.mx.kairos

import io.jaegertracing.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BasicApplication {
    @Value('${spring.application.name}')
    private String applicationName

    @Bean
    io.opentracing.Tracer tracer() {
        if(System.getenv(Configuration.JAEGER_SERVICE_NAME) == null) {
            return new Configuration(applicationName).getTracer()
        }
        Configuration.fromEnv().getTracer()
    }


	static void main(String[] args) {
		SpringApplication.run(BasicApplication, args)
	}

}
