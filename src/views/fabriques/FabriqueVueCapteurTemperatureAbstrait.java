package views.fabriques;

import javafx.collections.ListChangeListener;
import models.capteurs.CapteurTemperature;
import models.capteurs.CapteurTemperatureAbstrait;
import models.generateurs.StrategieTemperatureGenere;
import views.vues.CapteurTemperatureAbstraitVue;

public class FabriqueVueCapteurTemperatureAbstrait {

    public static CapteurTemperatureAbstraitVue from(CapteurTemperatureAbstrait root) {
        CapteurTemperatureAbstraitVue retour = new CapteurTemperatureAbstraitVue(root);

        plonger(root, retour);

        try {
            root.getListCapteurs().addListener(new ListChangeListener<CapteurTemperatureAbstrait>() {
                @Override
                public void onChanged(Change<? extends CapteurTemperatureAbstrait> c) {
                    while(c.next()) {
                        c.getAddedSubList().forEach(o -> {
                            retour.getChildren().add(FabriqueVueCapteurTemperatureAbstrait.from(o));
                        });
                    }
                    plonger(root, retour);
                }
            });
        } catch (NoSuchMethodException e) {}

        return retour;
    }

    public static void plonger(CapteurTemperatureAbstrait root, CapteurTemperatureAbstraitVue retour) {
        try {
            root.getListCapteurs().forEach(e -> {
                retour.getChildren().add(FabriqueVueCapteurTemperatureAbstrait.from(e));
            });
        } catch (NoSuchMethodException e) {}

    }
}
