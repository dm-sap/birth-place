package it.maeci.territory.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.maeci.territory.core.localita.LocalitaView;
import it.maeci.territory.core.localita.service.LocalitaService;
import it.maeci.territory.errors.LocalitaNonTrovataException;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for handling requests related to localities (Localita).
 */
@RestController
@RequestMapping("/private/localita")
@Tag(name = "Gestione delle Località")
@ApiResponse(responseCode = "200", description = "Richiesta completata con successo")
@CrossOrigin
public class LocalitaController {

    private final LocalitaService localitaService;

    public LocalitaController(LocalitaService localitaService) {
        this.localitaService = localitaService;
    }

    /**
     * Retrieves a list of localities associated with a specific territory, filtered by date and name.
     *
     * @param identificativo the unique identifier of the territory
     * @param request        an object containing the date and name used for filtering localities
     * @return a list of {@code LocalitaView} objects representing the localities associated with the specified territory
     * @throws LocalitaNonTrovataException if no localities are found for the provided criteria
     */
    @GetMapping("/territorio/identificativo/{id}")
    @Operation(description = "Ritorna la località associate al Territorio per data e nome")
    public List<LocalitaView> recuperaLocalitaPerTerritorioEData(
            @PathVariable(value = "id") String identificativo,
            @ParameterObject LocalitaRequest request) throws LocalitaNonTrovataException {
        return localitaService.recuperaLocalitaAssociateATerritorio(identificativo, request.getData(), request.getNome());
    }

    /**
     * Retrieves locality data for a specific territory and date using the provided request information.
     *
     * @param request an instance of {@code SingolaLocalitaRequest} containing:
     *                - the identifier of the locality (localitaId),
     *                - the identifier of the territory (territorioId),
     *                - the date (data) for which the locality data is requested.
     * @return an instance of {@code LocalitaView} representing the locality associated with the given request data.
     * @throws LocalitaNonTrovataException if no locality is found matching the provided criteria.
     */
    @GetMapping("")
    @Operation(description = "Ritorna la località per identificativo")
    public LocalitaView recuperaLocalitaPerTerritorioEData(@ParameterObject SingolaLocalitaRequest request) throws LocalitaNonTrovataException {
        return localitaService.recuperaLocalitaPerIdentificativo(request.getLocalitaId(), request.getTerritorioId(), request.getData());
    }

}
