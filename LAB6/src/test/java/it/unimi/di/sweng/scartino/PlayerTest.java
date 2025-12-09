package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import it.unimi.di.sweng.scartino.common.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerTest {

    Player SUT;
    Card mock1;

    @BeforeEach
    void setUp() {
        SUT = new Player("lollo");
        mock1 = mock();
    }

    @Test
    void testCarteIterabili() {
        Card mock2 = mock();
        Card mock3 = mock();
        SUT.takeDrawnCard(mock1);
        SUT.takeDrawnCard(mock2);
        SUT.takeDrawnCard(mock3);
        assertThat(SUT).containsExactlyInAnyOrder(mock1, mock2, mock3);
    }

    @Test
    void testHandSize() {
        SUT.takeDrawnCard(mock1);
        assertThat(SUT.handSize()).isEqualTo(1);
    }

    @Test
    void testGetPoints() {
        Card mock2 = mock();
        SUT.collectCards(mock1, mock2);
        when(mock1.getRank()).thenReturn(Rank.CINQUE);
        when(mock2.getRank()).thenReturn(Rank.ASSO);
        assertThat(SUT.getPoints()).isEqualTo(6);
    }

    @Test
    void testGetPoints2() {
        Card mock2 = mock();
        SUT.collectCards(mock1, mock2);
        when(mock1.getRank()).thenReturn(Rank.CAVALLO);
        when(mock2.getRank()).thenReturn(Rank.RE);
        assertThat(SUT.getPoints()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "'AB, 5C, RD' , 5C", // Caso 1: Trovato il 5, risultato 5C
            "'AB, 4B, RC' , 4B ",
            "'AB, 4C, RC' , AB"  // Caso 2: Nessun 5, fallback sulla prima carta (AB)
    })
    void testChooseAnswerCard_ChainBehavior(String cardSequence, String expectedCard) {
        SUT.setAnswerStrategyChain(new VincoSempreStrategy(
                new BattoAttaccoSeme(
                        Strategy.CARTA_CASUALE)));

        // Pulizia e popolamento del SUT con le carte
        Arrays.stream(cardSequence.split(",\\s*"))
                .map(Card::of)
                .forEach(SUT::takeDrawnCard);
        when((mock1.getSuit())).thenReturn(Suit.BASTONI);
        when(mock1.getRank()).thenReturn(Rank.DUE);//Per controllare anche "BattoAttaccoSeme"

        // Asserzione
        assertThat(SUT.chooseAnswerCard(mock1))
                .isEqualTo(Card.of(expectedCard));
    }

}