package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static it.unimi.di.sweng.scartino.TestUtils.cardsFrom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class StrategyTest {
    @Test
    void testStrategyBase() {
        Strategy SUT = Strategy.CARTA_CASUALE;

        Card c = SUT.chooseCard(cardsFrom("5B,7B,2S"), null);
        assertThat(c).isEqualTo(Card.of("5B"));
    }

    @Test
    void testGioca5Strategy() {
        Strategy SUT = new VincoSempreStrategy(mock());
        assertThat(SUT.chooseCard(cardsFrom("CB,RS,5B"),null)).isEqualTo(Card.of("5B"));
    }

    @ParameterizedTest
    @CsvSource({
            "'4B,CS,1C',CS",
            "'7D,6S,RC',RC",
            "'FD,CS,1C',FD"
    })
    void testGiocoFigura(String cards, String expected) {
        Strategy SUT = new ScartoFiguraStrategy(mock());
        assertThat(SUT.chooseCard(cardsFrom(cards),null)).isEqualTo(Card.of(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "'4B,7S,1C',1C",
            "'7D,1S,2C',1S",
            "'1D,3S,6C',1D"
    })
    void testCartaDebole(String cards, String expected) {
        Strategy SUT = new GiocoAssoStrategy(mock());
        assertThat(SUT.chooseCard(cardsFrom(cards),null)).isEqualTo(Card.of(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "'4B,7S,1C',7S",
            "'7D,1S,2C',7D",
            "'6B,3C,6C',6B"
    })
    void testVincoSeme(String cards, String expected) {
        Card attackCard = Card.of("4B");
        Strategy SUT = new BattoAttaccoSeme(mock());
        assertThat(SUT.chooseCard(cardsFrom(cards),attackCard)).isEqualTo(Card.of(expected));

    }


}