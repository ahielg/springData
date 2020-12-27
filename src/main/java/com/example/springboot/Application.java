package com.example.springboot;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;

import java.util.stream.LongStream;

/**
 * http://localhost:8085/actuator/prometheus
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

    private BeerService beerService;

    public Application(BeerService beerService) {
        this.beerService = beerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static Order toOrder(Long l) {
        double amount = l % 5;
        String type = l % 2 == 0 ? "ale" : "light";
        return new Order(amount, type);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void orderBeers() {
        LongStream.of(1, 100)
                //Flux.interval(Duration.ofSeconds(2))
                .mapToObj(Application::toOrder)
                .forEach(o -> beerService.orderBeer(o));
        //.subscribe();
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}