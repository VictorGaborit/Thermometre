package models.capteurs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;

public abstract class CapteurTemperatureAbstrait extends Capteur{

    protected DoubleProperty temperature = new SimpleDoubleProperty();

    public CapteurTemperatureAbstrait(String nom, double temperature) {
        super(nom);
        this.temperature.set(temperature);
    }
    public DoubleProperty temperatureProperty(){
        return temperature;
    }
    private void setTemperature(double temperature){
        this.temperature.set(temperature);
    }
    public abstract double getTemperature();
    public abstract ObservableList<CapteurTemperatureAbstrait> getListCapteurs() throws NoSuchMethodException;


}
