package challenge.meli.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;
import java.util.Map;

public class InfTarifaDto implements Serializable {
    public String base;

    @JsonAlias("rates")
    public Map<String,Object> tarifa;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Object> getTarifa() {
        return tarifa;
    }

    public void setTarifa(Map<String, Object> tarifa) {
        this.tarifa = tarifa;
    }
}
