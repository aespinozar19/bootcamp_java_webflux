package com.bootcamp.java.pasivoahorro.service.transaction;

import com.bootcamp.java.pasivoahorro.converter.TransactionConvert;
import com.bootcamp.java.pasivoahorro.dto.TransactionDTO;
import com.bootcamp.java.pasivoahorro.entity.Transaction;
import com.bootcamp.java.pasivoahorro.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService{


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    TransactionConvert transactionConverter;

    @Override
    public Mono<TransactionDTO> create(TransactionDTO transactionDTO) {

        //Registrar transaccion
        //Actualizar saldos en tabla ClienteProduct
        return null;
    }

    @Override
    public Flux<TransactionDTO> findAll() {
        log.debug("findAll executing");
        Flux<TransactionDTO> dataTransactionDTO = transactionRepository.findAll()
                .map(TransactionConvert::EntityToDTO);
        return dataTransactionDTO;
    }

    public void ActualizarSaldos(){
        log.info("Actualizar");
    }

    @Override
    public Mono<TransactionDTO> findById(Integer IdTransaction) {
        log.debug("findById executing {}", IdTransaction);
        Mono<TransactionDTO> dataTransactionDTO = transactionRepository.findByIdTransaction(IdTransaction)
                .map(TransactionConvert::EntityToDTO);
        log.debug("findById executed {}", dataTransactionDTO);
        return dataTransactionDTO;
    }
}
