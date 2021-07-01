package challenge.meli.infrastructure.persistence.model;

import java.io.Serializable;

public class ListaIpModel implements Serializable {
    private String ip;

    public ListaIpModel(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
