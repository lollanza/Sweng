package it.unimi.di.sweng.reverseindex.output;

import it.unimi.di.sweng.reverseindex.WordRefs;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.StringJoiner;

public class OutputAllineato implements OutputStrategy {
    @Override
    public String output(@NotNull Map<String, WordRefs> index) {
        int maxLength = 0;
        for (String word : index.keySet()) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        String formatString = "%-" + (maxLength + 2) + "s %s";
        StringJoiner joiner = new StringJoiner("\n");
        for (Map.Entry<String, WordRefs> entry : index.entrySet())
            joiner.add(String.format(formatString, entry.getKey(), entry.getValue().refs().toString()));
        return joiner.toString();
    }
}
