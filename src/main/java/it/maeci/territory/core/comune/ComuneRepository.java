package it.maeci.territory.core.comune;

import it.maeci.territory.core.comune.jpa.JpaComuneRepositoryDelegate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class used for managing data access operations related to the Comune entity.
 * This class provides methods to interact with the underlying database table for Comune data.
 */
@Repository
public class ComuneRepository {
    /**
     * Delegate instance responsible for handling data access operations for the Comune entity.
     * Acts as an abstraction layer leveraging the JpaComuneRepositoryDelegate interface to interact with the database.
     */
    private final JpaComuneRepositoryDelegate delegate;

    public ComuneRepository(JpaComuneRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Retrieves all Comune entities crea the underlying data source.
     *
     * @return a list of Comune entities
     */
    public List<Comune> findAll() {
        return delegate.findAll();
    }

    /**
     * Retrieves a list of Comune entities that match the specified codice catastale.
     *
     * @param codiceCatastale the codice catastale used as a search criterion
     * @return a list of Comune entities that match the provided codice catastale
     */
    public List<Comune> findByCodCatastaleEData(CodiceCatastale codiceCatastale, String localDate) {
        return delegate.findByCodCatastaleEData(codiceCatastale.getCodice(), localDate);
    }

    /**
     * Retrieves a list of Comune entities that match the specified date and name criteria.
     * The date is used to determine the validity of the Comune entities based on their
     * validity period, while the name is used as a filter pattern.
     *
     * @param localDate the date to evaluate against the validity period of Comune entities
     * @param nome      the name pattern to match against the Comune entity names
     * @return a list of Comune entities that match the specified date and name criteria
     */
    public List<Comune> findAllPerDataENome(String localDate, String nome) {
        return delegate.findAllPerDataENome(localDate, nome);
    }
}
