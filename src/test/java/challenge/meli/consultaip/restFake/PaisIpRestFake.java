package challenge.meli.consultaip.restFake;

import challenge.meli.infrastructure.client.PaisIpRest;
import challenge.meli.infrastructure.dto.PaisIpDto;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;

@Requires(env = Environment.TEST)
@Replaces(PaisIpRest.class)
@Singleton
public class PaisIpRestFake implements PaisIpRest {
    @Override
    public Mono<PaisIpDto> obtenerPaisIp(String ip) {
        PaisIpDto paisIpDto = new PaisIpDto();
        paisIpDto.setCodigoPais("CO");
        paisIpDto.setNombrePais("Colombia");
        paisIpDto.setCodMoneda("COP");
        paisIpDto.setMonedaLocal("Peso Colombiano");
        paisIpDto.setCotiazacionUsEu("3.600");
        paisIpDto.setCodigoIso("CO");

        return Mono.just(paisIpDto);
    }
}
