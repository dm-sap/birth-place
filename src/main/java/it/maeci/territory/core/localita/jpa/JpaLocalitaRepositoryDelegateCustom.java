package it.maeci.territory.core.localita.jpa;

import it.maeci.territory.core.localita.Localita;

import java.util.List;

public interface JpaLocalitaRepositoryDelegateCustom {

    /**
     * Retrieves a list of Localita entities that match the specified territorio, nome e dataNascita criteria.
     *
     * @param territorioId territorioId
     * @param nome         nome
     * @param dataNascita  dataNascita
     * @return List<Localita>
     */
    List<Localita> findLocalitaPerTerritorioDataENome(String territorioId, String nome, String dataNascita);
}


