package com.projeto.fullstack.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "produtos")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    private String id;

    @NotBlank
    @Size(min = 3, max = 70)
    private String nome;

    @NotNull
    @Positive
    private Double preco;

    @NotBlank
    @Size(min = 3, max = 100)
    private String descricao;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;
}
