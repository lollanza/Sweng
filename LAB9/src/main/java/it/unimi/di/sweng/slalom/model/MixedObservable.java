package it.unimi.di.sweng.slalom.model;

import org.jetbrains.annotations.NotNull;

public interface MixedObservable<Tpull, Tpush> {
    void notifyObservers();
    void addObserver(@NotNull MixedObserver<Tpull,Tpush> observer);
    @NotNull Tpull getState1();//Prima Manche
    @NotNull Tpull getState2();//Seconda manche
}
