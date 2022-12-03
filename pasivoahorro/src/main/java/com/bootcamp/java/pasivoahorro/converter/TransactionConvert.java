package com.bootcamp.java.pasivoahorro.converter;

import com.bootcamp.java.pasivoahorro.dto.TransactionDTO;
import com.bootcamp.java.pasivoahorro.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionConvert {
    public static TransactionDTO EntityToDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .idTransaction(transaction.getIdTransaction())
                .idProductClient(transaction.getIdProductClient())
                .mont(transaction.getMont())
                .registrationDate(transaction.getRegistrationDate())
                .build();
    }

    public static Transaction DTOtoEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .idTransaction(transactionDTO.getIdTransaction())
                .idProductClient(transactionDTO.getIdProductClient())
                .mont(transactionDTO.getMont())
                .registrationDate(transactionDTO.getRegistrationDate())
                .build();
    }
}
