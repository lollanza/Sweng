package it.unimi.di.sweng.lab11.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.testfx.assertions.api.Assertions.assertThat;

class StateTest {
    @Test
    void testGetSet() {
        Giocattolo g1 = new Giocattolo("Peluche", 8, "Milano");
        Giocattolo g2 = new Giocattolo("PS5", 14, "Napoli");

        IState SUT = new State();

        SUT.inserimentoGioco(g1);
        SUT.inserimentoGioco(g2);

        assertThat(SUT.getGiochi()).contains(g1, g2);
    }


    @Test
    void testInputErratiTroppiGiochi() {
        IState SUT = new State();

        Giocattolo g1 = new Giocattolo("Peluche", 98, "Milano");
        Giocattolo g2 = new Giocattolo("PS5", 14, "Milano");

        SUT.inserimentoGioco(g1);
        assertThatThrownBy(()->SUT.inserimentoGioco(g2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Troppi giochi in una città");
    }

    @Test
    void testConsegnaStessaCity() {
        IState SUT = new State();

        Giocattolo g1 = new Giocattolo("Peluche", 27,"Milano");
        Giocattolo g2 = new Giocattolo("Peluche", 14, "Milano");

        SUT.inserimentoGioco(g1);
        assertThatThrownBy(()-> SUT.inserimentoGioco(g2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Già consegnato in questa città");


    }
}