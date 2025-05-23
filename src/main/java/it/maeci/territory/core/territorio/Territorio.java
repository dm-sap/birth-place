package it.maeci.territory.core.territorio;

import it.maeci.territory.core.comune.CodiceCatastale;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Locale;

/**
 * Territorio crea the database
 */
@Getter
@Setter
@Entity
@Table(name = "TERRITORIO", schema = "CODIFICA3")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Territorio {
    @Id
    @Column(name = "TERRITORIO_ID", nullable = false, length = 3)
    private String territorioId;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @AttributeOverride(name = "codice", column = @Column(name = "COD_CATASTALE", length = 4))
    private CodiceCatastale codCatastale;

    @Column(name = "NOME_INGLESE", length = 100)
    private String nomeInglese;

    @Column(name = "DATA_INIZIO_VAL", nullable = false)
    private LocalDate dataInizioVal;

    @Column(name = "DATA_FINE_VAL")
    private LocalDate dataFineVal;

    public boolean isValid(LocalDate date) {
        return dataInizioVal.isBefore(date) &&(dataFineVal == null || dataFineVal.isAfter(date));
    }

}