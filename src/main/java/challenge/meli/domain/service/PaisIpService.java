package challenge.meli.domain.service;

import challenge.meli.domain.dependency.PaisIpI;
import challenge.meli.domain.exception.MessageIpException;
import challenge.meli.domain.model.PaisIp;
import challenge.meli.infrastructure.service.dependency.PaisIpInf;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.regex.Pattern;

@Singleton
public class PaisIpService implements PaisIpI {

    public static String FORMATO_IP = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$";

    @Inject
    PaisIpInf paisIpService;

    /*El metodo del dominio solo realiza el llamado al servicio de la infraestructura para que
    * devuelva toda la informacion relacionada a la IP*/
    @Override
    public Mono<PaisIp> obtenerPais(String ip) {
        return Mono.just(Pattern.compile(FORMATO_IP).matcher(ip).matches())
                .filter(it->it)
                .switchIfEmpty(Mono.error(MessageIpException.INVALID_IP.get()))
                .then(paisIpService.obtenerPais(ip))
                .onErrorResume(Mono::error);
    }
}
