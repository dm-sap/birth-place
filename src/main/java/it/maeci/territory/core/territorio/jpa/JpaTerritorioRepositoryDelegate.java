package it.maeci.territory.core.territorio.jpa;

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
public interface JpaTerritorioRepositoryDelegate extends JpaRepository<Territorio, String>,
        JpaTerritorioRepositoryDelegateCustom {

    @Query(value = "SELECT * " +
            "FROM CODIFICA3.TERRITORIO t " +
            "WHERE ( " +
            "        :data IS NULL OR " +
            "        (t.DATA_INIZIO_VAL < :data " +
            "         AND NVL(t.DATA_FINE_VAL, TO_DATE('9999-12-31','YYYY-MM-DD')) > :data) " +
            "      ) " +
            "  AND t.NOME LIKE :nome", nativeQuery = true)
    List<Territorio> findAllValidWithName(LocalDate data, String nome);
}
