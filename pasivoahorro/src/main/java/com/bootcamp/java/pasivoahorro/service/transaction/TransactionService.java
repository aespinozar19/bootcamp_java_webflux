package com.bootcamp.java.pasivoahorro.service.transaction;

import com.bootcamp.java.pasivoahorro.dto.TransactionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    public Mono<TransactionDTO> create(TransactionDTO transactionDTO);
    public Flux<TransactionDTO> findAll();

    public Mono<TransactionDTO> findById(Integer IdTransaction);
}
