package com.bootcamp.java.activoempresarial.service.productClient;

import com.bootcamp.java.activoempresarial.dto.ProductClientDTO;
import com.bootcamp.java.activoempresarial.model.AfiliacionRequestModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductClientService {
    public Flux<ProductClientDTO> findAll();

    public Mono<ProductClientDTO> findById(Integer IdProductClient);

    public Mono<ProductClientDTO> create(AfiliacionRequestModel afiliacionRequestModel);
}
