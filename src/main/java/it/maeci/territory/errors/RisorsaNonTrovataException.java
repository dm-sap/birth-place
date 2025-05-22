package it.maeci.territory.errors;

import lombok.Getter;


@Getter
public abstract class RisorsaNonTrovataException extends TerritorioException {

    private static final long serialVersionUID = 1312068305504821985L;
    private final String filtro;

    public RisorsaNonTrovataException(String messaggio, String filtro) {
        super(messaggio);
        this.filtro = filtro;
    }

}
