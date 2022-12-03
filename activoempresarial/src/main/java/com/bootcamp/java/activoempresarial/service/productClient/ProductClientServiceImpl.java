package com.bootcamp.java.activoempresarial.service.productClient;

import com.bootcamp.java.activoempresarial.common.Constants;
import com.bootcamp.java.activoempresarial.common.exception.FunctionalException;
import com.bootcamp.java.activoempresarial.converter.ProductClientConvert;
import com.bootcamp.java.activoempresarial.dto.ProductClientDTO;
import com.bootcamp.java.activoempresarial.entity.ProductClientEntity;
import com.bootcamp.java.activoempresarial.entity.TransactionEntity;
import com.bootcamp.java.activoempresarial.model.AfiliacionRequestModel;
import com.bootcamp.java.activoempresarial.repository.ProductClientRepository;
import com.bootcamp.java.activoempresarial.service.webClients.client.WcClientsService;
import com.bootcamp.java.activoempresarial.service.webClients.product.WcProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Console;

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
    public Mono<ProductClientDTO> create(AfiliacionRequestModel afiliacionRequestModel) {
        log.info(afiliacionRequestModel.toString());
        return wcClientsService.findById(afiliacionRequestModel.getDocumentNumber())
                .flatMap(x->{
                    if(x.getClientTypeDTO().getIdClientType() != Constants.ClientType.Personal)
                        return Mono.error(new FunctionalException("El tipo de cliente no es personal"));
                    if(x.getClientDocumentTypeDTO().getIdClientDocumentType() != Constants.ClientDocumentType.DNI)
                        return Mono.error(new FunctionalException("El tipo de documento del cliente no es DNI"));

                    return wcProductsService.findById(afiliacionRequestModel.getIdProduct())
                            .flatMap(y->{
                                log.info("Validacion si el tipo de cliente es personal");
                                if(y.getProductTypeDTO().getIdProductType() != Constants.ProductType.Activos)
                                    return Mono.error(new FunctionalException("El tipo de producto no pertenece al grupo de Activos"));
                                if(y.getProductSubTypeDTO().getIdProductSubType() != Constants.ProductSubType.CreditoPersonal)
                                    return Mono.error(new FunctionalException("El subtipo de producto no pertenece a credito personal"));

                                ProductClientEntity productClientEntity = new ProductClientEntity();
                                productClientEntity.setIdClient(x.getIdClient());
                                productClientEntity.setIdProduct(afiliacionRequestModel.getIdProduct());
                                productClientEntity.setProductDescription(y.getDescription());
                                productClientEntity.setIdProductType(y.getProductTypeDTO().getIdProductType());
                                productClientEntity.setProductTypeDescription(y.getProductTypeDTO().getDescription());
                                productClientEntity.setIdProductSubType(y.getProductSubTypeDTO().getIdProductSubType());
                                productClientEntity.setProductSubTypeDescription(y.getProductSubTypeDTO().getDescription());
                                productClientEntity.setIdClientType(x.getClientTypeDTO().getIdClientType());
                                productClientEntity.setClientTypeDescription(x.getClientTypeDTO().getDescription());
                                productClientEntity.setIdClientDocumentType(x.getClientDocumentTypeDTO().getIdClientDocumentType());
                                productClientEntity.setClientDocumentTypeDescription(x.getClientDocumentTypeDTO().getDescription());
                                productClientEntity.setDocumentNumber(afiliacionRequestModel.getDocumentNumber());
                                productClientEntity.setFullName(x.getFullName());
                                productClientEntity.setAuthorizedSigners(afiliacionRequestModel.getAuthorizedSigners());
                                productClientEntity.setCreditLimit(afiliacionRequestModel.getCreditLimit());
                                productClientEntity.setBalance(afiliacionRequestModel.getBalance());
                                productClientEntity.setDebt(0.0);
                                productClientEntity.setMaintenanceCost(0.0);
                                productClientEntity.setMovementLimit(0);
                                productClientEntity.setCredits(0);
                                log.info(productClientEntity.toString());
                                return productClientRepository.save(productClientEntity)
                                        .map(productClient -> productClientConvert.EntityToDTO(productClient))
                                        .switchIfEmpty(Mono.error(() -> new FunctionalException("Ocurrio un error al guardar")));

                            }).switchIfEmpty(Mono.error(() -> new FunctionalException("Ocurrio un error al consultar el servicio de producto")));
                }).switchIfEmpty(Mono.error(() -> new FunctionalException("Ocurrio un error al consultar el servicio de cliente")));
    }
}
















/*
                                return productClientRepository.findByDocumentNumber(afiliacionRequestModel.getDocumentNumber()).collectList()
                                        .flatMap(z->{
                                            if(z.stream().count()>1)
                                                return Mono.error(new FunctionalException("El subtipo de producto no pertenece a credito personal"));
                                        });
 */