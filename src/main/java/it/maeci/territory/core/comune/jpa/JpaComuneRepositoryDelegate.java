package it.maeci.territory.core.comune.jpa;

import it.maeci.territory.core.comune.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface JpaComuneRepositoryDelegate extends JpaRepository<Comune, Long> {

    /**
     * Finds a list of Comune entities based on the provided codiceCatastale catastale and date.
     *
     * @param codiceCatastale the codiceCatastale catastale used to filter the Comune entities
     * @param dataNascita     the date used to validate the Comune entities' validity period; can be null
     * @return a list of Comune entities matching the given codiceCatastale catastale and valid for the given date if provided
     */
    @Query(value = "SELECT * " +
            "FROM CODIFICA3.COMUNE c " +
            "WHERE c.COD_CATASTALE = :codiceCatastale " +
            "  AND ( " +
            "        :dataNascita IS NULL OR " +
            "        (c.DATA_INIZIO_VAL < :dataNascita " +
            "         AND NVL(c.DATA_FINE_VAL, TO_DATE('9999-12-31','YYYY-MM-DD')) > :dataNascita) " +
            "      ) ", nativeQuery = true)
    List<Comune> findByCodCatastaleEData(String codiceCatastale, LocalDate dataNascita);

    /**
     * Retrieves a list of Comune entities that match the specified date and name criteria.
     * The provided date is used to evaluate the validity of the Comune entities based on their
     * validity period, and the name is used as a filtering condition.
     *
     * @param dataNascita the date to evaluate the validity period of the Comune entities; can be null
     *                    to ignore the date filter
     * @param nome the name pattern to match against the Comune entity names
     * @return a list of Comune entities that match the specified date and name filtering criteria
     */
    @Query(value = "SELECT * " +
            "FROM CODIFICA3.COMUNE c " +
            "WHERE ( " +
            "        :dataNascita IS NULL OR " +
            "        (c.DATA_INIZIO_VAL < :dataNascita " +
            "         AND NVL(c.DATA_FINE_VAL, TO_DATE('9999-12-31','YYYY-MM-DD')) > :dataNascita) " +
            "      ) " +
            "AND c.NOME LIKE :nome", nativeQuery = true)
    List<Comune> findAllPerDataENome(LocalDate dataNascita, String nome);
}
