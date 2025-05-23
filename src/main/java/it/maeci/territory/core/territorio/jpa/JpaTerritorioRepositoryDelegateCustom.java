package it.maeci.territory.core.territorio.jpa;

import it.maeci.territory.core.territorio.Territorio;

import java.util.List;

public interface JpaTerritorioRepositoryDelegateCustom {
    /**
     * Retrieves a list of all valid Territorio entities based on the specified date.
     *
     * @param dataNascita dataNascita
     * @return List<Territorio>
     */
    List<Territorio> findAllValid(String dataNascita);

    /**
     * Retrieves a list of Territorio entities based on the specified codiceCatastale and dataNascita.
     *
     * @param codCatastale codCatastale
     * @param dataNascita  dataNascita
     * @return List<Territorio>
     */
    List<Territorio> findByCodCatastaleValido(String codCatastale, String dataNascita);
}


