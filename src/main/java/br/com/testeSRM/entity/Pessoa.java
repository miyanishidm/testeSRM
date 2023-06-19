package br.com.testeSRM.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "pessoa")
public class Pessoa {
    @Id
    private String id;
    private String nome;
    private String identificador;
    private EnumTipoIdentificador tipoIdentificador;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa that = (Pessoa) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Pessoa(String nome, String identificador, EnumTipoIdentificador tipoIdentificador) {
        this.nome = nome;
        this.identificador = identificador;
        this.tipoIdentificador = tipoIdentificador;
    }

    public Pessoa(){
    }


    public Pessoa merge(Pessoa other, EnumTipoIdentificador tipoIdentificador) {
        this.nome = other.nome;
        this.identificador = other.identificador;
        this.tipoIdentificador = tipoIdentificador;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public EnumTipoIdentificador getTipoIdentificador() {
        return tipoIdentificador;
    }

    public void setTipoIdentificador(EnumTipoIdentificador tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }
}
