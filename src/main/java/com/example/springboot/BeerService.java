package com.example.springboot;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BeerService {

    private MeterRegistry meterRegistry;
    private Counter lightOrderCounter;
    private Counter aleOrderCounter;
    private List<Order> orders = new ArrayList<>();

    public BeerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initOrderCounters();
        Gauge.builder("beer.ordersInQueue", orders, Collection::size)
                .description("Number of unserved orders")
                .register(meterRegistry);
    }

    private void initOrderCounters() {
        lightOrderCounter = this.meterRegistry.counter("beer.orders", "type", "light"); // 1 - create a counter
        aleOrderCounter = Counter.builder("beer.orders")    // 2 - create a counter using the fluent API
                .tag("type", "ale")
                .description("The number of orders ever placed for Ale beers")
                .register(meterRegistry);
    }

    @Timed(description = "Time spent serving orders beers")
    @SneakyThrows
    void orderBeer(Order order) {
        orders.add(order);
        Thread.sleep((long) (1000L * order.getAmount()));

        if ("light".equals(order.getType())) {
            lightOrderCounter.increment(1.0);  // 3 - increment the counter
        } else if ("ale".equals(order.getType())) {
            aleOrderCounter.increment();
        }
    }

}