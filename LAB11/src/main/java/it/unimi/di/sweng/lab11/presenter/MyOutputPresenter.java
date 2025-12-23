package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Giocattolo;
import it.unimi.di.sweng.lab11.model.Observable;
import it.unimi.di.sweng.lab11.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyOutputPresenter implements Observer<List<Giocattolo>> {

    @NotNull
    private final OutputView view;
    @NotNull
    private final Predicate<Giocattolo> filter;
    @NotNull
    private final Comparator<Giocattolo> sorted;
    @NotNull
    private final Function<Giocattolo, String> formatter;

    public MyOutputPresenter(@NotNull OutputView view , @NotNull Predicate<Giocattolo> filter, @NotNull Comparator<Giocattolo> sorted, @NotNull Function<Giocattolo, String> formatter){
        this.view = view;
        this.filter = filter;
        this.sorted = sorted;
        this.formatter = formatter;
    }

    @Override
    public void update(@NotNull Observable<List<Giocattolo>> obs, List<Giocattolo> state) {
        state.removeIf(filter);
        state.sort(sorted);
        for (int i = 0; i < view.size(); i++){
            if(i<state.size())
                view.set(i,formatter.apply(state.get(i)));
            else
                view.set(i,"");
        }
    }


}
