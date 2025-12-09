package it.unimi.di.sweng.reverseindex;

import it.unimi.di.sweng.reverseindex.output.OutputOrdinatoNumberReferencesStrategy;
import it.unimi.di.sweng.reverseindex.output.OutputSempliceStrategy;
import it.unimi.di.sweng.reverseindex.output.OutputStrategy;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStrategyOutput {
    OutputStrategy SUT;
    Map<String, WordRefs> testIndex = new LinkedHashMap<>();

    @Test
    void testOutputSemplice() {
        SUT = new OutputSempliceStrategy();
        WordRefs wordLa = new WordRefs("la",new TreeSet<>());
        wordLa.add(0);
        wordLa.add(1);
        testIndex.put("la", wordLa);
        WordRefs wordSopra = new WordRefs("Sopra", new TreeSet<>());
        wordSopra.add(1); // Corretto per matchare l'output atteso
        testIndex.put("Sopra", wordSopra);

        assertThat(SUT.output(testIndex)).isEqualTo("""
                la [0, 1]
                Sopra [1]""");
    }

    @Test
    void testOutputOrdinato() {
        SUT = new OutputOrdinatoNumberReferencesStrategy();

        WordRefs wordLa = new WordRefs("la",new TreeSet<>());
        wordLa.add(0);
        wordLa.add(1);
        testIndex.put("la", wordLa);
        WordRefs wordCapra = new WordRefs("capra",new TreeSet<>());
        wordCapra.add(0);
        wordCapra.add(2);
        testIndex.put("capra", wordCapra);
        WordRefs wordSopra = new WordRefs("Sopra", new TreeSet<>());
        wordSopra.add(1); // Corretto per matchare l'output atteso
        testIndex.put("Sopra", wordSopra);

        assertThat(SUT.output(testIndex)).isEqualTo("""
                capra [0, 2]
                la [0, 1]
                Sopra [1]""");
    }
}
