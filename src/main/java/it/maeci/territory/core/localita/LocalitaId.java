package it.maeci.territory.core.localita;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LocalitaId implements Serializable {
    private static final long serialVersionUID = 7755260987622076778L;
    @Column(name = "LOCALITA_ID", nullable = false)
    private Long localitaId;

    @Column(name = "TERRITORIO_ID", nullable = false, length = 3)
    private String territorioId;

    public static LocalitaId parse(Long localitaId, String territorioId) {
        return new LocalitaId(localitaId, territorioId);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LocalitaId entity = (LocalitaId) o;
        return Objects.equals(this.localitaId, entity.localitaId) &&
                Objects.equals(this.territorioId, entity.territorioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localitaId, territorioId);
    }

}