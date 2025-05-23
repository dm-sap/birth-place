package it.maeci.territory.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.maeci.territory.core.diacritico.DiacriticoView;
import it.maeci.territory.core.diacritico.service.DiacriticoService;
import it.maeci.territory.errors.DiacriticoNonTrovatoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The DiacriticoController class provides RESTful API endpoints for managing diacritical characters.
 * It enables the retrieval of transliterations for specific diacritical characters through dedicated operations.
 * This controller serves as the entry point for handling client requests related to diacritical character transliterations.
 * <p>
 * The controller is annotated with Spring's {@code @RestController} to denote that it handles HTTP requests
 * and returns data as JSON or other formats. It is mapped to the base path "/private/diacritico".
 * Additionally, OpenAPI annotations are used for API documentation purposes.
 */
@RestController
@RequestMapping("/private/diacritico")
@Tag(name = "Gestione dei Caratteri Diacritici")
@ApiResponse(responseCode = "200", description = "Richiesta completata con successo")
public class DiacriticoController {

    private final DiacriticoService diacriticoService;

    public DiacriticoController(DiacriticoService diacriticoService) {
        this.diacriticoService = diacriticoService;
    }

    /**
     * Retrieves the transliteration of a given diacritical character.
     *
     * @param lettera the diacritical character for which the transliteration needs to be retrieved
     * @return a {@link DiacriticoView} object containing information about the transliteration of the given character
     * @throws DiacriticoNonTrovatoException if the specified diacritical character is not found
     */
    @GetMapping("/carattere/{carattere}")
    @Operation(description = "Ritorna la traslitterazione del carattere diacritico")
    public DiacriticoView recuperaTraslitterazione(@PathVariable(value = "carattere") String lettera)
            throws DiacriticoNonTrovatoException {
        return diacriticoService.recuperaTraslitterazione(lettera);
    }

    /**
     * Retrieves the transliterations of all diacritical characters.
     *
     * @return a list of {@link DiacriticoView} objects representing all diacritical characters with their transliterations
     */
    @GetMapping("/tutti")
    @Operation(description = "Ritorna la traslitterazione di tutti i caratteri diacritici")
    public List<DiacriticoView> recuperaTutti() {
        return diacriticoService.recuperaTutti();
    }

}
