package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record Lesson(@NotNull String subject,@NotNull DayOfWeek day,@NotNull LocalTime time, int duration) {
    public Lesson {
        if (time.isBefore(LocalTime.of(8,30))) throw new IllegalArgumentException("Invalid time");
        if(time.plusHours(duration).isAfter(LocalTime.of(18,30))) throw new IllegalArgumentException("Invalid time");
        if(duration<1 || duration>4) throw new IllegalArgumentException("Invalid duration");
        if(day.compareTo(DayOfWeek.FRIDAY)>0) throw new IllegalArgumentException("Invalid day");
    }

    public boolean overlaps(@NotNull Lesson l) {
        if (day != l.day) return false;
        return time.isBefore(l.time.plusHours(l.duration)) &&
                l.time.isBefore(time.plusHours(duration));
    }

    public @NotNull String formatDay() {
        return "%s-%s %s".formatted(time, time.plusHours(duration), subject);
    }

    public @NotNull String formatMateria() {
        return "%s %s-%s".formatted(day, time, time.plusHours(duration));
    }
}