package it.maeci.territory.core.comune.jpa;

import it.maeci.territory.core.comune.Comune;
import it.maeci.territory.core.localita.Localita;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JpaComuniRepositoryDelegateCustomImpl implements JpaComuniRepositoryDelegateCustom {
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Comune> findByCodCatastaleEData(String codiceCatastale, String dataNascita) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CODIFICA3.COMUNE c ");
        filtraPerCodiceCatastale(WHERE, codiceCatastale, queryBuilder);
        filtraPerData(AND, dataNascita, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Comune.class);

        aggiuntaParametro("codiceCatastale", codiceCatastale, query);
        aggiuntaParametro("dataNascita", dataNascita, query);

        return query.getResultList();
    }

    @Override
    public List<Comune> findAllPerDataENome(String dataNascita, String nome) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CODIFICA3.COMUNE c ");
        filtraPerData(WHERE, dataNascita, queryBuilder);
        filtraPerNome(AND, nome, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Comune.class);

        aggiuntaParametro("dataNascita", dataNascita, query);
        aggiuntaParametro("nome", nome, query);

        return query.getResultList();
    }


    /**
     * Filtra per codice catastale.
     *
     * @param operatore       operatore
     * @param codiceCatastale codiceCatastale
     * @param queryBuilder    queryBuilder
     */
    private void filtraPerCodiceCatastale(String operatore, String codiceCatastale, StringBuilder queryBuilder) {
        if (codiceCatastale != null && !codiceCatastale.isEmpty()) {
            queryBuilder.append(operatore);
            queryBuilder.append(" c.COD_CATASTALE = :codiceCatastale ");
        }
    }

    /**
     * Filtra per data.
     *
     * @param operatore    operatore
     * @param data         data
     * @param queryBuilder queryBuilder
     */
    private void filtraPerData(String operatore, String data, StringBuilder queryBuilder) {
        if (data != null && !data.isEmpty()) {
            queryBuilder.append(operatore);
            queryBuilder.append(" (TRUNC(c.DATA_INIZIO_VAL) < TO_DATE(:dataNascita,'YYYY-MM-DD') " +
                    "AND NVL(TRUNC(c.DATA_FINE_VAL), TO_DATE('9999-12-31','YYYY-MM-DD')) > TO_DATE(:dataNascita,'YYYY-MM-DD')) ");
        }
    }

    /**
     * Filtra per nome.
     *
     * @param operatore    operatore
     * @param nome         nome
     * @param queryBuilder queryBuilder
     */
    private void filtraPerNome(String operatore, String nome, StringBuilder queryBuilder) {
        if (nome != null && !nome.isEmpty()) {
            queryBuilder.append(operatore);
            queryBuilder.append(" c.NOME LIKE :nome ");
        }
    }

    /**
     * Aggiunta parametro.
     *
     * @param parameterName  parameterName
     * @param parameterInput parameterInput
     * @param query          query
     */
    public static void aggiuntaParametro(String parameterName, String parameterInput, Query query) {
        if (parameterInput != null && !parameterInput.isEmpty()) {
            query.setParameter(parameterName, parameterInput);
        }
    }


}


