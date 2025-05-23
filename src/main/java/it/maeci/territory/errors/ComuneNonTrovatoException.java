package it.maeci.territory.errors;


import it.maeci.territory.core.comune.CodiceCatastale;
import lombok.Getter;

import java.time.LocalDate;


/**
 * This exception is thrown when a specific territory cannot be found based on the provided filter criteria.
 * It extends {@code RisorsaNonTrovataException}, providing additional context specifically
 * for cases where the search pertains to territorial entities.
 * <p>
 * The exception is typically used to signal failures in locating a {@code Territorio} that
 * matches the given filter details, such as a cadastral code.
 */
@Getter
public class ComuneNonTrovatoException extends RisorsaNonTrovataException {

    private static final long serialVersionUID = 2162636527341601405L;

    private ComuneNonTrovatoException(String filtro) {
        super("Comune non trovato", filtro);
    }

    /**
     * Creates a new instance of {@code ComuneNonTrovatoException} using the specified cadastral code.
     * This method is used to indicate that a territory associated with the given cadastral code
     * could not be found.
     *
     * @param code the {@code CodiceCatastaleTerritorio} used to identify the territory
     * @return an instance of {@code ComuneNonTrovatoException} constructed with the provided cadastral code
     */
    public static ComuneNonTrovatoException perCodiceCatastale(CodiceCatastale code) {
        return new ComuneNonTrovatoException("Codice Catastale: " + code.getCodice());
    }

    /**
     * Creates a new instance of {@code ComuneNonTrovatoException} using the provided date.
     * This method is utilized to indicate that a territory could not be found for
     * the specified date.
     *
     * @param localDate the {@code LocalDate} representing the date for which the
     *                  search was conducted
     * @return an instance of {@code ComuneNonTrovatoException} constructed with a message
     * containing the specified date
     */
    public static ComuneNonTrovatoException perData(LocalDate localDate) {
        return new ComuneNonTrovatoException("Valido in data: " + localDate.toString());
    }

    /**
     * Creates an instance of {@code ComuneNonTrovatoException} using the specified cadastral code
     * and date. This method is used to indicate that a territory associated with the given cadastral
     * code and valid on the provided date could not be found.
     *
     * @param code the {@code CodiceCatastale} used to identify the territory
     * @param localDate the {@code LocalDate} representing the date for which the search was conducted
     * @return an instance of {@code ComuneNonTrovatoException} containing the cadastral code and date
     */
    public static ComuneNonTrovatoException perCodiceCatastaleInData(CodiceCatastale code, String localDate) {
        return new ComuneNonTrovatoException("Codice catastale: " + code.getCodice() + " in data: " + localDate);
    }


    @Override
    public TerritorioError getError() {
        return TerritorioError.COMUNE_NON_TROVATO;
    }

}
