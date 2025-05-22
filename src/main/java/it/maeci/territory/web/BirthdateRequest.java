package it.maeci.territory.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BirthdateRequest {

    private final String data;

    public BirthdateRequest(String data) {
        this.data = data;
    }
}
