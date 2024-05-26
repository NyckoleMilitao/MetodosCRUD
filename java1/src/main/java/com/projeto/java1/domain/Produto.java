package com.projeto.java1.domain;


import lombok.*;
import static lombok.EqualsAndHashCode.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
@Entity(name = "produto")
public class Produto {

    @Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private String descricao;


    public Produto (RequestProductDTO requestProductDTO)
    {
        this.nome = requestProductDTO.nome();
        this.preco = requestProductDTO.preco();
        this.descricao = requestProductDTO.descricao();

    }

    
}
