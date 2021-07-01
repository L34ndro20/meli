package challenge.meli.infrastructure.persistence.dependency;

import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import reactor.core.publisher.Mono;

public interface PaisIpDaoI {
    Mono<Boolean> guardarIp(ListaIpModel listaIpModel);
    Mono<Boolean> validarIpLista(String ip);
}
