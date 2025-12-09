package it.unimi.di.sweng.reverseindex;

import it.unimi.di.sweng.reverseindex.input.InputStrategy;
import it.unimi.di.sweng.reverseindex.input.NoPunctuationReader;
import it.unimi.di.sweng.reverseindex.input.SimpleReader;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStrategyInput {

    @Test
    void testReaderSemplice() {
        String input = """
                Sopra la panca la capra campa
                sotto la panca
                """;
        InputStrategy SUT = new SimpleReader(new StringReader(input));

        List<Document> documents = SUT.readDocuments();

        assertThat(documents).hasSize(2);
        assertThat(documents.get(0)).isEqualTo(new Document(0,"Sopra la panca la capra campa"));
        assertThat(documents.get(1)).isEqualTo(new Document(1,"sotto la panca"));
    }

    @Test
    void testReaderNoPunteggiatura() {
        String input = """
                Sopra la panca, la capra? campa
                sotto la. panca
                """;
        InputStrategy SUT = new NoPunctuationReader(new StringReader(input));

        List<Document> documents = SUT.readDocuments();

        assertThat(documents).hasSize(2);
        assertThat(documents.get(0)).isEqualTo(new Document(0,"Sopra la panca la capra campa"));
        assertThat(documents.get(1)).isEqualTo(new Document(1,"sotto la panca"));
    }
}
