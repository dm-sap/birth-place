package it.maeci.territory.errors;


import lombok.Getter;


/**
 * This exception is thrown when a specific territory cannot be found based on the provided filter criteria.
 * It extends {@code RisorsaNonTrovataException}, providing additional context specifically
 * for cases where the search pertains to territorial entities.
 * <p>
 * The exception is typically used to signal failures in locating a {@code Territorio} that
 * matches the given filter details, such as a cadastral code.
 */
@Getter
public class DiacriticoNonTrovatoException extends RisorsaNonTrovataException {

    private static final long serialVersionUID = 2162636527341601405L;

    private DiacriticoNonTrovatoException(String filtro) {
        super("Carattere non trovato", filtro);
    }

    /**
     * Creates a new instance of {@code DiacriticoNonTrovatoException} using the provided cadastral code.
     * This method is utilized to signify that a specific diacritical character, associated with the given
     * cadastral code, could not be found.
     */
    public static DiacriticoNonTrovatoException perCarattere(String lettera) {
        return new DiacriticoNonTrovatoException("Carattere diacritico: " + lettera);
    }


    @Override
    public TerritorioError getError() {
        return TerritorioError.DIACRITICO_NON_TROVATO;
    }

}
