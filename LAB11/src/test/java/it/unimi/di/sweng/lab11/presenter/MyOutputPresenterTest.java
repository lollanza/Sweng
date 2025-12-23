package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Giocattolo;
import it.unimi.di.sweng.lab11.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyOutputPresenterTest {

    List<Giocattolo> l = new ArrayList<>(List.of(
            new Giocattolo("Peluche", 19, "Milano"),
            new Giocattolo("Trenino", 22, "Napoli"),
            new Giocattolo("PS5", 12, "Firenze"),
            new Giocattolo("Trenino", 42, "Roma"),
            new Giocattolo("PS5", 22, "Genova"),
            new Giocattolo("Peluche", 22, "Napoli"),
            new Giocattolo("Palla", 22, "Milano")
    ));
    OutputView view = mock();

    @BeforeEach
    void setUp() {
        when(view.size()).thenReturn(3);
    }

    @Test
    void testUnioneOutputPresenter() {
        MyOutputPresenter SUT = new MyOutputPresenter(
                view,
                g -> !g.nome().equals("Peluche"),
                Comparator.comparing(Giocattolo::city, String.CASE_INSENSITIVE_ORDER),
                Giocattolo::formatCity);
        SUT.update(mock(), l);
        verify(view).set(0, "Milano 19");
        verify(view).set(1, "Napoli 22");
        verify(view).set(2, "");
        verify(view, atLeastOnce()).size();
        verifyNoMoreInteractions(view);
    }
    @Test
    void testUnioneOutputPresenterCitta() {
        MyOutputPresenter SUT = new MyOutputPresenter(
                view,
                g -> !g.city().equals("Milano"),
                Comparator.comparing(Giocattolo::nome, String.CASE_INSENSITIVE_ORDER),
                Giocattolo::formatNome);
        SUT.update(mock(), l);
        verify(view).set(1, "Peluche 19");
        verify(view).set(0, "Palla 22");
        verify(view).set(2, "");
        verify(view, atLeastOnce()).size();
        verifyNoMoreInteractions(view);
    }
}