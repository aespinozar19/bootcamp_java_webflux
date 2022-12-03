package com.bootcamp.java.activoempresarial.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionDTO {
    private String id;
    private Integer idTransaction;
    private Integer idProductClient;
    private Integer idTransactionType;
    private Double mont;
    private Date registrationDate;
}