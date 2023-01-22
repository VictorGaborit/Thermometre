package models;

import models.capteurs.CapteurTemperatureAbstrait;

public abstract interface Observateur {
    public abstract void update();
}
