package challenge.meli.application;

import challenge.meli.domain.service.PaisIpService;
import challenge.meli.infrastructure.dto.PaisIpDto;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Controller("${micronaut.context-path}/")
public class PaisIpCtr {

    @Inject
    PaisIpService paisIpService;

    @Inject
    private ModelMapper mapper;

    /*El metodo nos permite validar si el artefacto se encuentra ejecutandose ya sea en docker o local*/
    @Get(value = "estado", produces = MediaType.APPLICATION_JSON)
    public Mono<String> comprobacionChallenge() {
        return Mono.just("{\n" +
                "  \"estado\" : \"Ok\",\n" +
                "  \"mensaje\" : \"ejecutando desafio\"\n" +
                "}");
    }


    /*El metodo nos permite consultar la informacion del pais a la que corresponde la Ip*/
    @Get(value = "consultar/{ip}", produces = MediaType.APPLICATION_JSON)
    public Mono<PaisIpDto> consultarPaisIp(String ip) {
        return paisIpService.obtenerPais(ip)
                .map(paisIp -> mapper.map(paisIp, PaisIpDto.class));
    }
}
