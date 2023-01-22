package models.generateurs;

import java.util.Random;

public class GenerateurParVariation implements StrategieTemperatureGenere{
    private final double valeurVariable;

    public GenerateurParVariation(double valeurVariable) {
        this.valeurVariable = valeurVariable;
    }

    @Override
    public double compute(double temperature) {
        Random random = new Random();
        double variation = random.nextDouble() * valeurVariable;
        int signe = random.nextInt(2);
        double temperatureGenere;
        if(signe == 0){
            temperatureGenere = temperature - variation;
        }
        else {
            temperatureGenere = temperature + variation;
        }

        if(temperatureGenere < -40){

            return 40;

        } else if (temperatureGenere > 54) {

            return 54;

        }
        else {
            return temperatureGenere;
        }
    }
}
