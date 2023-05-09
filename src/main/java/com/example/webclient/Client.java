package com.example.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Scanner;

@Configuration
class Client implements CommandLineRunner {
    WebClient client = WebClient.create("http://localhost:8080");
    @Autowired
    ClientService clientService;
    Scanner scanner = new Scanner(System.in);
    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Hello! Choose what you want:");
            int meny = scanner.nextInt();
            switch (meny) {
                case 1 -> System.out.println("Producter" + clientService.getAllProducts().collectList().block());
                case 2 -> System.out.println(clientService.getProductByIdMenyItem());
                case 3-> System.out.println(clientService.getProductBycategoryMenu());
                case 4-> System.out.println(clientService.deleteProduct());
                case 5-> System.out.println(clientService.addProduct());
                case 6-> System.out.println(clientService.changePriceOfProduct());
                case 7-> System.exit(0);

            }


        }

    }

}
