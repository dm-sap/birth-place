package it.maeci.territory.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalitaRequest {

    private final String data;
    private final String nome;

    public LocalitaRequest(String data, String nome) {
        this.data = data;
        this.nome = nome;
    }
}
