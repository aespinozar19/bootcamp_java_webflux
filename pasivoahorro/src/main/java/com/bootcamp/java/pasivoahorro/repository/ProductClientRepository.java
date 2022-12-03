package com.bootcamp.java.pasivoahorro.repository;

import com.bootcamp.java.pasivoahorro.dto.ProductClientDTO;
import com.bootcamp.java.pasivoahorro.entity.ProductClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductClientRepository extends ReactiveMongoRepository<ProductClient,Integer> {
    Mono<ProductClient> findByIdProductClient(Integer IdProductClient);
}
