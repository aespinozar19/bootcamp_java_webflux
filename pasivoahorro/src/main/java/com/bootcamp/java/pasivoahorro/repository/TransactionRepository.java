package com.bootcamp.java.pasivoahorro.repository;

import com.bootcamp.java.pasivoahorro.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction,Integer> {

    Mono<Transaction> findByIdTransaction(Integer IdTransaction);

}
