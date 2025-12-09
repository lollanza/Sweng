package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VincoSempreStrategy implements Strategy {

    private final @NotNull Strategy next;

    public VincoSempreStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card c :cards)
            if(c.getRank().equals(Rank.CINQUE)) return c;
        return next.chooseCard(cards, attackCard);
    }
}
