package it.maeci.territory.core.comune;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "COMUNE", schema = "CODIFICA3")
public class Comune {
    @Id
    @Column(name = "COMUNE_ID", nullable = false)
    private Long comuneId;

    @AttributeOverride(name = "codice", column = @Column(name = "COD_CATASTALE", nullable = false, length = 6))
    private CodiceCatastale codCatastale;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DATA_INIZIO_VAL", nullable = false)
    private LocalDate dataInizioVal;

    @Column(name = "DATA_FINE_VAL")
    private LocalDate dataFineVal;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "DATA_INS", nullable = false)
    private OffsetDateTime dataIns;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "DATA_AGG")
    private OffsetDateTime dataAgg;

    /**
     * Determines whether the given date falls within the validity period defined
     * by the dataInizioVal (start date) and dataFineVal (end date) of the Comune entity.
     *
     * @param date the date to evaluate against the validity period
     * @return true if the given date is after dataInizioVal and either before dataFineVal
     * or if dataFineVal is not set; false otherwise
     */
    public boolean isValid(LocalDate date) {
        return dataInizioVal.isBefore(date) && (dataFineVal == null || dataFineVal.isAfter(date));
    }

    /**
     * Checks if the current date is within the validity period of the Comune entity.
     * The validity is determined based on whether the current date is before the
     * `dataFineVal` or if `dataFineVal` is not set.
     *
     * @return true if the current date is before `dataFineVal` or if `dataFineVal` is null,
     *         false otherwise
     */
    public boolean isValidNow() {
        return dataFineVal.isAfter(LocalDate.now()) || dataFineVal == null;
    }
}