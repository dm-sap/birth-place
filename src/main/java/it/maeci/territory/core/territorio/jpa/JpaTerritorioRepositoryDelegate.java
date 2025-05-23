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

}
