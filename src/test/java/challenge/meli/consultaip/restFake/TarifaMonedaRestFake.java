package challenge.meli.consultaip.restFake;


import challenge.meli.infrastructure.client.TarifaMonedaRest;
import challenge.meli.infrastructure.dto.InfTarifaDto;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Requires(env = Environment.TEST)
@Replaces(TarifaMonedaRest.class)
@Singleton
public class TarifaMonedaRestFake implements TarifaMonedaRest {
    @Override
    public Mono<InfTarifaDto> obtenerTarifaPais(String apikey) {
        InfTarifaDto infTarifaDto = new InfTarifaDto();
        Map<String,Object> tarifa = new HashMap<>();
        tarifa.put("COP","3600");
        infTarifaDto.setBase("EU");
        infTarifaDto.setTarifa(tarifa);

        return Mono.just(infTarifaDto);
    }
}
