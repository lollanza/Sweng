package it.unimi.di.sweng.reverseindex.output;

import it.unimi.di.sweng.reverseindex.WordRefs;

import java.util.Map;

public interface OutputStrategy {
    String output(Map<String,WordRefs> mappa);
}
