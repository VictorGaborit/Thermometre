package models.generateurs;

import java.util.Random;

public class GenerateurAleatoire implements StrategieTemperatureGenere {


    @Override
    public double compute(double temperature) {
        Random random = new Random();
        final int minTemperatureEnFrance = -40;
        final int maxTemeperatureEnFrance = 55;
        double temperatureGenere;
        temperatureGenere = minTemperatureEnFrance + random.nextDouble() * (maxTemeperatureEnFrance - minTemperatureEnFrance);
        return temperatureGenere;
    }
}
