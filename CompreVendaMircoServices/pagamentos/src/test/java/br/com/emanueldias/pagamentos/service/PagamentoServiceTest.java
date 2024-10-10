package br.com.emanueldias.pagamentos.service;

import br.com.emanueldias.pagamentos.dto.PagamentoRequestDTO;
import br.com.emanueldias.pagamentos.dto.PagamentoResponseDTO;
import br.com.emanueldias.pagamentos.model.Pagamento;
import br.com.emanueldias.pagamentos.model.Status;
import br.com.emanueldias.pagamentos.repository.PagamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PagamentoServiceTest {

    @Mock
    PagamentoRepository pagamentoRepository;

    @InjectMocks
    PagamentoService pagamentoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Lista todos os pagamentos")
    void listaPagamentos(){

        PagamentoRequestDTO p1 = new PagamentoRequestDTO("nome", BigDecimal.TEN, 1L);
        PagamentoRequestDTO p2 = new PagamentoRequestDTO("nome1", BigDecimal.TEN, 2L);

        Pagamento pagamento = new Pagamento(p1);
        Pagamento pagamento1 = new Pagamento(p2);

        List<PagamentoResponseDTO> pagamentoResponseDTOS = Arrays.asList(new PagamentoResponseDTO(pagamento), new PagamentoResponseDTO(pagamento1));

        when(pagamentoService.listAll()).thenReturn(pagamentoResponseDTOS);
    }

    @Test
    @DisplayName("cria um pagamento")
    void criaPagamento(){
        PagamentoRequestDTO pagamentoRequestDTO = new PagamentoRequestDTO("nome", BigDecimal.TEN, 1L);
        Pagamento pagamento = new Pagamento(pagamentoRequestDTO);
        pagamento.setCodigo("10");
        PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO(pagamento);

        assertThat(pagamentoService.create(pagamentoRequestDTO).getNome()).isEqualTo(pagamentoResponseDTO.getNome());
    }

    @Test
    @DisplayName("cancela pagamento")
    void cancelaPagamento(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN, "nome", "codigo", LocalDate.now().plusDays(2), Status.CRIADO, 1L);

        when(pagamentoRepository.getReferenceById(pagamento.getId())).thenReturn(pagamento);
        pagamentoService.cancelaPagamento(pagamento.getId());

        assertThat(pagamento.getStatus()).isEqualTo(Status.CANCELADO);
    }

    @Test
    @DisplayName("paga pagamento")
    void pagaPagamento(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN, "nome", "codigo", LocalDate.now().plusDays(2), Status.CRIADO, 1L);

        when(pagamentoRepository.getReferenceById(pagamento.getId())).thenReturn(pagamento);
        pagamentoService.pagaPagamento(pagamento.getId());

        assertThat(pagamento.getStatus()).isEqualTo(Status.PAGO);
    }

    @Test
    @DisplayName("nao cancela pagamento pois ja está cancelado")
    void cancelaPagamentoFailJaCancelado(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN, "nome", "codigo", LocalDate.now().plusDays(2), Status.CANCELADO, 1L);

        when(pagamentoRepository.getReferenceById(pagamento.getId())).thenReturn(pagamento);

        Assertions.assertThrows(IllegalCallerException.class, () -> pagamentoService.cancelaPagamento(pagamento.getId()));

    }

    @Test
    @DisplayName("nao cancela pagamento pois ja foi pago")
    void cancelaPagamentoFailJaFoiPago(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN, "nome", "codigo", LocalDate.now().plusDays(2), Status.PAGO, 1L);

        when(pagamentoRepository.getReferenceById(pagamento.getId())).thenReturn(pagamento);

        Assertions.assertThrows(IllegalCallerException.class, () -> pagamentoService.cancelaPagamento(pagamento.getId()));

    }

    @Test
    @DisplayName("nao paga pagamento pois ja está pago")
    void pagaPagamentoFailJaPago(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN, "nome", "codigo", LocalDate.now().plusDays(2), Status.PAGO, 1L);

        when(pagamentoRepository.getReferenceById(pagamento.getId())).thenReturn(pagamento);

        Assertions.assertThrows(IllegalCallerException.class, () -> pagamentoService.pagaPagamento(pagamento.getId()));

    }

    @Test
    @DisplayName("nao paga pagamento pois ja está cancelado")
    void pagaPagamentoFailJaCancelado(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN, "nome", "codigo", LocalDate.now().plusDays(2), Status.CANCELADO, 1L);

        when(pagamentoRepository.getReferenceById(pagamento.getId())).thenReturn(pagamento);

        Assertions.assertThrows(IllegalCallerException.class, () -> pagamentoService.pagaPagamento(pagamento.getId()));

    }

}