package it.maeci.territory.errors;

import lombok.Getter;

import java.util.Date;

/**
 * Risposta di errore con gestione base degli errori di richiesta
 * 
 * @author Flavio Giannini
 *
 */
@Getter
public class ErroreServizioResponse {

	private long timestamp;
	private String codice;
	private String errore;
	private String dettaglio;
	private Integer status;

	@SuppressWarnings("unused")
	private ErroreServizioResponse() {
	}

	/**
	 * Inizializzazione
	 * 
	 * @param feaError feaError
	 * @param errore errore
	 * @param dettaglio dettaglio
	 * @param status status
	 */
	public ErroreServizioResponse(TerritorioError feaError, String errore, String dettaglio, Integer status) {
		super();
		this.timestamp = new Date().getTime();
		this.codice = "FEA_ERROR:" + feaError.name().toUpperCase();
		this.errore = errore;
		this.dettaglio = dettaglio;
		this.status = status;
	}

}
