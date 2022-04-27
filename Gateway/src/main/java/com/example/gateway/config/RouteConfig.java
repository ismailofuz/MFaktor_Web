package com.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final AuthFilter authFilter;


//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().route(firstServiceRouting()).build();
//    }
//
//    private final Function<PredicateSpec, Route.AsyncBuilder> firstServiceRouting() {
//        return r -> r.path("/api/**")
//                .filters(f -> f
//                        .removeRequestHeader("Authorization")
//                        .filter(authFilter.apply(new AuthFilterConfig()))
//                        .uri("lb://CATALOG");
//    }

}
