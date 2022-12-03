package com.bootcamp.java.pasivoahorro.service.productClient;

import com.bootcamp.java.pasivoahorro.dto.ProductClientDTO;
import com.bootcamp.java.pasivoahorro.dto.ProductClientRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductClientService {

    public Flux<ProductClientDTO> findAll();

    public Mono<ProductClientDTO> findById(Integer IdProductClient);

    public Mono<ProductClientDTO> create(ProductClientRequest productClientRequest);

    public Mono<ProductClientDTO> update(ProductClientDTO productClientDTO);

}
