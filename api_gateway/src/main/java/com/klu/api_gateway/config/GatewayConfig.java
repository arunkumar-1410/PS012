package com.klu.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()

                // Auth Service
                .route("auth-service", r -> r
                        .path("/auth-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082"))

                // Exam Service
                .route("exam-service", r -> r
                        .path("/exam_service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081"))

                // Submission Service
                .route("submission-service", r -> r
                        .path("/submission-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8083"))

                // Evaluation Service
                .route("evaluation-service", r -> r
                        .path("/evaluation-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8084"))

                .build();
    }
}