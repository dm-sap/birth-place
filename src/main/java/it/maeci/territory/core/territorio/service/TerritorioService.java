package it.maeci.territory.core.territorio.service;

import it.maeci.territory.core.comune.CodiceCatastale;
import it.maeci.territory.core.territorio.Territorio;
import it.maeci.territory.core.territorio.TerritorioRepository;
import it.maeci.territory.core.territorio.TerritorioView;
import it.maeci.territory.errors.TerritorioError;
import it.maeci.territory.errors.TerritorioNonTrovatoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to Territorio entities.
 */
@Service
public class TerritorioService {

    private final TerritorioRepository repository;

    public TerritorioService(TerritorioRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a specific territory represented as a {@code TerritorioView} object based on the provided
     * cadastral code and an optional date filter.
     *
     * @param codeCatastale a string representing the cadastral code of the desired territory; cannot be null
     * @param dataNascita an optional date represented as a string in ISO-8601 format (e.g., "yyyy-MM-dd").
     *                    Used to filter territories that are valid on this date; can be null.
     * @return a {@code TerritorioView} object representing the matching territory
     * @throws TerritorioNonTrovatoException if no territory is found for the given cadastral code, or if
     *                                       no valid territory is found for the given date
     */
    public TerritorioView recuperaTerritorioPerCodiceCatastaleEData(String codeCatastale, String dataNascita) throws TerritorioNonTrovatoException {
        CodiceCatastale code = CodiceCatastale.parse(codeCatastale);
        LocalDate localDate = dataNascita != null ? LocalDate.parse(dataNascita) : null;
        List<Territorio> territori = repository.findByCodCatastale(code, localDate);
        if (territori.isEmpty())
            throw TerritorioNonTrovatoException.perCodiceCatastaleInData(code, localDate);
        return TerritorioView.crea(territori.stream()
                .findFirst()
                .orElse(territori.get(0)));
    }

    /**
     * Retrieves a list of all territories represented as {@code TerritorioView} objects.
     * Optionally, filters the territories based on their validity with respect to a given date.
     *
     * @param dataNascita an optional date filter represented as a string in ISO-8601 format (e.g., "yyyy-MM-dd").
     *                    If provided, only territories valid on this date are returned. If null, no date filtering is applied.
     * @return a list of {@code TerritorioView} objects representing the filtered or unfiltered territories
     */
    public List<TerritorioView> recuperaTerritori(String dataNascita) {
        LocalDate localDate = dataNascita != null ? LocalDate.parse(dataNascita) : null;
        List<Territorio> territori = repository.findAll(localDate);
        return territori.stream()
                .map(TerritorioView::crea)
                .sorted((t1, t2) -> t1.getNome().compareToIgnoreCase(t2.getNome()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific territory represented as a {@code TerritorioView} object based on the provided
     * identifier.
     *
     * @param territorioId a string representing the unique identifier of the desired territory; cannot be null
     * @return a {@code TerritorioView} object representing the territory matching the provided identifier
     * @throws TerritorioNonTrovatoException if no territory is found for the given identifier
     */
    public TerritorioView recuperaTerritorioPerIdentificativo(String territorioId) throws TerritorioNonTrovatoException {
        Territorio territorio = repository.findById(territorioId)
                .orElseThrow(()->TerritorioNonTrovatoException.perIdentificativo(territorioId));
        return TerritorioView.crea(territorio);
    }
}
