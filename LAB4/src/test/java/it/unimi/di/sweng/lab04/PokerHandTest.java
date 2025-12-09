package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(2)
public class PokerHandTest {

    private final List<Card> FIVE_CARDS = List.of(
            Card.get(Rank.ACE, Suit.SPADES),
            Card.get(Rank.TEN, Suit.SPADES),
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.TWO, Suit.DIAMONDS),
            Card.get(Rank.SEVEN, Suit.HEARTS)
    );

    @Test
    void newPokerHand() {
        PokerHand hand = new PokerHand(FIVE_CARDS);
        assertThat(hand).containsExactlyInAnyOrderElementsOf(FIVE_CARDS);
    }


}
