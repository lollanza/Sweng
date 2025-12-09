package it.unimi.di.sweng.temperature.model;

public class TemperatureModel implements Model {
    private double temperatura;
    @Override
    public double getTemp() {
        return temperatura;
    }

    @Override
    public void setTemp(double temp) {
        temperatura = temp;
    }
}
