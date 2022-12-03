package com.bootcamp.java.pasivoahorro.service.productClient;

import com.bootcamp.java.pasivoahorro.common.exceptionHandler.FunctionalException;
import com.bootcamp.java.pasivoahorro.converter.ProductClientConvert;
import com.bootcamp.java.pasivoahorro.dto.ProductClientDTO;
import com.bootcamp.java.pasivoahorro.dto.ProductClientRequest;
import com.bootcamp.java.pasivoahorro.entity.ProductClient;
import com.bootcamp.java.pasivoahorro.repository.ProductClientRepository;
import com.bootcamp.java.pasivoahorro.service.webClients.WcClientsService;
import com.bootcamp.java.pasivoahorro.service.webClients.WcProductsService;
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
public class ProductClientServiceImpl implements ProductClientService{

    @Autowired
    private ProductClientRepository productClientRepository;
    @Autowired
    ProductClientConvert productClientConvert;

    @Autowired
    WcClientsService wcClientsService;

    @Autowired
    WcProductsService wcProductsService;

    @Override
    public Flux<ProductClientDTO> findAll() {
        log.debug("findAll executing");
        Flux<ProductClientDTO> dataProductClientDTO = productClientRepository.findAll()
                .map(ProductClientConvert::EntityToDTO);
        return dataProductClientDTO;
    }

    @Override
    public Mono<ProductClientDTO> findById(Integer IdProductClient) {
        log.debug("findById executing {}", IdProductClient);
        Mono<ProductClientDTO> dataProductClientDTO = productClientRepository.findByIdProductClient(IdProductClient)
                .map(prdCli -> productClientConvert.EntityToDTO(prdCli));
        log.debug("findById executed {}", IdProductClient);
        return dataProductClientDTO;
    }

    @Override
    public Mono<ProductClientDTO> create(ProductClientRequest productClientRequest) {
        return wcClientsService.findByDocumentNumber(productClientRequest.getDocumentNumber())
                .flatMap(cliente -> {
                        return wcProductsService.findById(productClientRequest.getIdProduct())
                                .flatMap(product-> {
                                    if(!product.getProductTypeDTO().getIdProductType().equals(1))
                                        return Mono.error(new FunctionalException("El producto no es Tipo Pasivo"));
                                    if(!product.getProductSubTypeDTO().getIdProductSubType().equals(1))
                                        return Mono.error(new FunctionalException("El producto no es SubTipo Ahorro"));

                                    ProductClient productclient = ProductClient.builder()
                                            //.id(productClientDTO.getId())
                                            .idProductClient(productClientRequest.getIdProductClient())
                                            .idProduct(product.getIdProduct())
                                            .productDescription(product.getDescription())
                                            .idProductType(product.getProductTypeDTO().getIdProductType())
                                            .productTypeDescription(product.getProductTypeDTO().getDescription())
                                            .idProductSubType(product.getProductSubTypeDTO().getIdProductSubType())
                                            .productSubTypeDescription(product.getProductSubTypeDTO().getDescription())
                                            .idClient(cliente.getIdClient())
                                            .idClientType(cliente.getClientTypeDTO().getIdClientType())
                                            .clientTypeDescription(cliente.getClientTypeDTO().getDescription())
                                            .idClientDocumentType(cliente.getClientDocumentTypeDTO().getIdClientDocumentType())
                                            .clientDocumentTypeDescription(cliente.getClientDocumentTypeDTO().getDescription())
                                            .docuementNumber(cliente.getDocumentNumber())
                                            .fullName(cliente.getFullName())
                                            //.authorizedSigners(productClientDTO.getAuthorizedSigners())
                                            //.creditLimit(productClientDTO.getCreditLimit())
                                            .balance(0.0)
                                            //.debt(productClientDTO.getDebt())
                                            .maintenanceCost(0.0)
                                            .movementLimit(productClientRequest.getMovementLimit())
                                            //.credits(productClientDTO.getCredits())
                                            .build();


                                    return productClientRepository.save(productclient)
                                            .map(ProductClientConvert::EntityToDTO);

                                })
                                .switchIfEmpty(Mono.error(new FunctionalException("No se encontro el producto")));
                })
                .switchIfEmpty(Mono.error(new FunctionalException("No se encontro el cliente")));
    }


    @Override
    public Mono<ProductClientDTO> update(ProductClientDTO productClientDTO) {
        return null;
    }
}
