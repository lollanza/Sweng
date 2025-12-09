package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class OnePairEvaluator implements HandEvaluator{

    private final HandEvaluator next;

    public OnePairEvaluator(HandEvaluator next){
        this.next = next;
    }

    @Override
    public @NotNull HandRank evaluate(@NotNull PokerHand hand) {
        if(isOnePair(hand)) return HandRank.ONE_PAIR;
        return next.evaluate(hand);
    }

    private boolean isOnePair(PokerHand hand) {
        Set<Rank> rankSet = new HashSet<>();
        for (Card card : hand){
            if (rankSet.contains(card.getRank())) return true;
            rankSet.add(card.getRank());
        }
        return false;
    }
}
