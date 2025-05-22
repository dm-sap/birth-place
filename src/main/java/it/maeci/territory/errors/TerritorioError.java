package it.maeci.territory.errors;

/**
 * Enum TerritorioError rappresenta una collezione di costanti che identificano
 * possibili tipi di errore relativi alle operazioni sul dominio dei territori.
 * Questa enumerazione può essere utilizzata per categorizzare e gestire situazioni
 * di errore in maniera consistente all'interno dell'applicazione.
 * <p>
 * Questa enumerazione è progettata per essere utilizzata in combinazione con la classe
 * astratta TerritorioException per fornire una gestione strutturata delle eccezioni
 * legate al dominio dei territori.
 */
public enum TerritorioError {

    ERRORE_INTERNO,

    RICHIESTA_NON_VALIDA,

    DATO_OBBLIGATORIO_ASSENTE,

    AUTORIZZAZIONE_NEGATA,

    TERRITORIO_NON_TROVATO,

    COMUNE_NON_TROVATO,
    DIACRITICO_NON_TROVATO,
    LOCALITA_NON_TROVATO, DATO_INCONGRUENTE
}
