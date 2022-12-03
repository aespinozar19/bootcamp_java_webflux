package com.bootcamp.java.activoempresarial.converter;

import com.bootcamp.java.activoempresarial.dto.TransactionDTO;
import com.bootcamp.java.activoempresarial.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionConvert {

    public static TransactionDTO EntityToDTO(TransactionEntity transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .idTransaction(transaction.getIdTransaction())
                .idProductClient(transaction.getIdProductClient())
                .mont(transaction.getMont())
                .registrationDate(transaction.getRegistrationDate())
                .build();
    }

    public static TransactionEntity DTOToEntity(TransactionDTO transactionDTO) {
        return TransactionEntity.builder()
                .id(transactionDTO.getId())
                .idTransaction(transactionDTO.getIdTransaction())
                .idProductClient(transactionDTO.getIdProductClient())
                .mont(transactionDTO.getMont())
                .registrationDate(transactionDTO.getRegistrationDate())
                .build();
    }
}
