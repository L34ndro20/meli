package challenge.meli.infrastructure.client;

import challenge.meli.infrastructure.dto.PaisIpDto;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;

@Singleton
@Client("${pais-ip.path}")
public interface PaisIpRest {

    @Get("/ip?{ip}")
    public Mono<PaisIpDto> obtenerPaisIp(String ip);

}
