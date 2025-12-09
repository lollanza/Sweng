package it.unimi.di.sweng.slalom.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IState {
    @NotNull List<Skier> getFirstManche();

    @NotNull List<Skier> getSecondManche();

    @NotNull String getNextSkier();

    void addSecondRunTime(@NotNull Skier secondRun);
}
