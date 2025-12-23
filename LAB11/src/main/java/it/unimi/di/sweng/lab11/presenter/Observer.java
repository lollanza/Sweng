package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Observable;
import org.jetbrains.annotations.NotNull;

public interface Observer<T>{
    void update(@NotNull Observable<T> obs, T state);
}
