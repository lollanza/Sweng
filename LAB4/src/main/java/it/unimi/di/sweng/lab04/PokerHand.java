package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PokerHand implements Iterable<Card> {

    private final List<Card> hand;
    private final HandEvaluator chain =
            new FlushHandEvaluator(
                    new TrisHandEvaluator(
                            new TwoPairHandEvaluator(
                                    new OnePairEvaluator(
                                            HandEvaluator.HIGHCARD))));

    public PokerHand(List<Card> cards) {
        hand = new ArrayList<>(cards);
    }

    @Override
    public @NotNull Iterator<Card> iterator() {
        return List.copyOf(hand).iterator();
    }

    public HandRank getRank() {
        return chain.evaluate(this);
    }
}

