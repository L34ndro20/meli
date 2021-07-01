package challenge.meli.consultaip.restFake;

import challenge.meli.infrastructure.client.InfPaisRest;
import challenge.meli.infrastructure.dto.InfPaisDto;
import challenge.meli.infrastructure.dto.MonedaPaisDto;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;
import java.util.Arrays;

@Requires(env = Environment.TEST)
@Replaces(InfPaisRest.class)
@Singleton
public class InfPaisRestFake implements InfPaisRest {

    @Override
    public Mono<InfPaisDto> obtenerInfPais(String codPais) {
        InfPaisDto infPaisDto = new InfPaisDto();
        MonedaPaisDto monedaPaisDto = new MonedaPaisDto();
        monedaPaisDto.setCodigo("COP");
        monedaPaisDto.setNombre("Pesos Colombianos");
        monedaPaisDto.setSimbolo("$");
        infPaisDto.setMoneda(Arrays.asList(monedaPaisDto));

        return Mono.just(infPaisDto);
    }
}
