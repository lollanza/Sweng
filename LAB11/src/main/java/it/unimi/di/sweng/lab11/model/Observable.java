package it.unimi.di.sweng.lab11.model;

import it.unimi.di.sweng.lab11.presenter.Observer;
import org.jetbrains.annotations.NotNull;


public interface Observable<T> {

    void addObserver(@NotNull Observer<T> obs);

    @NotNull T getState();

    void notifyObservers();
}
