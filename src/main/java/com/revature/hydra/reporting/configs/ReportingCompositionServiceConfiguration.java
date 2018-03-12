package com.revature.hydra.reporting.configs;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableEurekaClient
@EnableSwagger2
public class ReportingCompositionServiceConfiguration {
	
	@Primary
	@Bean
	AmqpTemplate rabbit(ConnectionFactory factory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		return rabbitTemplate;
	}

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.revature.hydra.reporting.controller"))              
          .paths(PathSelectors.any())                          
          .build();
    }
}
