package it.unimi.di.sweng.slalom.views;

import org.jetbrains.annotations.NotNull;

public interface OutputView {

  // assert i < size()
  void set(int i, @NotNull String s);

  int size();
}
