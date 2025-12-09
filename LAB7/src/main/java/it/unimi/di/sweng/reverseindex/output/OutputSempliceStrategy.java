package it.unimi.di.sweng.reverseindex.output;

import it.unimi.di.sweng.reverseindex.WordRefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputSempliceStrategy implements OutputStrategy {

    @Override
    public String output(Map<String, WordRefs> mappa) {
        StringJoiner joiner = new StringJoiner("\n");
        List<WordRefs> index = new ArrayList<>(mappa.values());
        for (var entry : index) {
            joiner.add("%s %s".formatted(entry.term(),entry.refs()));
        }
        return joiner.toString();
    }
}
