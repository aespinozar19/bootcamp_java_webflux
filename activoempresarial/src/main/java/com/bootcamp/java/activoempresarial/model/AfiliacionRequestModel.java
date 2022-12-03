package com.bootcamp.java.activoempresarial.model;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

@Data
public class AfiliacionRequestModel {
    private Integer idProductClient;
    private Integer idProduct;
    private Integer idTransaction;
    private String documentNumber;
    private String authorizedSigners;
    private Integer creditLimit;
    private Double balance;
    private Integer credits;
}
