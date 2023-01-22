package views.vues;

import javafx.scene.control.TreeItem;
import models.capteurs.CapteurTemperatureAbstrait;

public class CapteurTemperatureAbstraitVue extends TreeItem<CapteurTemperatureAbstrait> {

    protected CapteurTemperatureAbstrait capteurTemperatureAbstrait;

    public CapteurTemperatureAbstraitVue(CapteurTemperatureAbstrait capteur) {
        super(capteur);
        capteurTemperatureAbstrait = capteur;
    }
}
