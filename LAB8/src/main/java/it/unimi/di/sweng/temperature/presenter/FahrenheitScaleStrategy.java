package it.unimi.di.sweng.temperature.presenter;

public enum FahrenheitScaleStrategy implements ScaleStrategy {
    INSTANCE;

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * 9.0 / 5.0) + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * 5.0 / 9.0;
    }
}
