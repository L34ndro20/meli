package challenge.meli.domain.dependency;

import challenge.meli.domain.model.PaisIp;
import reactor.core.publisher.Mono;

public interface PaisIpI {

    Mono<PaisIp> obtenerPais(String ip);
}
