package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GiocoAssoStrategy implements Strategy {
    private final @NotNull Strategy next;
    public GiocoAssoStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card c: cards){
            if(c.getRank().ordinal()==0) return c;
        }
        return next.chooseCard(cards,attackCard);
    }
}
