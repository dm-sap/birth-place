package it.maeci.territory.core.localita;

import it.maeci.territory.core.territorio.Territorio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "LOCALITA", schema = "CODIFICA3")
public class Localita {
    @EmbeddedId
    private LocalitaId id;

    @MapsId("territorioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TERRITORIO_ID", nullable = false)
    private Territorio territorio;

    @Column(name = "NOME", nullable = false, length = 100)
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
     * Checks if the given date falls within the validity period defined by
     * the `dataInizioVal` and `dataFineVal` properties of the Localita entity.
     *
     * @param date the date to be validated
     * @return true if the date is after `dataInizioVal` and either before
     *         `dataFineVal` or if `dataFineVal` is null; false otherwise
     */
    public boolean isValid(LocalDate date) {
        return dataInizioVal.isBefore(date) &&(dataFineVal == null || dataFineVal.isAfter(date));
    }

}