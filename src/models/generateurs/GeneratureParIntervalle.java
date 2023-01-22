package models.generateurs;

import java.util.Random;

public class GeneratureParIntervalle implements StrategieTemperatureGenere{
    private final double valeurIntervalleMax;
    private final double valeurIntervalMin;

    public GeneratureParIntervalle(double valMaxInterval, double valMinInterval){
        valeurIntervalleMax = valMaxInterval;
        valeurIntervalMin = valMinInterval;
    }
    @Override
    public double compute(double temperature) {
        double temperatureGenere;
        Random random = new Random();
        temperatureGenere = valeurIntervalMin + random.nextDouble() * (valeurIntervalleMax - valeurIntervalMin);
        return temperatureGenere;
    }
}
