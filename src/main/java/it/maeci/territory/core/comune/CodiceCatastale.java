package it.maeci.territory.core.comune;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class CodiceCatastale implements Serializable {

    private String codice;

    /**
     * Constructs a CodiceCatastaleTerritorio object with the specified codice value.
     *
     * @param codice the cadastral code associated with the CodiceCatastaleTerritorio object
     */
    private CodiceCatastale(String codice) {
        this.codice = codice;
    }

    /**
     * Parses the given string representation of a Codice Catastale and creates a corresponding
     * CodiceCatastaleTerritorio object.
     *
     * @param codice the string representation of the Codice Catastale to parse
     * @return a new CodiceCatastaleTerritorio object initialized with the parsed
     */
    public static CodiceCatastale parse(String codice) {
        return new CodiceCatastale(codice);

    }

}
