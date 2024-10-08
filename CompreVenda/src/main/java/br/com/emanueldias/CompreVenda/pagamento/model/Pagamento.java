package br.com.emanueldias.CompreVenda.pagamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tab_pagamentos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotBlank
    @Size(min = 3)
    private String codigo;

    private LocalDate expiracao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long pedidoId;
}
