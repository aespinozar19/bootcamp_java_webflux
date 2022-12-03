package com.bootcamp.java.pasivoahorro.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = {"idTransaction"})
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "tbl_transaction")
public class Transaction {
    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    private Integer idTransaction;

    @NotNull
    private Integer idProductClient;

    @NotNull
    private Integer idTransactionType;

    @NotNull
    private Double mont;

    @NotNull
    private Date registrationDate;
}
