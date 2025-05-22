package it.maeci.territory.core.diacritico.service;

import it.maeci.territory.core.diacritico.Diacritico;
import it.maeci.territory.core.diacritico.DiacriticoRepository;
import it.maeci.territory.core.diacritico.DiacriticoView;
import it.maeci.territory.errors.DiacriticoNonTrovatoException;
import org.springframework.stereotype.Service;

@Service
public class DiacriticoService {

    private final DiacriticoRepository repository;

    public DiacriticoService(DiacriticoRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves the transliteration view representation of a diacritical character.
     *
     * @param lettera the diacritical character to be transliterated
     * @return a {@link DiacriticoView} object representing the transliteration of the given character
     * @throws DiacriticoNonTrovatoException if the specified diacritical character cannot be found
     */
    public DiacriticoView recuperaTraslitterazione(String lettera) throws DiacriticoNonTrovatoException {
        Diacritico diacritico = repository.findByDiacritic(lettera)
                .orElseThrow(() -> DiacriticoNonTrovatoException.perCarattere(lettera));
        return DiacriticoView.crea(diacritico);
    }
}
