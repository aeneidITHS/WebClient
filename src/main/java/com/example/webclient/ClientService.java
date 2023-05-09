package com.example.webclient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Scanner;

@Service
public class ClientService {
   private final WebClient client;
   Scanner sc = new Scanner(System.in);


    public ClientService(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder
                .baseUrl("http://localhost:8080")
                .build();
    }
    public String getProductByIdMenyItem(){
        System.out.println("Input id of Product:");
        int id = sc.nextInt();
        return  "The product you were searching for:" + getProductById(id).block();
    }
    public String getProductBycategoryMenu(){
        System.out.println("Input cateogry of Product:");
        String category = sc.next();
        return  "The product you were searching for:" + getProductByCategory(category).collectList().block();
    }
    public String deleteProduct(){
        System.out.println("Input id of Product:");
        int id = sc.nextInt();
        return  "The products left:" + deleteProductById(id).collectList().block();
    }
    public String addProduct(){
        System.out.println("Input name of Product:");
        String name = sc.next();
        System.out.println("Input price of Product:");
        Double price = (double)sc.nextInt();
        System.out.println("Input Category you want the product to be in:");
        String category = sc.next();
        return  "The product you added:" + addProductToDB(name,price,category).block();
    }
    public String changePriceOfProduct(){
        System.out.println("Id of product you want to change price on:");
        int id = sc.nextInt();
        System.out.println("The new price of product:");
        Double price = (double)sc.nextInt();
        return "The product you changed price on:" + changePrice(id,price).block();
    }
    public Flux<Product> getAllProducts(){
        return this.client
                .get()
                .uri("/rest/product/all")
                .retrieve()
                .bodyToFlux(Product.class);
    }
    private Mono<Product> getProductById(Integer id){
        return this.client
                .get()
                .uri("/rest/product/id/{id}",id)
                .retrieve()
                .bodyToMono(Product.class);
    }
    private Flux<Product> getProductByCategory(String category){
        return this.client
                .get()
                .uri("/rest/product/category/{category}",category)
                .retrieve()
                .bodyToFlux(Product.class);
    }
    private Flux<Product> deleteProductById(Integer id){
        return this.client
                .delete()
                .uri("/rest/delete/product/{id}",id)
                .retrieve()
                .bodyToFlux(Product.class);
    }
    private Mono<Product> addProductToDB(String name,Double price, String category){
        return this.client
                .post()
                .uri("/rest/add/product/{name}/{price}/{category}",name,price,category)
                .retrieve()
                .bodyToMono(Product.class);
    }
    private Mono<Product> changePrice(Integer id,Double price){
        return this.client
                .put()
                .uri("/rest/add/product/{id}/{price}")
                .retrieve()
                .bodyToMono(Product.class);
    }
}
