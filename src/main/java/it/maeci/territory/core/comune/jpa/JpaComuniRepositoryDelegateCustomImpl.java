package it.maeci.territory.core.comune.jpa;

import it.maeci.territory.core.comune.Comune;

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
        filtraPerCodiceCatastale(codiceCatastale, queryBuilder);
        filtraPerData(dataNascita, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Comune.class);

        aggiuntaParametro("codiceCatastale", codiceCatastale, query);
        aggiuntaParametro("dataNascita", dataNascita, query);

        return query.getResultList();
    }

    @Override
    public List<Comune> findAllPerDataENome(String dataNascita, String nome) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CODIFICA3.COMUNE c ");
        filtraPerData(dataNascita, queryBuilder);
        filtraPerNome(nome, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Comune.class);

        aggiuntaParametro("dataNascita", dataNascita, query);
        aggiuntaParametro("nome", nome, query);

        return query.getResultList();
    }


    /**
     * Filtra per codice catastale.
     *
     * @param codiceCatastale codiceCatastale
     * @param queryBuilder    queryBuilder
     */
    private void filtraPerCodiceCatastale(String codiceCatastale, StringBuilder queryBuilder) {
        if (codiceCatastale != null && !codiceCatastale.isEmpty()) {
            operatore(queryBuilder);
            queryBuilder.append(" c.COD_CATASTALE = :codiceCatastale ");
        }
    }

    /**
     * Filtra per data.
     *
     * @param data         data
     * @param queryBuilder queryBuilder
     */
    private void filtraPerData(String data, StringBuilder queryBuilder) {
        if (data != null && !data.isEmpty()) {
            operatore(queryBuilder);
            queryBuilder.append(" (TRUNC(c.DATA_INIZIO_VAL) < TO_DATE(:dataNascita,'YYYY-MM-DD') " +
                    "AND NVL(TRUNC(c.DATA_FINE_VAL), TO_DATE('9999-12-31','YYYY-MM-DD')) > TO_DATE(:dataNascita,'YYYY-MM-DD')) ");
        }
    }

    /**
     * Filtra per nome.
     *
     * @param nome         nome
     * @param queryBuilder queryBuilder
     */
    private void filtraPerNome(String nome, StringBuilder queryBuilder) {
        if (nome != null && !nome.isEmpty()) {
            operatore(queryBuilder);
            queryBuilder.append(" UPPER(REPLACE(c.NOME, '-', ' ')) LIKE :nome ");
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

    /**
     * Appends the appropriate query operator ("WHERE" or "AND") to the provided
     * {@code queryBuilder} based on its current content. If the query already
     * contains the "WHERE" clause, the method adds "AND"; otherwise, it adds "WHERE".
     *
     * @param queryBuilder the {@code StringBuilder} instance representing the query
     *                     being constructed
     */
    private static void operatore(StringBuilder queryBuilder) {
        if (queryBuilder.toString().contains(WHERE))
            queryBuilder.append(AND);
        else
            queryBuilder.append(WHERE);
    }

}


