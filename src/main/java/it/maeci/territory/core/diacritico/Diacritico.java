package it.maeci.territory.core.diacritico;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "DIACRITICO", schema = "CODIFICA3")
public class Diacritico {
    @Id
    @Size(max = 10)
    @Column(name = "DIACRITICO_ID", nullable = false, length = 10)
    private String diacriticoId;

    @Size(max = 10)
    @NotNull
    @Column(name = "CAR_DIACRITICO", nullable = false, length = 10)
    private String carDiacritico;

    @Size(max = 10)
    @NotNull
    @Column(name = "TRASL_ICAO", nullable = false, length = 10)
    private String traslIcao;

}