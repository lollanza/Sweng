package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(2)
public class HandEvaluatorTest {

    @Test
    void pairHandEvalTest() {
        PokerHand hand = new PokerHand(List.of(
                Card.get(Rank.ACE, Suit.SPADES),
                Card.get(Rank.ACE, Suit.CLUBS),
                Card.get(Rank.TEN, Suit.SPADES),
                Card.get(Rank.TWO, Suit.DIAMONDS),
                Card.get(Rank.SEVEN, Suit.HEARTS)
        ));

        assertThat(hand.getRank()).isEqualTo(HandRank.ONE_PAIR);
    }

    @Test
    void highCardHandEvalTest() {
        PokerHand hand = new PokerHand(List.of(
                Card.get(Rank.ACE, Suit.SPADES),
                Card.get(Rank.SIX, Suit.CLUBS),
                Card.get(Rank.TEN, Suit.SPADES),
                Card.get(Rank.TWO, Suit.DIAMONDS),
                Card.get(Rank.SEVEN, Suit.HEARTS)
        ));

        assertThat(hand.getRank()).isEqualTo(HandRank.HIGH_CARD);
    }

    @Test
    void trisHandEvalTest() {
        PokerHand hand = new PokerHand(List.of(
                Card.get(Rank.ACE, Suit.SPADES),
                Card.get(Rank.ACE, Suit.CLUBS),
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.TWO, Suit.DIAMONDS),
                Card.get(Rank.SEVEN, Suit.HEARTS)
        ));

        assertThat(hand.getRank()).isEqualTo(HandRank.THREE_OF_A_KIND);
    }


    @Test
    void flushHandEvalTest() {
        PokerHand hand = new PokerHand(List.of(
                Card.get(Rank.ACE, Suit.SPADES),
                Card.get(Rank.SIX, Suit.SPADES),
                Card.get(Rank.TEN, Suit.SPADES),
                Card.get(Rank.TWO, Suit.SPADES),
                Card.get(Rank.SEVEN, Suit.SPADES)
        ));
        assertThat(hand.getRank()).isEqualTo(HandRank.FLUSH);
    }

    @Test
    void twoPairHandEvalTest() {
        PokerHand hand = new PokerHand(List.of(
                Card.get(Rank.ACE, Suit.SPADES),
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.TEN, Suit.SPADES),
                Card.get(Rank.TEN, Suit.CLUBS),
                Card.get(Rank.SEVEN, Suit.SPADES)
        ));
        assertThat(hand.getRank()).isEqualTo(HandRank.TWO_PAIR);
    }
}
