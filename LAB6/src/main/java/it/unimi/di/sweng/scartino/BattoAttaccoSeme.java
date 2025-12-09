package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BattoAttaccoSeme implements Strategy {
    private final @NotNull Strategy next;
    public BattoAttaccoSeme(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card c: cards){
            assert attackCard != null;
            if(c.getSuit().ordinal() > attackCard.getSuit().ordinal()) return c;
            else if(c.getSuit().ordinal() == attackCard.getSuit().ordinal() &&
                    c.getRank().ordinal() > attackCard.getRank().ordinal()) return c;
        }
        return next.chooseCard(cards,attackCard);
    }
}
