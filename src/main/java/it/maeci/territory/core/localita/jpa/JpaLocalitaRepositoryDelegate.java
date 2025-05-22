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
public interface JpaLocalitaRepositoryDelegate extends JpaRepository<Localita, LocalitaId> {
    /**
     * Retrieves a list of Localita entities associated with a given Territorio.
     *
     * @param territorio the Territorio entity used to filter and find associated Localita entities
     * @return a list of Localita entities that are associated with the provided Territorio
     */
    @Query("SELECT l FROM Localita l WHERE l.territorio = :territorio")
    List<Localita> findLocalitaByTerritorio(Territorio territorio);


    /**
     * Retrieves a list of Localita entities filtered by the specified Territorio ID,
     * name, and optionally by birth date. The method matches Localita entities
     * that belong to the Territorio with the provided identifier, that have a name
     * matching the given pattern, and (if provided) whose validity period includes
     * the specified date.
     *
     * @param identificativo the unique identifier of the Territorio to filter by
     * @param nome a string pattern to match against the name of the Localita entities
     * @param dataNascita an optional date to filter Localita entities based on
     *                    their validity period; if null, the date filter is not applied
     * @return a list of Localita entities that match the specified criteria
     */
    @Query(value = "SELECT * " +
            "FROM CODIFICA3.LOCALITA l JOIN CODIFICA3.TERRITORIO t ON t.TERRITORIO_ID=l.TERRITORIO_ID " +
            "WHERE t.TERRITORIO_ID = :identificativo " +
            "  AND ( " +
            "        :dataNascita IS NULL OR " +
            "        (l.DATA_INIZIO_VAL < :dataNascita " +
            "         AND NVL(l.DATA_FINE_VAL, TO_DATE('9999-12-31','YYYY-MM-DD')) > :dataNascita) " +
            "      ) " +
            "  AND l.NOME LIKE :nome", nativeQuery = true)
    List<Localita> findLocalitaPerTerritorioDataENome(String identificativo, String nome, LocalDate dataNascita);

    /**
     * Retrieves a Localita entity based on its unique identifier.
     *
     * @param id id
     * @return Optional<Localita>
     */
    @Query("SELECT l FROM Localita l WHERE l.id=:id")
    Optional<Localita> findByLocalitaId(LocalitaId id);
}
