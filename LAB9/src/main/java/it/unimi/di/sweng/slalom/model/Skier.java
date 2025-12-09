package it.unimi.di.sweng.slalom.model;

import java.util.Comparator;
import java.util.Locale;

public record Skier(String name, double time) implements Comparable<Skier> {

    private static final Comparator<Skier> COMPARATOR = Comparator
            .comparingDouble(Skier::time)
            .reversed()
            .thenComparing(s -> s.name)
            .reversed();

    public Skier{
        if(name.isBlank())
            throw new IllegalArgumentException("Nome non può essere vuoto");
        if(time<0)
            throw new IllegalArgumentException("Tempo non può essere negativo");
    }

    @Override
    public String toString() {
        if(time<60)
            return String.format(Locale.ROOT, "%-27s    %5.2f",name,time);
        else
            return String.format(Locale.ROOT, "%-27s %d:%05.2f",name,(int) time /60,time-60);
    }

    public int compareTo(Skier o){
        return COMPARATOR.compare(this,o);
    }

}
