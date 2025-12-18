package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Lesson;
import it.unimi.di.sweng.esame.model.Observable;
import it.unimi.di.sweng.esame.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;

public class MyOutputPresenterMaths implements Observer<List<Lesson>> {
    private final OutputView view;

    public MyOutputPresenterMaths(@NotNull OutputView view) {
        this.view = view;
    }

    @Override
    public void update(@NotNull Observable<List<Lesson>> obs, @NotNull List<Lesson> state) {
        state.removeIf(l -> !l.subject().equals("Maths"));
        state.sort(Comparator.comparing(Lesson::time));
        state.sort(Comparator.comparing(Lesson::day));
        for (int i = 0; i < view.size(); i++) {
            if (i < state.size())
                view.set(i, state.get(i).formatMateria());
            else
                view.set(i, "");
        }
    }
}
