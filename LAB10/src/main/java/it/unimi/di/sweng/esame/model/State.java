package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class State implements IState {

    private final List<Lesson> lessons = new ArrayList<>();

    @Override
    public void insertLesson(@NotNull Lesson lesson) {
        for (Lesson l : lessons) {
            if (lesson.overlaps(l)) throw new IllegalArgumentException("Overlapping time error");
        }
        lessons.add(lesson);
    }

    @Override
    public @NotNull List<Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }
}
