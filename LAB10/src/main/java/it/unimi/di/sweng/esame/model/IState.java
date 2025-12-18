package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IState {
    void insertLesson(@NotNull Lesson lesson);

    @NotNull List<Lesson> getLessons();

}
