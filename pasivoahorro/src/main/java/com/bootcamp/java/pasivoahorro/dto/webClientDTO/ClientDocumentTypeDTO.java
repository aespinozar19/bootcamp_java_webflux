package com.bootcamp.java.pasivoahorro.dto.webClientDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDocumentTypeDTO {

    private String id;
    private int idClientDocumentType;
    private String description;
}
