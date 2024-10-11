package br.com.emanueldias.pagamentos.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "pedidos-ms", url = "http://localhost:8082")
public interface PedidoClient {
    @RequestMapping(method = RequestMethod.PATCH, value = "pedidos-ms/pedidos/pagar/{id}")
    void pagaPedido(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.PATCH, value = "pedidos-ms/pedidos/cancelar/{id}")
    void cancelaPedido(@PathVariable Long id);
}
