package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.jetbrains.annotations.NotNull;

public class FlushHandEvaluator implements HandEvaluator{

    private final HandEvaluator next;

    public FlushHandEvaluator(HandEvaluator next){
        this.next = next;
    }

    @Override
    public @NotNull HandRank evaluate(@NotNull PokerHand hand) {
        if(isFlush(hand)) return HandRank.FLUSH;
        return next.evaluate(hand);
    }

    private boolean isFlush(@NotNull PokerHand hand) {
        Suit firstSuit = hand.iterator().next().getSuit();
        for (Card c:hand)
            if (c.getSuit() != firstSuit) return false;
        return true;
    }
}
