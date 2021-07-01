package challenge.meli.infrastructure.service.dependency;

import challenge.meli.domain.model.PaisIp;
import reactor.core.publisher.Mono;

public interface PaisIpInf {
    Mono<PaisIp> obtenerPais(String ip);
}
