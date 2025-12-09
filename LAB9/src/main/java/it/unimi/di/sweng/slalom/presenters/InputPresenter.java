package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import org.jetbrains.annotations.NotNull;

public class InputPresenter implements Presenter {

    private final NextSkierView view;
    private final Model model;


    public InputPresenter(Model model, NextSkierView view) {
        this.model = model;
        this.view = view;
        view.addHandlers(this);
    }

    @Override
    public void action(@NotNull String text1, @NotNull String text2) {
        try {
           double tempo = Double.parseDouble(text2);
            if (tempo < 0){
                throw new IllegalArgumentException("Tempo deve essere positivo");
            }
            if (tempo > 60) {
                throw new IllegalArgumentException("Il tempo della singola manche deve essere inferiore ai 60 secondi");
            }
            model.addSecondRuntime(new Skier(text1, tempo));
        } catch (NumberFormatException e) {
            view.showError("Il tempo deve essere un numero");
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
}
