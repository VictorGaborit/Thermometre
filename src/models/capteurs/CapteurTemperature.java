package models.capteurs;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import models.TachePeriodique;
import models.generateurs.StrategieTemperatureGenere;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

public class CapteurTemperature extends CapteurTemperatureAbstrait{

    private LocalDateTime updatedTime;

    public CapteurTemperature(String nom, double temperature, StrategieTemperatureGenere strategieGeneration, TachePeriodique tachePeriodique) {
        super(nom, temperature);
        setStrategieGeneration(strategieGeneration);
        setFrequence(1);
        setenFonctionnementProperty(true);
        updatedTime = LocalDateTime.now();
        tachePeriodique.ajouterUnCpateurTachePeriodique(this);
    }
    @Override
    public double getTemperature() {return this.temperature.get();}

    private IntegerProperty frequence = new SimpleIntegerProperty();
    public IntegerProperty frequenceProperty() {
        return frequence;
    }
    private void setFrequence(int tick) {this.frequence.set(tick);}
    public int getFrequence() { return frequence.get();}

    private BooleanProperty enFonctionnement = new SimpleBooleanProperty();
    public BooleanProperty enFonctionnementProperty() {
        return enFonctionnement;
    }

    private void setenFonctionnementProperty(boolean activate) {
        this.enFonctionnement.set(activate);
    }
    public boolean getenFonctionnementProperty() { return enFonctionnement.get();}


    @Override
    public ObservableList<CapteurTemperatureAbstrait> getListCapteurs() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    private StrategieTemperatureGenere strategieGeneration;

    public StrategieTemperatureGenere getStrategieGeneration() {
        return strategieGeneration;
    }

    public void setStrategieGeneration(StrategieTemperatureGenere strategieGeneration) {
        this.strategieGeneration = strategieGeneration;
    }

    public void compute() {
        if(Objects.nonNull(strategieGeneration) && Boolean.TRUE.equals(getenFonctionnementProperty())) {
            if(ChronoUnit.SECONDS.between(updatedTime, LocalDateTime.now()) >= getFrequence()) {
                updatedTime = LocalDateTime.now();
                temperatureProperty().setValue(strategieGeneration.compute(temperatureProperty().getValue()));
            }
        }
    }

}
