package br.com.emanueldias.pedidos.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "pagamentos-ms", url = "http://localhost:8082")
public interface PagamentoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/pagamentos-ms/pagamentos")
    void criaPagamento(@RequestBody RequestCriaPagamento req);
}
