package it.unimi.di.sweng.lab11.view;


import it.unimi.di.sweng.lab11.presenter.InputPresenter;
import org.jetbrains.annotations.NotNull;

public interface InputView {
    void addHandlers(@NotNull InputPresenter presenter);

    void showError(@NotNull String s);

    void showSuccess();
}
