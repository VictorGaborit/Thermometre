package models;

import models.capteurs.CapteurTemperatureAbstrait;

public abstract class Visualisateur {

    protected CapteurTemperatureAbstrait capteur;

    public Visualisateur(CapteurTemperatureAbstrait capteur){
        this.capteur = capteur;
    }
}
