package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ObservableModel extends TemperatureModel implements Observable<Double> {

    private final List<Observer<Double>> observers = new ArrayList<>();

    @Override
    public void notifyObservers() {
        for (Observer<Double> obs : observers) {
            obs.update(this, getState());
        }
    }

    @Override
    public void addObserver(@NotNull Observer<Double> obs) {
        observers.add(obs);
    }

    @Override
    public @NotNull Double getState() {
        return getTemp();
    }

    @Override
    public void setTemp(double temp) {
        temp = Math.round(temp * 100.0) / 100.0;
        if(temp!=getTemp()) {
            super.setTemp(temp);
            notifyObservers();
        }
    }
}
