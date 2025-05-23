package it.maeci.territory.core.localita.jpa;

import it.maeci.territory.core.localita.Localita;
import it.maeci.territory.core.territorio.Territorio;

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
        filtraPerIdentificativo(WHERE, territorioId, queryBuilder);
        filtraPerData(AND, dataNascita, queryBuilder);
        filtraPerNome(AND, nome, queryBuilder);

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Territorio.class);

        aggiuntaParametro("dataNascita", dataNascita, query);
        aggiuntaParametro("territorioId", territorioId, query);
        aggiuntaParametro("nome", nome, query);

        return query.getResultList();
    }

    /**
     * Filtra per codice catastale.
     *
     * @param operatore    operatore
     * @param territorioId territorioId
     * @param queryBuilder queryBuilder
     */
    private void filtraPerIdentificativo(String operatore, String territorioId, StringBuilder queryBuilder) {
        if (territorioId != null && !territorioId.isEmpty()) {
            queryBuilder.append(operatore);
            queryBuilder.append(" l.TERRITORIO_ID = :territorioId ");
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
            queryBuilder.append(" (TRUNC(l.DATA_INIZIO_VAL) < TO_DATE(:dataNascita,'YYYY-MM-DD') " +
                    "AND NVL(TRUNC(l.DATA_FINE_VAL), TO_DATE('9999-12-31','YYYY-MM-DD')) > TO_DATE(:dataNascita,'YYYY-MM-DD')) ");
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
            queryBuilder.append(" l.NOME LIKE :nome ");
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


