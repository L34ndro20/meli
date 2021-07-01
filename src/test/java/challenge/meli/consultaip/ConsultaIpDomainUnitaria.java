package challenge.meli.consultaip;

import challenge.meli.domain.dependency.PaisIpI;
import challenge.meli.domain.model.PaisIp;
import challenge.meli.domain.service.PaisIpService;
import challenge.meli.infrastructure.dto.PaisIpDto;
import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import challenge.meli.infrastructure.service.PaisIpServiceI;
import challenge.meli.infrastructure.service.dependency.PaisIpInf;
import io.micronaut.context.env.Environment;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

@MicronautTest(environments = Environment.TEST)
public class ConsultaIpDomainUnitaria {

    @Inject
    PaisIpService paisIpService;

    @Inject
    PaisIpInf paisIpInf;

    @Inject
    ModelMapper mapper;

    @MockBean(PaisIpServiceI.class)
    public PaisIpInf paisIpInf() {
        return mock(PaisIpInf.class);
    }

    @Test
    void consultarIpPaisDomainOk(){

        Mockito.when(paisIpInf.obtenerPais(anyString()))
                .thenReturn(getPais());

        StepVerifier.create(paisIpService.obtenerPais("0.0.0.2"))
                .consumeNextWith(paisIp -> {
                    PaisIpDto paisIpDto = new PaisIpDto();
                    paisIpDto = mapper.map(paisIp,PaisIpDto.class);
                    assertEquals("CO", paisIpDto.getCodigoPais());
                })
                .verifyComplete();
    }


    public Mono<PaisIp> getPais(){
        PaisIpDto paisIpDto = new PaisIpDto();
        paisIpDto.setCodigoPais("CO");
        paisIpDto.setNombrePais("Colombia");
        paisIpDto.setCodMoneda("COP");
        paisIpDto.setMonedaLocal("Peso Colombiano");
        paisIpDto.setCotiazacionUsEu("3.600");
        paisIpDto.setCodigoIso("CO");

        return Mono.just(mapper.map(paisIpDto,PaisIp.class));
    }
}
