package it.unimi.di.sweng.lab11.model;

import it.unimi.di.sweng.lab11.presenter.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Model extends State implements Observable<List<Giocattolo>> {
    private final List<Observer<List<Giocattolo>>> observers = new ArrayList<>();

    @Override
    public void addObserver(@NotNull Observer<List<Giocattolo>> obs) {
        observers.add(obs);
        notifyObservers();
    }

    @Override
    public @NotNull List<Giocattolo> getState() {
        return getGiochi();
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Giocattolo>> o : observers)
            o.update(this,getState());
    }

    @Override
    public void inserimentoGioco(@NotNull Giocattolo g) {
        super.inserimentoGioco(g);
        notifyObservers();
    }
}
