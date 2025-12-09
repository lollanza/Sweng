package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;


import java.util.Arrays;
import java.util.List;

public class TestUtils {



    @ParameterizedTest
    @ValueSource(strings = {"1C", "2S"})
    void provaParsing(String card) {
        Card cardP= cardFrom(card);
        System.out.println(card);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1C, 2S ,  KC", "2S"})
    void provaParsingCards(String cards) {
        var parsed= cardListFrom(cards);
        System.out.println(parsed);
    }

    @Test
    void provaMocking() {
        Iterable<Card> ph = mock();
        whenIterated(ph, cardFrom("2S"),  cardFrom("KD"));

        for (Card card : ph) {
            System.out.println(card);
        }

    }



    public static List<Card> cardListFrom(String str) {
        return Arrays.stream(str.split("\\s*,\\s*"))
              .map(TestUtils::cardFrom).toList();
    }

    public static Card[] cardArrayFrom(String str) {
        return Arrays.stream(str.split("\\s*,\\s*"))
              .map(TestUtils::cardFrom).toArray(Card[]::new);
    }

    public static Card cardFrom(String input) {
        Suit s = parseSuit(input);
        Rank r = parseRank(input);
        return Card.get(r, s);
    }

    private static Rank parseRank(String str) {
        return switch (str.charAt(0)) {
            case '1', 'A' -> Rank.ACE;
            case '2' -> Rank.TWO;
            case '3' -> Rank.THREE;
            case '4' -> Rank.FOUR;
            case '5' -> Rank.FIVE;
            case '6' -> Rank.SIX;
            case '7' -> Rank.SEVEN;
            case '8' -> Rank.EIGHT;
            case '9' -> Rank.NINE;
            case '0' -> Rank.TEN;
            case 'J' -> Rank.JACK;
            case 'Q' -> Rank.QUEEN;
            case 'K' -> Rank.KING;
            default -> throw new IllegalStateException("Unexpected rank value: " + str.charAt(0));
        };
    }

    private static Suit parseSuit(String s) {
        return switch (s.charAt(1)) {
            case 'H' -> Suit.HEARTS;
            case 'C' -> Suit.CLUBS;
            case 'D' -> Suit.DIAMONDS;
            case 'S' -> Suit.SPADES;
            default -> throw new IllegalStateException("Unexpected suit value: " + s.charAt(1));
        };
    }

    @SafeVarargs
    public static <T> void whenIterated(Iterable<T> p, T... d) {
        doAnswer(a -> List.of(d).iterator()).when(p).iterator();
    }
}
