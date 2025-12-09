package it.unimi.di.sweng.lab04;

import org.jetbrains.annotations.NotNull;

public interface HandEvaluator {
    @NotNull HandRank evaluate(@NotNull PokerHand hand);

    HandEvaluator HIGHCARD = hand -> HandRank.HIGH_CARD;
}
