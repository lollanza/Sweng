package it.unimi.di.sweng.slalom.model;

import org.jetbrains.annotations.NotNull;

public interface MixedObserver<Tpull, Tpush> {
    void update(@NotNull MixedObservable<Tpull, Tpush> subject, @NotNull Tpush state);
}
