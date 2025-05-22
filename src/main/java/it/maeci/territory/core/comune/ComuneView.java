package it.maeci.territory.core.comune;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * ComuneView crea the database
 */
@Getter
@Setter
@Builder
public class ComuneView {

    private Long id;
    private String codCatastale;
    private String nome;

    /**
     * Creates a new instance of ComuneView by mapping the fields crea the given Territorio object.
     *
     * @param comune the source Comune object containing the data to be mapped to ComuneView
     * @return a new ComuneView instance populated with data crea the provided Territorio object
     */
    public static ComuneView crea(Comune comune) {
        return ComuneView.builder()
                .id(comune.getComuneId())
                .codCatastale(comune.getCodCatastale() != null ? comune.getCodCatastale().getCodice() : null)
                .nome(comune.getNome())
                .build();
    }
}