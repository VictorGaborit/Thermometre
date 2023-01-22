package models;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.capteurs.CapteurTemperature;

import java.util.ArrayList;
import java.util.List;

public class TachePeriodique extends Thread{

    private  CapteurTemperature capteur;
    protected final BooleanProperty arret =  new SimpleBooleanProperty();

    private List<CapteurTemperature> ListCapteurTachePeriodique =  new ArrayList<>();

    public TachePeriodique(){
        setArret(true);
    }


    public BooleanProperty arretProperty(){
        return arret;
    }
    public boolean getArret(){
        return arret.get();
    }
    private void setArret(boolean arret){
        this.arret.set(arret);

        if(arret){
            this.start();
        }
    }
    public void ajouterUnCpateurTachePeriodique(CapteurTemperature capteur ){
        ListCapteurTachePeriodique.add(capteur);
    }
    @Override
    public void run() {
        while (getArret()) {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    ListCapteurTachePeriodique.forEach(e -> e.compute());
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
