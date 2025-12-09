package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Strategy {

    Strategy CARTA_CASUALE = (cards, attackCard) -> cards.iterator().next();


    // ATTENZIONE: quando attackCard è null vuol dire che è una strategia di attacco,
    //             se è diversa da null è strategia di risposta
    @NotNull Card chooseCard(@NotNull Iterable<Card> cards,
                             @Nullable Card attackCard);
}
