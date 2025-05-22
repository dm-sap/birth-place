package it.maeci.territory.errors;


import it.maeci.territory.core.localita.LocalitaId;
import lombok.Getter;

import java.time.LocalDate;

/**
 * The {@code LocalitaNonTrovataException} class is a specific exception type that
 * extends {@code RisorsaNonTrovataException}. It is designed to signal that a
 * specific locality could not be found based on a provided filter.
 * <p>
 * This exception is typically used in contexts where operations related to
 * territorial or locality data fail due to the absence of the requested resource.
 */
@Getter
public class LocalitaNonTrovataException extends RisorsaNonTrovataException {

    private static final long serialVersionUID = 2162636527341601405L;

    private LocalitaNonTrovataException(String filtro) {
        super("Località non trovata", filtro);
    }

    /**
     * Creates a new instance of {@code LocalitaNonTrovataException} using the provided identifier.
     * This method is utilized to indicate that a locality associated with the specified identifier
     * could not be found.
     *
     * @param id the {@code Long} representing the identifier of the locality
     * @return an instance of {@code LocalitaNonTrovataException} constructed with the provided identifier
     */
    public static LocalitaNonTrovataException perIdentificativo(LocalitaId id) {
        return new LocalitaNonTrovataException("Identificativo località: " + id.getLocalitaId() +
                ", Identificativo Territorio: " + id.getTerritorioId());
    }

    /**
     * Creates a new instance of {@code LocalitaNonTrovataException} using the provided date.
     * This method is used to indicate that a locality could not be found for the specified date.
     *
     * @param localDate the {@code LocalDate} representing the date for which the search was conducted
     * @return an instance of {@code LocalitaNonTrovataException} constructed with a message
     * containing the specified date
     */
    public static LocalitaNonTrovataException perData(LocalDate localDate) {
        return new LocalitaNonTrovataException("Valida in data: " + localDate.toString());
    }

    /**
     * Creates a new instance of {@code LocalitaNonTrovataException} using the provided
     * territory identifier, date, and locality name.
     * This method is used to indicate that a locality could not be found based
     * on the given parameters.
     *
     * @param identificativo the identifier of the territory
     * @param data the date for which the locality was searched
     * @param nomeLocalita the name of the locality searched for
     * @return an instance of {@code LocalitaNonTrovataException} populated with the
     *         provided parameters
     */
    public static LocalitaNonTrovataException perTerritorioDataENome(String identificativo, LocalDate data, String nomeLocalita) {
        return new LocalitaNonTrovataException("Valido per identificativo territorio :" + identificativo + " ed in data: " + data + " e nome: " + nomeLocalita);

    }

    /**
     * Retrieves the specific error associated with the exception.
     *
     * @return the error of type TerritorioError indicating that the locality
     * was not found.
     */
    @Override
    public TerritorioError getError() {
        return TerritorioError.LOCALITA_NON_TROVATO;
    }

}
