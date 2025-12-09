package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TwoPairHandEvaluator implements HandEvaluator{

    private final HandEvaluator next;

    public TwoPairHandEvaluator(HandEvaluator next){
        this.next = next;
    }


    @Override
    public @NotNull HandRank evaluate(@NotNull PokerHand hand) {
        if(isTowPair(hand)) return HandRank.TWO_PAIR;
        return next.evaluate(hand);
    }

    private boolean isTowPair(@NotNull PokerHand hand) {
        Set<Rank> rankSet = new HashSet<>();
        for (Card c: hand)
            rankSet.add(c.getRank());
        return rankSet.size()==3;
    }
}