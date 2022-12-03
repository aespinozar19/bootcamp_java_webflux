package com.bootcamp.java.pasivoahorro.dto.webClientDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDTO {
    private String id;
    private Integer idClient;
    private String documentNumber;
    private String fullName;
    private ClientTypeDTO clientTypeDTO;
    private ClientDocumentTypeDTO clientDocumentTypeDTO;
}