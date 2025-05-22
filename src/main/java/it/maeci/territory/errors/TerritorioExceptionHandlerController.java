package it.maeci.territory.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler controller for managing and handling exceptions related to territory operations.
 * This class centralizes exception handling logic using @ControllerAdvice to provide consistent
 * error responses for specific exceptions related to territorial entities.
 */
@ControllerAdvice
public class TerritorioExceptionHandlerController {

    @ExceptionHandler(value = {TerritorioNonTrovatoException.class})
    protected ResponseEntity<ErroreServizioResponse> handleTerritorioNonTrovatoException(
            TerritorioNonTrovatoException ex) {
        return new ResponseEntity<>(
                new ErroreServizioResponse(ex.getError(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DiacriticoNonTrovatoException.class})
    protected ResponseEntity<ErroreServizioResponse> handleDiacriticoNonTrovatoException(
            DiacriticoNonTrovatoException ex) {
        return new ResponseEntity<>(
                new ErroreServizioResponse(ex.getError(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ComuneNonTrovatoException.class})
    protected ResponseEntity<ErroreServizioResponse> handleComuneNonTrovatoException(
            ComuneNonTrovatoException ex) {
        return new ResponseEntity<>(
                new ErroreServizioResponse(ex.getError(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LocalitaNonTrovataException.class})
    protected ResponseEntity<ErroreServizioResponse> handleLocalitaNonTrovatoException(
            LocalitaNonTrovataException ex) {
        return new ResponseEntity<>(
                new ErroreServizioResponse(ex.getError(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }
}
