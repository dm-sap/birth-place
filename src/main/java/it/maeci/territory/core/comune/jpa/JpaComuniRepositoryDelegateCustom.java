package it.maeci.territory.core.comune.jpa;

import it.maeci.territory.core.comune.Comune;

import java.util.List;

public interface JpaComuniRepositoryDelegateCustom {

    /**
     * Finds a list of Comune entities based on the provided codiceCatastale catastale and date.
     *
     * @param codiceCatastale the codiceCatastale catastale used to filter the Comune entities
     * @param dataNascita     the date used to validate the Comune entities' validity period; can be null
     * @return a list of Comune entities matching the given codiceCatastale catastale and valid for the given date if provided
     */
    List<Comune> findByCodCatastaleEData(String codiceCatastale, String dataNascita);

    /**
     * Retrieves a list of Comune entities that match the specified date and name criteria.
     * The provided date is used to evaluate the validity of the Comune entities based on their
     * validity period, and the name is used as a filtering condition.
     *
     * @param dataNascita the date to evaluate the validity period of the Comune entities; can be null
     *                    to ignore the date filter
     * @param nome        the name pattern to match against the Comune entity names
     * @return a list of Comune entities that match the specified date and name filtering criteria
     */
    List<Comune> findAllPerDataENome(String dataNascita, String nome);
}


