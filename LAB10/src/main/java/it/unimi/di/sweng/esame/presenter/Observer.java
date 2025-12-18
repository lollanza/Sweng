package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Observable;
import org.jetbrains.annotations.NotNull;

public interface Observer<T>{
    void update(@NotNull Observable<T> obs, @NotNull T state);
}
