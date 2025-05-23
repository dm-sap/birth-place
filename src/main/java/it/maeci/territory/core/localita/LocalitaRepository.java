package it.maeci.territory.core.localita;

import it.maeci.territory.core.localita.jpa.JpaLocalitaRepositoryDelegate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalitaRepository {
    /**
     * Delegate responsible for handling the interaction with the data persistence
     * layer for Localita entities. It provides methods to access and manage
     * Localita data in the database, leveraging the capabilities of the
     * JpaLocalitaRepositoryDelegate interface.
     */
    private final JpaLocalitaRepositoryDelegate delegate;

    public LocalitaRepository(JpaLocalitaRepositoryDelegate delegate) {
        this.delegate = delegate;
    }


    /**
     * Retrieves a Localita entity based on its unique identifier.
     *
     * @param id id
     * @return Optional<Localita>
     */
    public Optional<Localita> findByLocalitaId(LocalitaId id) {
        return delegate.findByLocalitaId(id);
    }

    /**
     * Retrieves a list of Localita objects matching the specified cadastral code, date,
     * and a partial name. The partial name is matched using a prefix search.
     *
     * @param code the cadastral code to identify the territory
     * @param data the date to filter the Localita objects falling within their validity range
     * @param nome the partial name of the Localita to match (prefix search)
     * @return a list of Localita entities that match the specified criteria
     */
    public List<Localita> findLocationsFromTerritorioDataENome(String code, String data, String nome) {
        return delegate.findLocalitaPerTerritorioDataENome(code, nome, data);
    }
}
