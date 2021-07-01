package challenge.meli.infrastructure.client;

import challenge.meli.infrastructure.dto.InfPaisDto;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;

@Singleton
@Client("${info-pais.path}")
public interface InfPaisRest {

    @Get("/alpha/{codPais}")
    Mono<InfPaisDto> obtenerInfPais(String codPais);
}
