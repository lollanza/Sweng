package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Giocattolo;
import it.unimi.di.sweng.lab11.model.IState;
import it.unimi.di.sweng.lab11.view.InputView;
import org.jetbrains.annotations.NotNull;

public class MyInputPresenter implements InputPresenter {

    private final InputView view;
    private final IState stato;

    public MyInputPresenter(@NotNull InputView view, IState stato) {
        this.view = view;
        this.stato = stato;
        view.addHandlers(this);
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        try {
            String[] gioco = text.split(":");
            if (gioco.length != 2) throw new IllegalArgumentException("The input must be in the form toy:num");
            if (gioco[0].isBlank()) throw new IllegalArgumentException("City and toy must not be blank");
            if (text1.isBlank()) throw new IllegalArgumentException("City and toy must not be blank");
            int q = Integer.parseInt(gioco[1]);
            Giocattolo g = new Giocattolo(gioco[0],q,text1);
            stato.inserimentoGioco(g);
            view.showSuccess();
        } catch (NumberFormatException e){
            view.showError("The number of toys must be a positive integer");
        } catch (IllegalArgumentException e){
            view.showError(e.getMessage());
        }
    }
}
