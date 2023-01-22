package models.capteurs;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.generateurs.GenerateurAleatoire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapteurTemperatureVirtuel extends CapteurTemperatureAbstrait{

    private Map<Integer, List<CapteurTemperatureAbstrait>> ListCapteur = new HashMap<>();
    private ObservableList<CapteurTemperatureAbstrait> ListCapteurObserve = FXCollections.observableArrayList();
    public ListProperty<CapteurTemperatureAbstrait> listeCapteur = new SimpleListProperty<>(ListCapteurObserve);

    public CapteurTemperatureVirtuel(String nom, double temperature) {
        super(nom, temperature);
    }

    public List<CapteurTemperatureAbstrait> getCapteur(){
        List<CapteurTemperatureAbstrait> list = new ArrayList<>();
        for(Map.Entry<Integer, List<CapteurTemperatureAbstrait>> valeur : ListCapteur.entrySet()){
            valeur.getValue().forEach(list::add);
        }
        return list;
    }

    public void AjouterUnCapteur(CapteurTemperatureAbstrait capteur, Integer poids){
        if(ListCapteur.containsKey(poids)){
            ListCapteur.get(poids).add(capteur);
            ListCapteurObserve.add(capteur);
        }else {
            ListCapteur.put(poids, new ArrayList<>());
            ListCapteur.get(poids).add(capteur);
            ListCapteurObserve.add(capteur);
        }
    }
    public void supprimerCapteur(CapteurTemperatureAbstrait capteur){
        ListCapteurObserve.remove(capteur);
        for(Map.Entry<Integer, List<CapteurTemperatureAbstrait>> valeur : ListCapteur.entrySet()){
            if(valeur.getValue().contains(capteur)){
                valeur.getValue().remove(capteur);
            }
        }
    }
    private double calculMoyenne(){
        double SommeDesPoids = 0;
        double SommeDesTemperatures = 0;
        double Moyenne = 0;
        for (Map.Entry<Integer, List<CapteurTemperatureAbstrait>> valeur : ListCapteur.entrySet()){
            for (CapteurTemperatureAbstrait capteur : valeur.getValue()){
                SommeDesPoids += valeur.getKey();
                SommeDesTemperatures += (capteur.temperatureProperty().getValue() * valeur.getKey());
            }
        }
        Moyenne = SommeDesTemperatures/SommeDesPoids;
        return Moyenne;
    }
    public void MiseAjourTemperature() {temperatureProperty().setValue(calculMoyenne());}

    @Override
    public double getTemperature() {
        return temperatureProperty().getValue();
    }

    @Override
    public ObservableList<CapteurTemperatureAbstrait> getListCapteurs() throws NoSuchMethodException {
        return ListCapteurObserve;
    }
}
