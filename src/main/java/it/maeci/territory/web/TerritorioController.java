package it.maeci.territory.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.maeci.territory.core.territorio.TerritorioView;
import it.maeci.territory.core.territorio.service.TerritorioService;
import it.maeci.territory.errors.TerritorioNonTrovatoException;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/private/territorio")
@Tag(name = "Gestione dei Territori")
@ApiResponse(responseCode = "200", description = "Richiesta completata con successo")
@CrossOrigin
public class TerritorioController {

    private final TerritorioService territorioService;

    public TerritorioController(TerritorioService territorioService) {
        this.territorioService = territorioService;
    }

    /**
     * Retrieves the territory associated with the provided cadastral code and date.
     *
     * @param codiceCatastale the cadastral code used to identify the territory
     * @param birthdate the object containing the date for filtering the territory
     * @return the {@code TerritorioView} object representing the territory corresponding to the provided cadastral code and date
     * @throws TerritorioNonTrovatoException if no territory is found for the specified cadastral code and date
     */
    @GetMapping("/codiceCatastale/{codiceCatastale}")
    @Operation(description = "Ritorna il territorio associato al codice catastale e data")
    public TerritorioView recuperaTerritorioPerCodiceCatastaleEData(
            @PathVariable(value = "codiceCatastale") String codiceCatastale,
            @ParameterObject BirthdateRequest birthdate) throws TerritorioNonTrovatoException {
        return territorioService.recuperaTerritorioPerCodiceCatastaleEData(codiceCatastale, birthdate.getData());
    }

    /**
     * Retrieves a list of valid territories based on the provided birthdate filter.
     * If the birthdate is not specified, all territories are returned without filtering.
     *
     * @param birthdate an object containing the date used to filter valid territories.
     *                  The date should be in ISO*/
    @GetMapping("/tutti")
    @Operation(description = "Ritorna tutti i territori in funzione della data")
    public List<TerritorioView> recuperaTerritoriValidi(@ParameterObject BirthdateRequest birthdate) {
        return territorioService.recuperaTerritori(birthdate.getData());
    }

    /**
     * Retrieves the territory associated with the provided identifier.
     *
     * @param territorioId the unique identifier of the territory to be retrieved
     * @return the {@code TerritorioView} object representing the territory corresponding to the provided identifier
     * @throws TerritorioNonTrovatoException if no territory is found for the specified identifier
     */
    @GetMapping("/identificativo/{id}")
    @Operation(description = "Recupera un territorio per identificativo")
    public TerritorioView recuperaTerritoriValidi(@PathVariable(value = "id") String territorioId) throws TerritorioNonTrovatoException {
        return territorioService.recuperaTerritorioPerIdentificativo(territorioId);
    }

    /**
     * Retrieves a list of valid territories based on the provided birthdate filter.
     * If the birthdate is not specified, all territories are returned without filtering.
     *
     * @param request an object containing the date used to filter valid territories.
     *                  The date should be in ISO*/
    @GetMapping()
    @Operation(description = "Ritorna tutti i territori in funzione della data e nome")
    public List<TerritorioView> recuperaTerritoriValidiPerNome(@ParameterObject TerritorioRequest request) {
        return territorioService.recuperaTerritorioConNome(request.getData(), request.getNome());
    }
}
