package it.unimi.di.sweng.reverseindex;

import org.jetbrains.annotations.NotNull;

public record Document(int id, String body) {
    @Override
    public @NotNull String toString() {
        return "%d: %s".formatted(id,body);
    }
}
