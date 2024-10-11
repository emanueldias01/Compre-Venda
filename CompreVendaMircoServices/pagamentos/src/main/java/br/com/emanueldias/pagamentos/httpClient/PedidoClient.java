package br.com.emanueldias.pagamentos.httpClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "pedidos-ms", )
public interface PedidoClient {
}
