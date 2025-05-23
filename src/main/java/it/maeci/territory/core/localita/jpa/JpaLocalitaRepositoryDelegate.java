package it.maeci.territory.core.localita.jpa;

import it.maeci.territory.core.comune.CodiceCatastale;
import it.maeci.territory.core.localita.Localita;
import it.maeci.territory.core.localita.LocalitaId;
import it.maeci.territory.core.territorio.Territorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * JpaLocalitaRepositoryDelegate is a repository interface for managing
 * Localita entities. It extends the JpaRepository interface, providing
 * methods for basic CRUD operations and allowing additional query
 * methods to be defined.
 */
public interface JpaLocalitaRepositoryDelegate extends JpaRepository<Localita, LocalitaId>, JpaLocalitaRepositoryDelegateCustom {

    /**
     * Retrieves a Localita entity based on its unique identifier.
     *
     * @param id id
     * @return Optional<Localita>
     */
    @Query("SELECT l FROM Localita l WHERE l.id=:id")
    Optional<Localita> findByLocalitaId(LocalitaId id);
}
