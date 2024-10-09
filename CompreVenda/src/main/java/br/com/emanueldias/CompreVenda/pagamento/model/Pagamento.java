package br.com.emanueldias.CompreVenda.pagamento.model;

import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoRequestDTO;
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
    private String nome;

    @NotBlank
    private String codigo;

    private LocalDate expiracao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long pedidoId;

    public Pagamento(PagamentoRequestDTO dto) {

        this.nome = dto.getNome();
        this.valor = dto.getValor();
        this.expiracao = LocalDate.now().plusDays(2);
        this.status = Status.CRIADO;
        this.pedidoId = dto.getPedidoId();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCodigo(@NotBlank @Size(min = 3) String codigo) {
        this.codigo = codigo;
    }
}
