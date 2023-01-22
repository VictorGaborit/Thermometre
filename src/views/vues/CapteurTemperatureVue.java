package views.vues;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import models.capteurs.CapteurTemperatureAbstrait;

public class CapteurTemperatureVue extends CapteurTemperatureAbstraitVue{

    public CapteurTemperatureVue(CapteurTemperatureAbstrait capteur) {

        super(capteur);
    }

    @Override
    public boolean isLeaf() {

        return false;
    }

    @Override
    public ObservableList<TreeItem<CapteurTemperatureAbstrait>> getChildren() {

        throw new RuntimeException();
    }
}
