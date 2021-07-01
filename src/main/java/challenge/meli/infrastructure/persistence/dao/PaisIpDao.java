package challenge.meli.infrastructure.persistence.dao;

import challenge.meli.infrastructure.persistence.dependency.PaisIpDaoI;
import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoCollection;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PaisIpDao implements PaisIpDaoI {

    @Inject
    private MongoCollection<ListaIpModel> listaIpDao;

    /*El metodo nos permite validar si una Ip ya se encuentra marcada en la lista negra de Ip's*/
    @Override
    public Mono<Boolean> validarIpLista(String ip){
      return Mono.from(listaIpDao.countDocuments(Filters.eq("ip",ip)))
                .flatMap(listaIpModel -> {
                    return Mono.just(listaIpModel > 0?true:false);
                })
                .onErrorResume(throwable -> {
                    return Mono.error(throwable);
                });
    }

    /*El metodo nos permite agregar una Ip en la lista negra de Ip's*/
    @Override
    public Mono<Boolean> guardarIp(ListaIpModel listaIpModel){
        return Mono.from(listaIpDao.insertOne(listaIpModel))
                .map(success -> true)
                .onErrorResume(Mono::error);
    }

}
