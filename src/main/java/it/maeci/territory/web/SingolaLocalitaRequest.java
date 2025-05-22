package it.maeci.territory.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingolaLocalitaRequest {

    private final Long localitaId;
    private final String territorioId;
    private final String data;

    public SingolaLocalitaRequest(Long localitaId, String territorioId, String data) {
        this.localitaId = localitaId;
        this.territorioId = territorioId;
        this.data = data;
    }
}
