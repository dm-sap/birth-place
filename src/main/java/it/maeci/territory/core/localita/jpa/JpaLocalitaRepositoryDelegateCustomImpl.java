package it.maeci.territory.core.localita.jpa;

import it.maeci.territory.core.localita.Localita;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JpaLocalitaRepositoryDelegateCustomImpl implements JpaLocalitaRepositoryDelegateCustom {
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Localita> findLocalitaPerTerritorioDataENome(String territorioId, String nome, String dataNascita) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CODIFICA3.LOCALITA l ");
        filtraPerIdentificativo(territorioId, queryBuilder);
        filtraPerData(dataNascita, queryBuilder);
        filtraPerNome(nome, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Localita.class);

        aggiuntaParametro("dataNascita", dataNascita, query);
        aggiuntaParametro("territorioId", territorioId, query);
        aggiuntaParametro("nome", nome, query);

        return query.getResultList();
    }

    /**
     * Filtra per codice catastale.
     *
     * @param territorioId territorioId
     * @param queryBuilder queryBuilder
     */
    private void filtraPerIdentificativo(String territorioId, StringBuilder queryBuilder) {
        if (territorioId != null && !territorioId.isEmpty()) {
            operatore(queryBuilder);
            queryBuilder.append(" l.TERRITORIO_ID = :territorioId ");
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
            queryBuilder.append(" (TRUNC(l.DATA_INIZIO_VAL) < TO_DATE(:dataNascita,'YYYY-MM-DD') " +
                    "AND NVL(TRUNC(l.DATA_FINE_VAL), TO_DATE('9999-12-31','YYYY-MM-DD')) > TO_DATE(:dataNascita,'YYYY-MM-DD')) ");
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
            queryBuilder.append(" UPPER(REPLACE(l.NOME, '-', ' ')) LIKE :nome ");
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


