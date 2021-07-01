package challenge.meli.consultaip;

import challenge.meli.domain.model.PaisIp;
import challenge.meli.infrastructure.dto.PaisIpDto;
import challenge.meli.infrastructure.persistence.MongoContext;
import challenge.meli.infrastructure.persistence.dao.PaisIpDao;
import challenge.meli.infrastructure.persistence.dependency.PaisIpDaoI;
import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import challenge.meli.infrastructure.service.PaisIpServiceI;
import challenge.meli.infrastructure.service.dependency.PaisIpInf;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.micronaut.configuration.mongo.reactive.MongoSettings;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.core.io.socket.SocketUtils;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import io.reactivex.Flowable;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.inject.Inject;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest(environments = Environment.TEST)
public class ConsultaIpInfraUnitaria {


    @Inject
    ModelMapper mapper;

    @Inject
    PaisIpServiceI paisIpServiceI;

    @Inject
    PaisIpDaoI paisIpDaoI;

    @MockBean(PaisIpDao.class)
    public PaisIpDaoI paisIpDaoI() {
        return mock(PaisIpDaoI.class);
    }

    @Test
    void consultarIpPaisInfraOk(){
        Mockito.when(paisIpDaoI.validarIpLista(anyString())).thenReturn(getValidarLista());
        Mockito.when(paisIpDaoI.guardarIp(any(ListaIpModel.class))).thenReturn(getValidarLista());

        StepVerifier.create(paisIpServiceI.obtenerPais("0.0.0.2"))
                .consumeNextWith(paisIp -> {
                    PaisIpDto paisIpDto = new PaisIpDto();
                    paisIpDto = mapper.map(paisIp,PaisIpDto.class);
                    assertEquals("CO", paisIpDto.getCodigoPais());
                })
                .verifyComplete();
    }

    public Mono<Boolean> getValidarLista(){
        return Mono.just(false);
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
