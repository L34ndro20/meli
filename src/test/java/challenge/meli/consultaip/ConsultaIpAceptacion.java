package challenge.meli.consultaip;

import challenge.meli.consultaip.restFake.InfPaisRestFake;
import challenge.meli.infrastructure.client.InfPaisRest;
import challenge.meli.infrastructure.dto.PaisIpDto;
import challenge.meli.infrastructure.persistence.dao.PaisIpDao;
import challenge.meli.infrastructure.persistence.dependency.PaisIpDaoI;
import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import challenge.meli.infrastructure.service.PaisIpServiceI;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@MicronautTest(environments = Environment.TEST)
public class ConsultaIpAceptacion {

    @Inject
    @Client("/challenge")
    HttpClient client;

    @Inject
    PaisIpDaoI paisIpDaoI;

    @MockBean(PaisIpDao.class)
    public PaisIpDaoI paisIpDaoI() {
        return mock(PaisIpDaoI.class);
    }

    @Test
    public void consultaIpPais() {
        Mockito.when(paisIpDaoI.validarIpLista(anyString())).thenReturn(Mono.just(false));
        Mockito.when(paisIpDaoI.guardarIp(any(ListaIpModel.class))).thenReturn(Mono.just(false));

        StepVerifier.create(client.retrieve(HttpRequest.GET("/consultar/0.0.0.1"), PaisIpDto.class))
                .consumeNextWith(paisIpDto ->{
                    assertNotNull(paisIpDto);
                    assertTrue(paisIpDto.getCodigoPais().equals("CO"));
                }).expectComplete()
                .verify();
    }
}
