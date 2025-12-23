package it.unimi.di.sweng.lab11.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class GiocattoloTest {

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
            Peluche | -3 | Milano | Quantità consegnata negativa!
            PS5 |  101 | Firenze | Quantità consegnata troppo elevata!
            Trenino  |  1334 | Roma | Quantità consegnata troppo elevata!
            Palla | 0 | Venezia | Quantità consegnata nulla!
            """)
    void testInserimentiNonValidi(String giochi, int q, String c,String m) {
        assertThatThrownBy(() -> new Giocattolo(giochi,q,c))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(m);
    }
}