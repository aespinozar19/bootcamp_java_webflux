package com.bootcamp.java.pasivoahorro.controller;

import com.bootcamp.java.pasivoahorro.dto.ProductClientDTO;
import com.bootcamp.java.pasivoahorro.dto.ProductClientRequest;
import com.bootcamp.java.pasivoahorro.dto.webClientDTO.ProductResponseDTO;
import com.bootcamp.java.pasivoahorro.service.productClient.ProductClientService;
import com.bootcamp.java.pasivoahorro.service.webClients.WcProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/ProductClient")
public class productClientController {

    @Autowired
    private WcProductsService wcProductsService;

    @Autowired
    private ProductClientService productClientService;


    @PostMapping
    public Mono<ResponseEntity<ProductClientDTO>> create(@Valid @RequestBody ProductClientRequest request) {
        log.info("create executed {}", request);
        return productClientService.create(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/GetAllProducts")
    public Mono<ResponseEntity<Flux<ProductResponseDTO>>> getAllProducts(){
        log.info("getAllProducts executed");
        return Mono.just(ResponseEntity.ok()
                .body(wcProductsService.findAll()));
    }

    @GetMapping("/{idProduct}")
    public Mono<ResponseEntity<ProductResponseDTO>> getProductById(@PathVariable Integer idProduct){
        return wcProductsService.findById(idProduct)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
