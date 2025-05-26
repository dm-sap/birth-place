package it.maeci.territory.core.localita.service;

import it.maeci.territory.core.localita.Localita;
import it.maeci.territory.core.localita.LocalitaId;
import it.maeci.territory.core.localita.LocalitaRepository;
import it.maeci.territory.core.localita.LocalitaView;
import it.maeci.territory.errors.LocalitaNonTrovataException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for operations related to localities (Localita) and their association with territories (Territorio).
 * This class provides functionality to retrieve locality data that is associated with specific territorial entities.
 * <p>
 * The service interacts with two repositories:
 * - LocalitaRepository: Handles operations related to Localita entities.
 * - TerritorioRepository: Handles operations related to Territorio entities.
 */
@Service
public class LocalitaService {

    private final LocalitaRepository localitaRepository;

    public LocalitaService(LocalitaRepository localitaRepository) {
        this.localitaRepository = localitaRepository;
    }

    /**
     * Retrieves a list of locality views (LocalitaView) associated with a specific territorial entity.
     * This method filters localities based on the provided territory identifier, birth date, and locality name.
     *
     * @param identificativo the unique identifier of the territorial entity
     * @param dataNascita    the birth date associated with the locality, in string format (ISO local date)
     * @param nomeLoc        the name of the locality to filter results
     * @return a list of LocalitaView objects representing localities associated with the given parameters, sorted alphabetically by name
     * @throws LocalitaNonTrovataException if no localities are found matching the given filters
     */
    public List<LocalitaView> recuperaLocalitaAssociateATerritorio(String identificativo, String dataNascita, String nomeLoc)
            throws LocalitaNonTrovataException {
        String nomeLocalita = nomeLoc != null ? nomeLoc.toUpperCase().trim().replace("-", " ") + "%" : "%";
        List<Localita> localita = localitaRepository.findLocationsFromTerritorioDataENome(identificativo, dataNascita, nomeLocalita);
        if (localita.isEmpty()) {
            throw LocalitaNonTrovataException.perTerritorioDataENome(identificativo, dataNascita, nomeLocalita);
        }
        return localita.stream()
                .map(LocalitaView::crea)
                .sorted((t1, t2) -> t1.getNome().compareToIgnoreCase(t2.getNome()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a locality (Localita) identified by its unique identifier.
     *
     * @param locId        locId
     * @param territorioId territorioId
     * @param data         data
     * @return LocalitaView
     * @throws LocalitaNonTrovataException LocalitaNonTrovataException
     */
    public LocalitaView recuperaLocalitaPerIdentificativo(Long locId, String territorioId, String data) throws LocalitaNonTrovataException {
        LocalitaId localitaId = LocalitaId.parse(locId, territorioId);
        Localita localita = localitaRepository.findByLocalitaId(localitaId)
                .orElseThrow(() -> LocalitaNonTrovataException.perIdentificativo(localitaId));
        if (data != null) {
            LocalDate localDate = LocalDate.parse(data);
            if (localita.isValid(localDate))
                throw LocalitaNonTrovataException.perData(localDate);
        }
        return LocalitaView.crea(localita);
    }
}
