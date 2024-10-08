package br.com.emanueldias.CompreVenda.produto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tab_produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 255)
    private String descricao;

    @NotNull
    @Size(min = 1)
    private Integer quantidade;

    @NotNull
    private BigDecimal preco;



}
