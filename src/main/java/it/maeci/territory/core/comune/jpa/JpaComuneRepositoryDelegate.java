package it.maeci.territory.core.comune.jpa;

import it.maeci.territory.core.comune.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaComuneRepositoryDelegate extends JpaRepository<Comune, Long>, JpaComuniRepositoryDelegateCustom {


}
