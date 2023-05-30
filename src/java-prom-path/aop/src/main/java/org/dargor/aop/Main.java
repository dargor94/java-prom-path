package org.dargor.aop;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        int val = 4;
        /*
        Error
         */
        //int val = 0;
        WebClient.builder().build().get().uri("localhost:8080/" + val).exchangeToMono(clientResponse -> clientResponse.bodyToMono(List.class).and(System.out::println)).block();

    }
}
