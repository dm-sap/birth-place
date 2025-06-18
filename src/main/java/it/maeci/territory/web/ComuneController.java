package it.maeci.territory.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.maeci.territory.core.comune.ComuneView;
import it.maeci.territory.core.comune.service.ComuneService;
import it.maeci.territory.errors.ComuneNonTrovatoException;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/private/comune")
@Tag(name = "Gestione dei Comuni")
@ApiResponse(responseCode = "200", description = "Richiesta completata con successo")
@CrossOrigin
public class ComuneController {

    private final ComuneService comuneService;

    public ComuneController(ComuneService comuneService) {
        this.comuneService = comuneService;
    }

    /**
     * Retrieves the municipality (comune) associated with the provided cadastral code and a specified date.
     *
     * @param codiceCatastale the cadastral code of the municipality to retrieve
     * @param birthdate an object containing the date to filter the municipality's validity
     * @return an object representing the municipality (comune) corresponding to the provided cadastral code
     *         and date, or an appropriate exception if not found
     * @throws ComuneNonTrovatoException if no municipality is found for the provided cadastral code and date
     */
    @GetMapping("/codiceCatastale/{codiceCatastale}")
    @Operation(description = "Ritorna il comune associato al codice catastale e data")
    public Object recuperaComunePerCodiceCatastaleEData(
            @PathVariable(value = "codiceCatastale") String codiceCatastale,
            @ParameterObject BirthdateRequest birthdate) throws ComuneNonTrovatoException {
        return comuneService.recuperaComunePerCodiceCatastaleEData(codiceCatastale, birthdate.getData());
    }

    /**
     * Retrieves a list of municipalities (comuni) filtered by date and name.
     *
     * @param request an object containing filter criteria, including:
     *                - data: the date used to filter valid municipalities based on their validity period
     *                - nome: the name or partial name of the municipalities to filter
     * @return a list of ComuneView objects representing the filtered municipalities
     */
    @GetMapping()
    @Operation(description = "Ritorna tutti i comuni per data e nome")
    public List<ComuneView> recuperaComuni(@ParameterObject ComuniRequest request) {
        return comuneService.recuperaComuniEDataENome(request.getData(), request.getNome());
    }

}
