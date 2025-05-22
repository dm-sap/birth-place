package it.maeci.territory.core.comune.service;

import it.maeci.territory.core.comune.CodiceCatastale;
import it.maeci.territory.core.comune.Comune;
import it.maeci.territory.core.comune.ComuneRepository;
import it.maeci.territory.core.comune.ComuneView;
import it.maeci.territory.errors.ComuneNonTrovatoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to Comune entities.
 */
@Service
public class ComuneService {

    private final ComuneRepository repository;

    public ComuneService(ComuneRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a ComuneView object based on the provided cadastral code and date.
     * This method searches for a Comune entity using the given codice catastale
     * and constructs a ComuneView representation of the matching entity.
     *
     * @param codeCatastale the cadastral code (codice catastale) identifying the Comune entity
     * @param data          the date used in conjunction with the cadastral code to locate the desired Comune entity
     * @return a ComuneView object representing the found Comune entity
     * @throws ComuneNonTrovatoException if no Comune entity is found with the specified codice catastale
     */
    public ComuneView recuperaComunePerCodiceCatastaleEData(String codeCatastale, String data) throws ComuneNonTrovatoException {
        CodiceCatastale code = CodiceCatastale.parse(codeCatastale);
        LocalDate localDate = data != null ? LocalDate.parse(data) : null;
        List<Comune> comuni = repository.findByCodCatastaleEData(code, localDate);
        if (comuni.isEmpty())
            throw ComuneNonTrovatoException.perCodiceCatastaleInData(code, localDate);

        return ComuneView.crea(comuni.stream()
                .filter(Comune::isValidNow)
                .findFirst()
                .orElse(comuni.get(0)));
    }


    /**
     * Retrieves a list of ComuneView objects that match the specified date and name criteria.
     * This method filters Comune entities based on the provided date and name, converts them
     * into ComuneView representations, and sorts the results by their name.
     *
     * @param data the date used to filter valid Comune entities based on their validity period;
     *             may be null, in which case no date filtering is applied
     * @param nome the name to filter Comune entities; may be null or partial, matching names
     *             starting with the specified value
     * @return a list of ComuneView objects representing filtered and sorted Comune entities
     */
    public List<ComuneView> recuperaComuniEDataENome(String data, String nome) {
        LocalDate localDate = data != null ? LocalDate.parse(data) : null;
        List<Comune> comuni = repository.findAllPerDataENome(localDate, nome);
        return comuni.stream()
                .map(ComuneView::crea)
                .sorted((t1, t2) -> t1.getNome().compareToIgnoreCase(t2.getNome()))
                .collect(Collectors.toList());
    }
}
