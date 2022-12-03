package com.bootcamp.java.activoempresarial.service.transaction;

import com.bootcamp.java.activoempresarial.common.exception.FunctionalException;
import com.bootcamp.java.activoempresarial.converter.TransactionConvert;
import com.bootcamp.java.activoempresarial.dto.TransactionDTO;
import com.bootcamp.java.activoempresarial.repository.TransactionRepository;
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
    public Flux<TransactionDTO> findAll() {
        log.debug("findAll executing");
        Flux<TransactionDTO> dataTransactionDTO = transactionRepository.findAll()
                .map(TransactionConvert::EntityToDTO);
        return dataTransactionDTO;
    }

    @Override
    public Mono<TransactionDTO> findById(Integer IdTransaction) {
        log.debug("findById executing {}", IdTransaction);
        Mono<TransactionDTO> dataTransactionDTO = transactionRepository.findByIdTransaction(IdTransaction)
                .map(trxType -> transactionConverter.EntityToDTO(trxType));
        log.debug("findById executed {}", dataTransactionDTO);
        return dataTransactionDTO;
    }

    @Override
    public Mono<TransactionDTO> create(TransactionDTO transactionDTO) {
        log.debug("create executing {}",transactionDTO);
        return transactionRepository.save(transactionConverter.DTOToEntity(transactionDTO))
                .map(clientProduct -> transactionConverter.EntityToDTO(clientProduct))
                .switchIfEmpty(Mono.error(() -> new FunctionalException("Ocurrio un error al guardar las transacciones")));
    }
}
