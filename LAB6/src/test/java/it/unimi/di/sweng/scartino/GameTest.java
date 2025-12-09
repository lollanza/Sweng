package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GameTest {

    Game SUT;
    Player p1;
    Player p2;

    @BeforeEach
    void setUp() {
        p1 = mock();
        p2 = mock();
        SUT  = new Game(p1,p2);
    }

    @ParameterizedTest
    @CsvSource({
            "RB , 5D, 0",
            "4S, FC, 0",
            "5C, AD, 1",
            "4S, 5D, -1",
            "3C, 7C, -1",
            "6S, 2S, 1"
    })
    void testBeatsScarto(String attack, String answer, int expected) {
        when(p1.chooseAttackCard()).thenReturn(Card.of(attack));
        Card attackCard = p1.chooseAttackCard();
        when(p2.chooseAnswerCard(attackCard)).thenReturn(Card.of(answer));
        assertThat(SUT.beats(attackCard,p2.chooseAnswerCard(attackCard))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "20, 5, 'Vince p1!'",
            "5, 20, 'Vince p2!'",
            "20, 20, 'Pareggio!'",
    })
    void testProclamaVincitoreOPareggio(int punti1,int punti2,String output) {
        when(p1.getPoints()).thenReturn(punti1);
        when(p2.getPoints()).thenReturn(punti2);
        when(p1.toString()).thenReturn("p1");
        when(p2.toString()).thenReturn("p2");
        assertThat(SUT.proclamaVincitoreOPareggio()).isEqualTo(output);
    }

    @Test
    void testPlayTurnDistribuisciCarte() {
        when(p1.handSize()).thenReturn(0);

        SUT.playTurn();

        verify(p1, times(3)).takeDrawnCard(any(Card.class));
        verify(p2, times(3)).takeDrawnCard(any(Card.class));
    }

    @Test
    void testPlayTurnCompleto() {

        // ARRANGE: Definiamo il comportamento dei giocatori mock per un turno
        Card attackCard = Card.of("3B");
        Card answerCard = Card.of("7B"); // Carta che vince

        when(p1.chooseAttackCard()).thenReturn(attackCard);
        when(p2.chooseAnswerCard(attackCard)).thenReturn(answerCard);

        // ACT: Eseguiamo il turno
        SUT.playTurn();

        // ASSERT: Verifichiamo le interazioni corrette
        verify(p2).collectCards(attackCard, answerCard); // p2 vince e raccoglie le carte
        verify(p1, never()).collectCards(any(Card.class), any(Card.class)); // p1 non raccoglie nulla
        // Poiché p2 ha vinto, il turno è passato a lui. Verifichiamolo.
        assertThat(SUT.opponentOf(p2)).isEqualTo(p1);
    }
}