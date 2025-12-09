package it.unimi.di.sweng.temperature.presenter;

public interface ScaleStrategy {
  double convertFromCelsius(double temperature);

  double convertToCelsius(double temperature);
}
