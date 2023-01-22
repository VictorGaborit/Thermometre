package views.vues;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import models.capteurs.CapteurTemperatureAbstrait;

public class CapteurTemperatureVirtuelVue extends CapteurTemperatureAbstraitVue{

    private ObservableList<TreeItem<CapteurTemperatureAbstrait>> observableListCapteurtemperatureAbstrait = FXCollections.observableArrayList();

    public CapteurTemperatureVirtuelVue(CapteurTemperatureAbstrait capteur) {
        super(capteur);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public ObservableList<TreeItem<CapteurTemperatureAbstrait>> getChildren() {
        System.out.println(observableListCapteurtemperatureAbstrait.stream().count());
        return observableListCapteurtemperatureAbstrait;
    }
}
