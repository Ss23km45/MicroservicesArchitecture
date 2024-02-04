package com.codingninja.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
public class CloudGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGateWayApplication.class, args);
	}
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
		return factor -> factor.configureDefault(id -> new Resilience4JConfigBuilder(id).circuitBreakerConfig
			(CircuitBreakerConfig.ofDefaults()).build()
		);
		
	}

}
