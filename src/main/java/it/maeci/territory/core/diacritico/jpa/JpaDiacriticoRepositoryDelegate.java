package it.maeci.territory.core.diacritico.jpa;

import it.maeci.territory.core.diacritico.Diacritico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaDiacriticoRepositoryDelegate extends JpaRepository<Diacritico, String> {
    /**
     * Retrieves the ICAO transliteration of a given diacritic letter from the repository.
     *
     * @param letter the diacritic letter to search for
     * @return an {@link Optional} containing the {@link Diacritico} with the corresponding ICAO transliteration,
     *         or an empty {@link Optional} if no match is found
     */
   @Query("SELECT d FROM Diacritico d WHERE d.carDiacritico = :letter")
    Optional<Diacritico> findByDiacritico(String letter);
}
