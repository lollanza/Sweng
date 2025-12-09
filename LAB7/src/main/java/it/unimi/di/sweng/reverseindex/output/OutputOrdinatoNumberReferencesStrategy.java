package it.unimi.di.sweng.reverseindex.output;

import it.unimi.di.sweng.reverseindex.WordRefs;

import java.util.*;

public class OutputOrdinatoNumberReferencesStrategy implements OutputStrategy {

    @Override
    public String output(Map<String,WordRefs> mappa) {
        StringJoiner joiner = new StringJoiner("\n");
        List<WordRefs> index = new ArrayList<>(mappa.values());

        index.sort(WordRefs.NUMBER_REFERECES_CMP);
        for (var entry : index)
            joiner.add("%s %s".formatted(entry.term(),entry.refs()));
        return joiner.toString();
    }
}
