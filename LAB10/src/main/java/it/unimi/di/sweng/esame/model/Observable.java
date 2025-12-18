package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.presenter.Observer;
import org.jetbrains.annotations.NotNull;

public interface Observable<T> {
    void addObserver(@NotNull Observer<T> obs);
    @NotNull T getState();
    void notifyObservers();

}
