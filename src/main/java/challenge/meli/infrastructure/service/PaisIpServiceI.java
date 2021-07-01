package challenge.meli.infrastructure.service;


import challenge.meli.domain.exception.MessageIpException;
import challenge.meli.domain.model.PaisIp;
import challenge.meli.infrastructure.client.InfPaisRest;
import challenge.meli.infrastructure.client.PaisIpRest;
import challenge.meli.infrastructure.client.TarifaMonedaRest;
import challenge.meli.infrastructure.dto.InfPaisDto;
import challenge.meli.infrastructure.dto.InfTarifaDto;
import challenge.meli.infrastructure.dto.PaisIpDto;
import challenge.meli.infrastructure.persistence.dependency.PaisIpDaoI;
import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import challenge.meli.infrastructure.service.dependency.PaisIpInf;
import io.micronaut.context.annotation.Value;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PaisIpServiceI implements PaisIpInf {

    @Inject
    PaisIpRest paisIpRest;

    @Inject
    InfPaisRest infPaisRest;

    @Inject
    TarifaMonedaRest tarifaMonedaRest;

    @Inject
    PaisIpDaoI paisIpDaoI;

    @Inject
    private ModelMapper mapper;

    @Value("${tarifa-pais.api-key}")
    private String apiKey;

    /*método que realiza los diferentes llamados a las apis para formar un solo Objet y asi retornarlo a la capa de
    * dominio*/
    @Override
    public Mono<PaisIp> obtenerPais(String ip) {
        return paisIpDaoI.validarIpLista(ip)
                .zipWhen(aBoolean -> aBoolean?Mono.just(false):paisIpDaoI.guardarIp(new ListaIpModel(ip)))
                .flatMap(objects -> {
                    return objects.getT1()?Mono.error(MessageIpException.IP_LIST_BLACK.get()):obtenerPaisIp(ip);
                })
                .zipWhen(paisIpDto -> obtenerInfoPais(paisIpDto))
                .flatMap(objects -> {
                    objects.getT1().setCodMoneda(objects.getT2().getMoneda().get(0).getCodigo());
                    objects.getT1().setMonedaLocal(objects.getT2().getMoneda().get(0).getNombre());
                    return Mono.just(objects.getT1());
                })
                .zipWhen(paisIpDto -> obtenerTarifaPais(paisIpDto))
                .flatMap(objects -> {
                    objects.getT2().getTarifa().entrySet().removeIf(item -> !item.getKey().equals(objects.getT1().getCodMoneda()));
                    objects.getT1().setCotiazacionUsEu(objects.getT2().getTarifa().get(objects.getT1().getCodMoneda()).toString());

                    return Mono.just(objects.getT1());
                })
                .map(paisIpDto -> mapper.map(paisIpDto, PaisIp.class))
                .onErrorResume(Mono::error);
    }

    /*método que mediante la ip nos devuelve el código del pais al que pertenece*/
    private Mono<PaisIpDto> obtenerPaisIp(String ip){
        return paisIpRest.obtenerPaisIp(ip)
                .filter(item -> !item.getCodigoPais().isEmpty())
                .switchIfEmpty(Mono.error(MessageIpException.NO_FOUND_DATA_IP.get()))
                .onErrorResume(Mono::error);
    }

    /*método que mediante el codigo ISO del pais podemos obtener el resto de la informacion
    * como modena y codio de su monela local*/
    private Mono<InfPaisDto> obtenerInfoPais(PaisIpDto paisIpDto){
        return infPaisRest.obtenerInfPais(paisIpDto.getCodigoPais())
                .filter(infPaisDto -> null != infPaisDto.getMoneda())
                .switchIfEmpty(Mono.error(MessageIpException.NO_FOUND_DATA_PAIS.get()))
                .onErrorResume(Mono::error);
    }

    /*método que mediante el codigo de su moneda local se puede obtener su cotizacion en EUROS*/
    private Mono<InfTarifaDto> obtenerTarifaPais(PaisIpDto paisIpDto){
        return tarifaMonedaRest.obtenerTarifaPais(apiKey)
                .onErrorResume(Mono::error);
    }

}
