package it.unimi.di.sweng.lab11.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State implements IState {

    private final Map<String, List<Giocattolo>> consegne = new HashMap<>();

    @Override
    public void inserimentoGioco(@NotNull Giocattolo g) {
        List<Giocattolo> inventarioCity = consegne.computeIfAbsent(g.city(), k -> new ArrayList<>());
        int tot = inventarioCity.stream().mapToInt(Giocattolo::quantita).sum();
        if(tot+g.quantita()>100) throw new IllegalArgumentException("Troppi giochi in una città");
        var presente = inventarioCity.stream().anyMatch(t -> t.nome().equalsIgnoreCase(g.nome()));
        if(presente) throw new IllegalArgumentException("Già consegnato in questa città");
        inventarioCity.add(g);
    }

    @Override
    public @NotNull List<Giocattolo> getGiochi() {
        return new ArrayList<>(consegne.values().stream().flatMap(List::stream).toList());
    }
}
