package it.unimi.di.sweng.lab11.model;

import org.jetbrains.annotations.NotNull;

public record Giocattolo(@NotNull String nome, int quantita, @NotNull String city) {
    public Giocattolo{
        if(quantita<0) throw new IllegalArgumentException("Quantità consegnata negativa!");
        else if(quantita ==0) throw new IllegalArgumentException("Quantità consegnata nulla!");
        if(quantita>100) throw new IllegalArgumentException("Quantità consegnata troppo elevata!");
    }

    @NotNull
    public String formatNome(){
        return "%s %d".formatted(nome,quantita);
    }

    public @NotNull String formatCity() {
        return "%s %d".formatted(city,quantita);
    }
}
