package challenge.meli.infrastructure.client;

import challenge.meli.infrastructure.dto.InfTarifaDto;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;

@Singleton
@Client("${tarifa-pais.path}")
public interface TarifaMonedaRest {

    @Get("/latest?access_key={apikey}")
    Mono<InfTarifaDto> obtenerTarifaPais(String apikey);
}
