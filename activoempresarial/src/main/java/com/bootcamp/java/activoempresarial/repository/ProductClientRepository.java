package com.bootcamp.java.activoempresarial.repository;

import com.bootcamp.java.activoempresarial.entity.ProductClientEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductClientRepository extends ReactiveMongoRepository<ProductClientEntity,Integer> {
    Mono<ProductClientEntity> findByIdProductClient(Integer IdProductClient);
    Flux<ProductClientEntity> findByDocumentNumber(String documentNumber);
}