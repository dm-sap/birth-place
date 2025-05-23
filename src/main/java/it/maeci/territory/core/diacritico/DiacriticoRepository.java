package it.maeci.territory.core.diacritico;

import it.maeci.territory.core.diacritico.jpa.JpaDiacriticoRepositoryDelegate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DiacriticoRepository {

    private final JpaDiacriticoRepositoryDelegate delegate;

    public DiacriticoRepository(JpaDiacriticoRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Retrieves a {@link Diacritico} entity by its unique identifier.
     *
     * @param id the unique identifier of the {@link Diacritico} to be retrieved
     * @return an {@link Optional} containing the {@link Diacritico}, if found; otherwise, an empty {@link Optional}
     */
    public Optional<Diacritico> findById(String id) {
        return delegate.findById(id);
    }

    /**
     * Retrieves a {@link Diacritico} entity that matches the specified diacritic letter.
     *
     * @param letter the diacritic letter to be searched for
     * @return an {@link Optional} containing the {@link Diacritico} if a match is found, otherwise an empty {@link Optional}
     */
    public Optional<Diacritico> findByDiacritic(String letter) {
        return delegate.findByDiacritico(letter);
    }

    /**
     * Retrieves all {@link Diacritico} entities from the repository.
     *
     * @return a list of {@link Diacritico} objects representing all diacritical characters in the repository
     */
    public List<Diacritico> findAll() {
        return delegate.findAll();
    }
}
