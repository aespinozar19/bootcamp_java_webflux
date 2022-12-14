package com.bootcamp.java.pasivoahorro.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductClientDTO {
    private String id;
    private Integer idProductClient;
    private Integer idProduct;
    private String productDescription;
    private Integer idProductType;
    private String productTypeDescription;
    private Integer idProductSubType;
    private String productSubTypeDescription;
    private Integer idClient;
    private Integer idClientType;
    private String clientTypeDescription;
    private Integer idClientDocumentType;
    private String clientDocumentTypeDescription;
    private String documentNumber;
    private String fullName;
    private String authorizedSigners;
    private Integer creditLimit;
    private Double balance;
    private Double debt;
    private Double maintenanceCost;
    private Integer movementLimit;
    private Integer credits;
}
