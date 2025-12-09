package it.unimi.di.sweng.reverseindex.output;

import it.unimi.di.sweng.reverseindex.WordRefs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputOrdinatoAlfabeticamenteStrategy implements OutputStrategy {
    @Override
    public String output(Map<String, WordRefs> mappa) {
        StringJoiner joiner = new StringJoiner("\n");
        List<WordRefs> index = new ArrayList<>(mappa.values());
        index.sort(WordRefs.CASEINSENSITIVE);
        for (var entry : index)
            joiner.add("%s %s".formatted(entry.term(),entry.refs()));
        return joiner.toString();
    }
}
