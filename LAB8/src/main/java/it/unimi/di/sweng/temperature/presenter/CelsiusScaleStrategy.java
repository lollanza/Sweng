package it.unimi.di.sweng.temperature.presenter;

public enum CelsiusScaleStrategy implements ScaleStrategy {
    INSTANCE;

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }
}
