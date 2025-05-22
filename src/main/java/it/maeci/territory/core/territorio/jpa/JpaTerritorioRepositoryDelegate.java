package it.maeci.territory.core.territorio.jpa;

import it.maeci.territory.core.comune.CodiceCatastale;
import it.maeci.territory.core.territorio.Territorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * JpaTerritorioRepositoryDelegate is an interface that extends JpaRepository
 * to provide CRUD operations and additional JPA-related functionalities
 * for the Territorio entity, identified by a String as its primary key.
 * This interface serves as a delegate for database operations on the
 * Territorio entity and integrates with Spring Data JPA to support
 * data access abstraction.
 */
public interface JpaTerritorioRepositoryDelegate extends JpaRepository<Territorio, String> {

    /**
     * Retrieves a list of Territorio entities based on the specified codiceCatastale and dataNascita.
     * If dataNascita is provided, only entities that were valid on the specified date are returned,
     * meaning the date falls after the entity's DATA_INIZIO_VAL and before its DATA_FINE_VAL (if specified).
     * If dataNascita is null, all entities with the specified codiceCatastale are returned.
     *
     * @param codCatastale the codiceCatastale used to filter the Territorio entities (cannot be null).
     * @param dataNascita the date used to filter valid Territorio entities. If null, the filter on validity is not applied.
     * @return a list of Territorio entities that match the specified codiceCatastale and validity criteria.
     */
    @Query(value = "SELECT * " +
            "FROM CODIFICA3.TERRITORIO t " +
            "WHERE t.COD_CATASTALE = :codCatastale " +
            "  AND ( " +
            "        :dataNascita IS NULL OR " +
            "        (t.DATA_INIZIO_VAL < :dataNascita " +
            "         AND NVL(t.DATA_FINE_VAL, TO_DATE('9999-12-31','YYYY-MM-DD')) > :dataNascita) " +
            "      ) ", nativeQuery = true)
    List<Territorio> findByCodCatastaleValido(String codCatastale, LocalDate dataNascita);

    /**
     * Retrieves a list of all valid Territorio entities based on the specified date.
     * A Territorio entity is considered valid if the specified date falls after
     * its DATA_INIZIO_VAL and before its DATA_FINE_VAL (if specified). If no date
     * is provided, all Territorio entities are considered valid.
     *
     * @param dataNascita the date used to filter valid Territorio entities.
     *                    If null, all entities are returned.
     * @return a list of valid Territorio entities that satisfy the date criteria.
     */
    @Query(value = "SELECT * FROM CODIFICA3.TERRITORIO t WHERE ( " +
            "        :dataNascita IS NULL OR " +
            "        (t.DATA_INIZIO_VAL < :dataNascita " +
            "         AND NVL(t.DATA_FINE_VAL, TO_DATE('9999-12-31','YYYY-MM-DD')) > :dataNascita) " +
            "      ) ", nativeQuery = true)
    List<Territorio> findAllValid(LocalDate dataNascita);
}
