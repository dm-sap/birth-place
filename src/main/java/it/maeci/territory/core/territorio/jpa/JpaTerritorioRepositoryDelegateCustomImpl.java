package it.maeci.territory.core.territorio.jpa;

import it.maeci.territory.core.territorio.Territorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JpaTerritorioRepositoryDelegateCustomImpl implements JpaTerritorioRepositoryDelegateCustom {
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieves a list of all valid Territorio entities based on the specified date.
     *
     * @param dataNascita dataNascita
     * @return List<Territorio>
     */
    @Override
    public List<Territorio> findAllValid(String dataNascita) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CODIFICA3.TERRITORIO t ");
        filtraPerData(dataNascita, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Territorio.class);

        aggiuntaParametro("dataNascita", dataNascita, query);

        return query.getResultList();
    }

    /**
     * Retrieves a list of Territorio entities based on the specified codiceCatastale and dataNascita.
     *
     * @param codCatastale codCatastale
     * @param dataNascita  dataNascita
     * @return List<Territorio>
     */
    @Override
    public List<Territorio> findByCodCatastaleValido(String codCatastale, String dataNascita) {

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CODIFICA3.TERRITORIO t ");
        filtraPerCodiceCatastale(codCatastale, queryBuilder);
        filtraPerData(dataNascita, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Territorio.class);

        aggiuntaParametro("codiceCatastale", codCatastale, query);
        aggiuntaParametro("dataNascita", dataNascita, query);

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
            queryBuilder.append(" t.COD_CATASTALE = :codiceCatastale ");
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
            queryBuilder.append(" (TRUNC(t.DATA_INIZIO_VAL) < TO_DATE(:dataNascita,'YYYY-MM-DD') " +
                    "AND NVL(TRUNC(t.DATA_FINE_VAL), TO_DATE('9999-12-31','YYYY-MM-DD')) > TO_DATE(:dataNascita,'YYYY-MM-DD')) ");
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


