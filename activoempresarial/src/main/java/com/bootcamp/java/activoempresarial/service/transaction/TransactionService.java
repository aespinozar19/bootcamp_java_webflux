package com.bootcamp.java.activoempresarial.service.transaction;

import com.bootcamp.java.activoempresarial.dto.ProductClientDTO;
import com.bootcamp.java.activoempresarial.dto.TransactionDTO;
import com.bootcamp.java.activoempresarial.model.AfiliacionRequestModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    public Flux<TransactionDTO> findAll();

    public Mono<TransactionDTO> findById(Integer IdTransaction);

    public Mono<TransactionDTO> create(TransactionDTO transactionDTO);

}
