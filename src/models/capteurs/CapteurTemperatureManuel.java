package models.capteurs;

public class CapteurTemperatureManuel extends CapteurTemperature{

    public CapteurTemperatureManuel(String nom, double temperature) {
        super(nom, temperature, null, null);
    }
    public void setTemperature(double temperature){
        setTemperature(temperature);
    }

}
