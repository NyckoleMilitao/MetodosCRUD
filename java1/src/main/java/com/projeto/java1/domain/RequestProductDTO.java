package com.projeto.java1.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductDTO( @NotBlank String nome, 
                                @NotNull double preco, 
                                String descricao) {

}
