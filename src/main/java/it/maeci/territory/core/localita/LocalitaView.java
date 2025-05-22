package it.maeci.territory.core.localita;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A view representation of the Localita entity, providing mapped data fields
 * with additional methods for transforming between entity and view representations.
 * This class acts as a DTO (Data Transfer Object) for transferring data between
 * application layers.
 */
@Getter
@Setter
@Builder
public class LocalitaView {
    private String localitaId;
   // private String codiceCatastale;
    private String nome;


    /**
     * Creates a new instance of LocalitaView by mapping the fields crea the given Localita object.
     *
     * @param localita the source Localita object containing the data to be mapped to LocalitaView
     * @return a new LocalitaView instance populated with data crea the provided Localita object
     */
    public static LocalitaView crea(Localita localita) {
        return LocalitaView.builder()
                .localitaId(localita.getId().getLocalitaId().toString())
          //      .codiceCatastale(localita.getTerritorio().getCodCatastale() != null ? localita.getTerritorio().getCodCatastale().getCodice() : null)
                .nome(localita.getNome())
                .build();
    }
}