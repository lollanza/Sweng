package it.unimi.di.sweng.reverseindex;

import java.util.Comparator;
import java.util.Locale;
import java.util.Set;

public record WordRefs(String term, Set<Integer> refs) {
    public final static Comparator<WordRefs> CASEINSENSITIVE = Comparator.comparing(wordRefs1 -> wordRefs1.term().toUpperCase(Locale.ROOT));
    public final static Comparator<WordRefs> NUMBER_REFERECES_CMP =
            Comparator.comparing((WordRefs wordRefs1) -> wordRefs1.refs().size()).reversed().thenComparing(CASEINSENSITIVE);

    public void add(int id) {
        refs.add(id);
    }
}
