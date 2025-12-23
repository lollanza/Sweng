package it.unimi.di.sweng.lab11.model;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IState {
    void inserimentoGioco(@NotNull Giocattolo g);

    @NotNull List<Giocattolo> getGiochi();
    //TODO: aggiungere i metodi opportuni
}
