package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrisHandEvaluator implements HandEvaluator {

    private final HandEvaluator next;

    public TrisHandEvaluator(HandEvaluator next) {
        this.next = next;
    }

    @Override
    public @NotNull HandRank evaluate(@NotNull PokerHand hand) {
        if (isTris(hand)) return HandRank.THREE_OF_A_KIND;
        return next.evaluate(hand);
    }

    private boolean isTris(@NotNull PokerHand hand) {
        Map<Rank, Integer> rankMap = new HashMap<>();
        for (Card c : hand) {
            rankMap.put(c.getRank(), rankMap.getOrDefault(c.getRank(), 0) + 1);
        }
        return rankMap.containsValue(3);
    }
}
