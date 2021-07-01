package challenge.meli.infrastructure.persistence;

import challenge.meli.infrastructure.persistence.model.ListaIpModel;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class MongoContext {

    @Value("${mongodb.database}")
    private String databaseName;

    @Value("${mongodb.collection}")
    private String collectionName;

    @Inject
    private MongoClient mongoClient;

    /*Realizamos la conexion a mongoDB con los valores por defecto*/
    @Singleton
    public MongoCollection<ListaIpModel> getMongoCollectionListaIp(){
        return mongoClient.getDatabase(databaseName).getCollection(collectionName, ListaIpModel.class);
    }
}
