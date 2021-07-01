package challenge.meli.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;

public class PaisIpDto implements Serializable {
    @JsonAlias("countryCode")
    private String codigoPais;

    @JsonAlias("countryName")
    private String nombrePais;
    private String codigoIso;
    private String codMoneda;
    private String monedaLocal;
    private String cotiazacionUsEu;


    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public String getMonedaLocal() {
        return monedaLocal;
    }

    public void setMonedaLocal(String monedaLocal) {
        this.monedaLocal = monedaLocal;
    }

    public String getCotiazacionUsEu() {
        return cotiazacionUsEu;
    }

    public void setCotiazacionUsEu(String cotiazacionUsEu) {
        this.cotiazacionUsEu = cotiazacionUsEu;
    }

    public String getCodMoneda() {
        return codMoneda;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }
}
