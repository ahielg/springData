package com.example.springboot;

import com.example.springboot.jpa.Book;
import com.example.springboot.jpa.BookService;
import com.example.springboot.jpa.DataConfiguration;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * http://localhost:8085/actuator/prometheus
 */
 public class DataApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfiguration.class)) {
            BookService service = context.getBean(BookService.class);
            Book book = new Book();
            book.setTitle("Test title");
            book.setPageCount(22);
         //   book.setPublishedDate(new Date());
            service.save(book);
        }
        //SpringApplication.run(DataApplication.class, args);
    }


    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}