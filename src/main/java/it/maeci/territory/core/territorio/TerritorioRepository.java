package it.maeci.territory.core.territorio;

import it.maeci.territory.core.comune.CodiceCatastale;
import it.maeci.territory.core.territorio.jpa.JpaTerritorioRepositoryDelegate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Territorio entity.
 */
@Repository
public class TerritorioRepository {

    /**
     * A delegate responsible for performing specific data operations related to the Territorio entity.
     * Encapsulates the functionality needed for interacting with the underlying JPA implementation.
     */
    private final JpaTerritorioRepositoryDelegate delegate;

    /**
     * Constructor.
     *
     * @param delegate delegate
     */
    public TerritorioRepository(JpaTerritorioRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Find a Territorio by its ID.
     *
     * @param id the ID of the Territorio
     * @return the Territorio if found, null otherwise
     */
    public Optional<Territorio> findById(String id) {
        return delegate.findById(id);
    }

    /**
     * Finds and retrieves a list of Territorio entities based on the given cadastral code
     * and date of birth. The resulting list contains Territorio entities with cadastral codes
     * matching the provided {@code codCatastale} and valid at the specified {@code dataNascita}.
     *
     * @param codCatastale the cadastral code used to filter Territorio entities
     * @param dataNascita the date used to check the validity of Territorio entities
     * @return a list of Territorio entities that match the given cadastral code and are valid at the specified date
     */
    public List<Territorio> findByCodCatastale(CodiceCatastale codCatastale, LocalDate dataNascita) {
        return delegate.findByCodCatastaleValido(codCatastale.getCodice(), dataNascita);
    }

    /**
     * Find all Territorio entities.
     *
     * @return list of all Territorio entities
     */
    public List<Territorio> findAll(LocalDate dataNascita) {
        return delegate.findAllValid(dataNascita);
    }


}
