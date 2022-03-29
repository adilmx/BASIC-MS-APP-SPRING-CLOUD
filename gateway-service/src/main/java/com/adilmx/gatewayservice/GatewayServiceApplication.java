package com.adilmx.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


    //if we don't want to use the yml file to configure routes we can use this builder
    //@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/clients/**").uri("lb://CLIENT-SERVICE"))
                .route(r -> r.path("/accounts/**").uri("lb://ACCOUNT-SERVICE"))
                .build();
    }

    //dynamic routes builder if we have a multiple MS to configure
    //now we should use our gateway url(http://ip-gateway:port-gateway/micro-service-name/path/**) as this example : http://localhost:8888/ACCOUNT-SERVICE/accounts
    //@Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

}
