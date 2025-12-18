package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Lesson;
import it.unimi.di.sweng.esame.model.Observable;
import it.unimi.di.sweng.esame.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;

public class MyOutputPresenter implements Observer<List<Lesson>> {
    public final DayOfWeek day;
    private final OutputView view;

    public MyOutputPresenter(@NotNull OutputView view,@NotNull DayOfWeek day) {
        this.day = day;
        this.view = view;
    }

    @Override
    public void update(@NotNull Observable<List<Lesson>> obs, @NotNull List<Lesson> state) {
        state.removeIf(l -> !l.day().equals(day));
        state.sort(Comparator.comparing(Lesson::time));
        for (int i = 0; i < view.size(); i++) {
            if (i < state.size())
                view.set(i, state.get(i).formatDay());
            else
                view.set(i, "");
        }
    }
}
