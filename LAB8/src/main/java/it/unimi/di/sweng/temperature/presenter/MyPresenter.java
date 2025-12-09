package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import it.unimi.di.sweng.temperature.model.Model;
import it.unimi.di.sweng.temperature.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MyPresenter implements Presenter, Observer<Double> {
    private final Model model;
    private final ScaleStrategy strategy;
    private final View view;

    public MyPresenter(Model model, ScaleStrategy strategy, View view) {
        this.model = model;
        this.strategy = strategy;
        this.view = view;
        this.view.addHandlers(this);

    }

    @Override
    public void action(@NotNull String temperatura) {
        try{
            Double tempCelsius = strategy.convertToCelsius(Double.parseDouble(temperatura));
            model.setTemp(tempCelsius);
        }catch (NumberFormatException e){}

    }

    @Override
    public void update(@NotNull Observable<Double> subject, @NotNull Double state) {
        Double tempCelsius = subject.getState();
        Double scaledTemp = strategy.convertFromCelsius(tempCelsius);
        view.setValue(String.format(Locale.ROOT,"%.2f", scaledTemp));

    }
}
