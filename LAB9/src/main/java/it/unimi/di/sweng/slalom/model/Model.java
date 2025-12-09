package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Main;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Model extends State implements MixedObservable<List<Skier>,String>{

    private final List<MixedObserver<List<Skier>, String>> observers = new ArrayList<>();

    // TODO completare modello dati

    @Override
    public void readFilePrimaManche(@NotNull Scanner s) {
        super.readFilePrimaManche(s);
        notifyObservers();
    }

    @Override
    public void addSecondRuntime(@NotNull Skier secondVolta) {
        super.addSecondRunTime(secondVolta);
      notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(MixedObserver<List<Skier>, String> ob : observers)
            ob.update(this, getNextSkier());
    }

    @Override
    public void addObserver(@NotNull MixedObserver<List<Skier>, String> observer) {
        observers.add(observer);
    }

    @Override
    public @NotNull List<Skier> getState1() {
        return getFirstManche();
    }

    @Override
    public @NotNull List<Skier> getState2() {
        return getSecondManche();
    }
}
