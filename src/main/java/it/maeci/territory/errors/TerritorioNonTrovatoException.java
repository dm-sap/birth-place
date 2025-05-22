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
public class TerritorioNonTrovatoException extends RisorsaNonTrovataException {

    private static final long serialVersionUID = 2162636527341601405L;

    private TerritorioNonTrovatoException(String filtro) {
        super("Territorio non trovato", filtro);
    }

    /**
     * Errore per codice catastale
     *
     * @param code code
     * @return TerritorioNonTrovatoException
     */
    public static TerritorioNonTrovatoException perCodiceCatastale(CodiceCatastale code) {
        return new TerritorioNonTrovatoException("Codice Catastale: " + code.getCodice());
    }

    /**
     * Errore per data
     *
     * @param localDate localDate
     * @return TerritorioNonTrovatoException
     */
    public static TerritorioNonTrovatoException perData(LocalDate localDate) {
        return new TerritorioNonTrovatoException("Valido in data: " + localDate.toString());
    }

    /**
     * Errore per codice catastale in data
     *
     * @param code      code
     * @param localDate localDate
     * @return TerritorioNonTrovatoException
     */
    public static TerritorioNonTrovatoException perCodiceCatastaleInData(CodiceCatastale code, LocalDate localDate) {
        return new TerritorioNonTrovatoException("Codice Catastale: " + code.getCodice() + " in data: " + localDate);
    }

    /**
     * Errore per identificativo
     *
     * @param territorioId territorioId
     * @return TerritorioNonTrovatoException
     */
    public static TerritorioNonTrovatoException perIdentificativo(String territorioId) {
        return new TerritorioNonTrovatoException("Identificativo: " + territorioId);
    }

    /**
     * Retrieves the specific error associated with this exception.
     *
     * @return the error of type {@code TerritorioError} that provides information
     * about the nature of the exception, in this case {@code TERRITORIO_NON_TROVATO}.
     */
    @Override
    public TerritorioError getError() {
        return TerritorioError.TERRITORIO_NON_TROVATO;
    }

}
