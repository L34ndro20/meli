package challenge.meli.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;
import java.util.List;

public class InfPaisDto implements Serializable {

    @JsonAlias("currencies")
    private List<MonedaPaisDto> moneda;

    public List<MonedaPaisDto> getMoneda() {
        return moneda;
    }

    public void setMoneda(List<MonedaPaisDto> moneda) {
        this.moneda = moneda;
    }
}
