package com.bootcamp.java.pasivoahorro.converter;

import com.bootcamp.java.pasivoahorro.dto.ProductClientDTO;
import com.bootcamp.java.pasivoahorro.dto.ProductClientRequest;
import com.bootcamp.java.pasivoahorro.dto.webClientDTO.ClientResponseDTO;
import com.bootcamp.java.pasivoahorro.dto.webClientDTO.ProductResponseDTO;
import com.bootcamp.java.pasivoahorro.entity.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientConvert {
    public static ProductClientDTO EntityToDTO(ProductClient productClient) {
        return ProductClientDTO.builder()
                .id(productClient.getId())
                .idProductClient(productClient.getIdProductClient())
                .idProduct(productClient.getIdProduct())
                .productDescription(productClient.getProductDescription())
                .idProductType(productClient.getIdProductType())
                .productTypeDescription(productClient.getProductTypeDescription())
                .idProductSubType(productClient.getIdProductSubType())
                .productSubTypeDescription(productClient.getProductSubTypeDescription())
                .idClient(productClient.getIdClient())
                .idClientType(productClient.getIdClientType())
                .clientTypeDescription(productClient.getClientTypeDescription())
                .idClientDocumentType(productClient.getIdClientDocumentType())
                .clientDocumentTypeDescription(productClient.getClientDocumentTypeDescription())
                .documentNumber(productClient.getDocuementNumber())
                .fullName(productClient.getFullName())
                .authorizedSigners(productClient.getAuthorizedSigners())
                .creditLimit(productClient.getCreditLimit())
                .balance(productClient.getBalance())
                .debt(productClient.getDebt())
                .maintenanceCost(productClient.getMaintenanceCost())
                .movementLimit(productClient.getMovementLimit())
                .credits(productClient.getCredits())
                .build();
    }

    public static ProductClient DTOToEntity(ProductClientDTO productClientDTO) {
        return ProductClient.builder()
                .id(productClientDTO.getId())
                .idProductClient(productClientDTO.getIdProductClient())
                .idProduct(productClientDTO.getIdProduct())
                .productDescription(productClientDTO.getProductDescription())
                .idProductType(productClientDTO.getIdProductType())
                .productTypeDescription(productClientDTO.getProductTypeDescription())
                .idProductSubType(productClientDTO.getIdProductSubType())
                .productSubTypeDescription(productClientDTO.getProductSubTypeDescription())
                .idClient(productClientDTO.getIdClient())
                .idClientType(productClientDTO.getIdClientType())
                .clientTypeDescription(productClientDTO.getClientTypeDescription())
                .idClientDocumentType(productClientDTO.getIdClientDocumentType())
                .clientDocumentTypeDescription(productClientDTO.getClientDocumentTypeDescription())
                .docuementNumber(productClientDTO.getDocumentNumber())
                .fullName(productClientDTO.getFullName())
                .authorizedSigners(productClientDTO.getAuthorizedSigners())
                .creditLimit(productClientDTO.getCreditLimit())
                .balance(productClientDTO.getBalance())
                .debt(productClientDTO.getDebt())
                .maintenanceCost(productClientDTO.getMaintenanceCost())
                .movementLimit(productClientDTO.getMovementLimit())
                .credits(productClientDTO.getCredits())
                .build();
    }

    public static ProductClient DTOToEntity2(ProductClientRequest productClientRequest,
                                            ProductResponseDTO productResponseDTO,
                                            ClientResponseDTO clientResponseDTO) {
        return ProductClient.builder()
                //.id(productClientDTO.getId())
                .idProductClient(productClientRequest.getIdProductClient())
                .idProduct(productResponseDTO.getIdProduct())
                .productDescription(productResponseDTO.getDescription())
                .idProductType(productResponseDTO.getProductTypeDTO().getIdProductType())
                .productTypeDescription(productResponseDTO.getProductTypeDTO().getDescription())
                .idProductSubType(productResponseDTO.getProductSubTypeDTO().getIdProductSubType())
                .productSubTypeDescription(productResponseDTO.getProductSubTypeDTO().getDescription())
                .idClient(clientResponseDTO.getIdClient())
                .idClientType(clientResponseDTO.getClientTypeDTO().getIdClientType())
                .clientTypeDescription(clientResponseDTO.getClientTypeDTO().getDescription())
                .idClientDocumentType(clientResponseDTO.getClientDocumentTypeDTO().getIdClientDocumentType())
                .clientDocumentTypeDescription(clientResponseDTO.getClientDocumentTypeDTO().getDescription())
                .docuementNumber(clientResponseDTO.getDocumentNumber())
                .fullName(clientResponseDTO.getFullName())
                //.authorizedSigners(productClientDTO.getAuthorizedSigners())
                //.creditLimit(productClientDTO.getCreditLimit())
                .balance(0.0)
                //.debt(productClientDTO.getDebt())
                .maintenanceCost(0.0)
                .movementLimit(productClientRequest.getMovementLimit())
                //.credits(productClientDTO.getCredits())
                .build();
    }
}
