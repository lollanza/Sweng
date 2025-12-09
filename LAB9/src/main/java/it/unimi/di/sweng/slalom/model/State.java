package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Main;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class State implements IState {

    private final Map<String, Skier> firstManche = new HashMap<>();
    private final Map<String, Skier> secondManche = new HashMap<>();

    @Override
    public @NotNull List<Skier> getFirstManche() {
        return new ArrayList<>(firstManche.values());
    }

    @Override
    public @NotNull List<Skier> getSecondManche() {
        return new ArrayList<>(secondManche.values());
    }

    public abstract void addSecondRuntime(@NotNull Skier secondVolta);

    @Override
    public @NotNull String getNextSkier() {
        var r = new ArrayList<>(firstManche.values());
        int next = Main.SKIER_NUM - secondManche.size() - 1;
        if(next>=0){
            r.sort(null);
            return r.get(Main.SKIER_NUM - secondManche.size() - 1).name();
        }
        return "GARA FINITA";
    }

    @Override
    public void addSecondRunTime(@NotNull Skier secondRun) {
        var name = secondRun.name();
        if(!firstManche.containsKey(name)){
            if(secondManche.size()==firstManche.size())
                throw new IllegalArgumentException("Gara già finita");
            throw new IllegalArgumentException("Nome non presente nella prima manche");
        }
        if(secondManche.containsKey(name))
            throw new IllegalArgumentException("Nome già presente nella seconda manche");
        secondManche.put(name, secondRun);
    }

    public void readFilePrimaManche(@NotNull Scanner s) {
        while (s.hasNextLine()) {
            String linea = s.nextLine();
            String[] el = linea.split(";");
            String name = el[0];
            double time = Double.parseDouble(el[1]);
            firstManche.put(name, new Skier(name, time));
        }
    }
}
