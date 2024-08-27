package com.Suresh.SpringBoot_MicroService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class KafkaBindingProcessor {
   // Fundamental functional interfaces
    //Function ->processor
    //supplier ->producer
    //consumer ->consumer

    Random random =new Random();

    @Bean
    public Supplier<String> customProducer() {
        System.out.println("Producer supplier called");
        Supplier<String> supplier =() -> {
            return "Hello kafka streams Springboot Integration:" +random.nextInt(100);
        };
        return supplier;
    }
    @Bean
    public Function<String,String>  customProcessor(){
        System.out.println("processor Function called");
        Function<String,String>  func = (String message)-> {
            return message.toUpperCase();
        };
        return func;
    }

    @Bean
    public Consumer<String> customConsumer(){
        System.out.println("consumer called");
        Consumer<String> consumer = (String message)->System.out.println("Consumed:"+message);
        return consumer;
        }
    }

