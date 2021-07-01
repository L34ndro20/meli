package challenge.meli.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;

public class MonedaPaisDto implements Serializable {
    @JsonAlias("code")
    public String codigo;

    @JsonAlias("name")
    public String nombre;

    @JsonAlias("symbol")
    public String simbolo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}
