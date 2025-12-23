package it.unimi.di.sweng.lab11.model;

import it.unimi.di.sweng.lab11.presenter.Observer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ModelTest {
    @Test
    void testNotifyObserver() {
        Model  SUT = new Model();

        Observer<List<Giocattolo>> obs1 = mock();
        Observer<List<Giocattolo>> obs2 = mock();

        SUT.addObserver(obs1);
        SUT.addObserver(obs2);

        Giocattolo g = mock();

        SUT.inserimentoGioco(g);

        verify(obs1).update(SUT, SUT.getState());
        verify(obs2).update(SUT,SUT.getState());



    }
}