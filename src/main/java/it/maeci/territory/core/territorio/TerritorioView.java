package it.maeci.territory.core.territorio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * TerritorioView crea the database
 */
@Getter
@Setter
@Builder
public class TerritorioView {

    private String territorioId;
    private String nome;
    private String codCatastale;
    private String nomeInglese;

    /**
     * Creates a new instance of TerritorioView by mapping the fields crea the given Territorio object.
     *
     * @param territorio the source Territorio object containing the data to be mapped to TerritorioView
     * @return a new TerritorioView instance populated with data crea the provided Territorio object
     */
    public static TerritorioView crea(Territorio territorio) {
        return TerritorioView.builder()
                .territorioId(territorio.getTerritorioId())
                .nome(territorio.getNome())
                .codCatastale(territorio.getCodCatastale() != null ? territorio.getCodCatastale().getCodice() : null)
                .nomeInglese(territorio.getNomeInglese())
                .build();
    }
}