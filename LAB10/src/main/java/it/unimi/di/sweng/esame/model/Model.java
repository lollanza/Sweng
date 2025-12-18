package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.presenter.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Model extends State implements Observable<List<Lesson>> {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(@NotNull Observer<List<Lesson>> obs) {
        observers.add(obs);
    }

    @Override
    public @NotNull List<Lesson> getState() {
        return getLessons();
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this, getState());
        }
    }

    @Override
    public void insertLesson(@NotNull Lesson lesson) {
        super.insertLesson(lesson);
        notifyObservers();
    }
}
