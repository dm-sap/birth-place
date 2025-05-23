package it.maeci.territory.core.diacritico;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiacriticoView {
    private String carDiacritico;
    private String traslIcao;

    /**
     * Creates a new instance of {@link DiacriticoView} by mapping the properties from the given {@link Diacritico}.
     *
     * @param diacritico the {@link Diacritico} object whose properties are used to create the view representation
     * @return a new {@link DiacriticoView} object populated with the values from the given {@link Diacritico}
     */
    public static DiacriticoView crea(Diacritico diacritico) {
        return DiacriticoView.builder()
                .carDiacritico(diacritico.getCarDiacritico())
                .traslIcao(diacritico.getTraslIcao())
                .build();
    }
}