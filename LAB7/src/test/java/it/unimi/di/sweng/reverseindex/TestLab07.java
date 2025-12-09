package it.unimi.di.sweng.reverseindex;

import it.unimi.di.sweng.reverseindex.input.NoPunctuationReader;
import it.unimi.di.sweng.reverseindex.output.OutputAllineato;
import it.unimi.di.sweng.reverseindex.output.OutputOrdinatoAlfabeticamenteStrategy;
import it.unimi.di.sweng.reverseindex.output.OutputOrdinatoNumberReferencesStrategy;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static it.unimi.di.sweng.reverseindex.WordRefs.CASEINSENSITIVE;
import static it.unimi.di.sweng.reverseindex.WordRefs.NUMBER_REFERECES_CMP;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLab07 {

    private InvertedIndex SUT;
    private String input = """
                Sopra la panca la capra campa
                sotto la panca
                """;

    @Test
    void testLetturaDocumentiU01() {
        String input = """
                Sopra la panca la capra campa!
                sotto la panca""";
        SUT = new InvertedIndex(input);
        assertThat(SUT).asString().isEqualTo(
                """
                        0: Sopra la panca la capra campa!
                        1: sotto la panca""");
    }

    @Test
    void testLetturaDocumentiDaReaderU09() {
        String input = """
                Sopra la panca la capra campa!
                sotto la panca""";
         SUT = new InvertedIndex(new StringReader(input));
        assertThat(SUT).asString().isEqualTo(
                """
                        0: Sopra la panca la capra campa!
                        1: sotto la panca""");
    }

    @Test
    void testCreaIndexSempliceU06() {

         SUT = new InvertedIndex(new StringReader(input));

        SUT.create();
        assertThat(SUT.output(null)).isEqualTo("""
                Sopra [0]
                la [0, 1]
                panca [0, 1]
                capra [0]
                campa [0]
                sotto [1]""");
    }

    @Test
    void testU04() {


        SUT = new InvertedIndex(new StringReader(input));

        SUT.create();

        assertThat(SUT.output(CASEINSENSITIVE)).isEqualTo("""
                campa [0]
                capra [0]
                la [0, 1]
                panca [0, 1]
                Sopra [0]
                sotto [1]""");

    }

    @Test
    void testU03() {

        SUT = new InvertedIndex(new StringReader(input));

        SUT.create();

        assertThat(SUT.output(NUMBER_REFERECES_CMP)).isEqualTo("""
                la [0, 1]
                panca [0, 1]
                campa [0]
                capra [0]
                Sopra [0]
                sotto [1]""");

    }

    @Test
    void testU05() {

        String input = """
                Sopra, la panca la. capra campa,
                sotto la- panca
                """;

        SUT = new InvertedIndex(new StringReader(input), true);

        SUT.create();

        assertThat(SUT.output(CASEINSENSITIVE)).isEqualTo("""
                campa [0]
                capra [0]
                la [0, 1]
                panca [0, 1]
                Sopra [0]
                sotto [1]""");

    }

    @Test
    void testInputStrategy() {

        String input = """
                Sopra, la panca la. capra campa,
                sotto la- panca
                """;

        SUT = new InvertedIndex(new NoPunctuationReader(new StringReader(input)));

        SUT.create();

        assertThat(SUT.output(CASEINSENSITIVE)).isEqualTo("""
                campa [0]
                capra [0]
                la [0, 1]
                panca [0, 1]
                Sopra [0]
                sotto [1]""");
    }
    @Test
    void testOutputStrategy() {
        SUT = new InvertedIndex(new StringReader(input),new OutputOrdinatoNumberReferencesStrategy());
        SUT.create();
        assertThat(SUT.output()).isEqualTo("""
                la [0, 1]
                panca [0, 1]
                campa [0]
                capra [0]
                Sopra [0]
                sotto [1]""");
    }

    @Test
    void testOutputStrategyOrdinatoalfabeticamente() {
        SUT = new InvertedIndex(new StringReader(input),new OutputOrdinatoAlfabeticamenteStrategy());
        SUT.create();
        assertThat(SUT.output()).isEqualTo("""
                campa [0]
                capra [0]
                la [0, 1]
                panca [0, 1]
                Sopra [0]
                sotto [1]""");
    }

    @Test
    void tesstUnioneStrategy() {
        SUT = new InvertedIndex(new NoPunctuationReader(new StringReader(input)),
                new OutputOrdinatoAlfabeticamenteStrategy());
        SUT.create();
        assertThat(SUT.output()).isEqualTo("""
                campa [0]
                capra [0]
                la [0, 1]
                panca [0, 1]
                Sopra [0]
                sotto [1]""");
    }

    @Test
    void testInizializzazioneBuilder() {
        SUT = new InvertedIndex.BuiliderInvertedIndex(new StringReader(input)).build();
        SUT.create();
        assertThat(SUT.output()).isEqualTo("""
        Sopra [0]
        la [0, 1]
        panca [0, 1]
        capra [0]
        campa [0]
        sotto [1]""");
    }


    @Test
    void testInizializzazioneBuilderConStrategie() {
        SUT = new InvertedIndex.BuiliderInvertedIndex(new StringReader(input)).build();
        SUT.create();
        assertThat(SUT.output()).isEqualTo("""
        Sopra [0]
        la [0, 1]
        panca [0, 1]
        capra [0]
        campa [0]
        sotto [1]""");
    }


    @Test
    void testU10() {
        SUT = new InvertedIndex.BuiliderInvertedIndex(new StringReader(input)).
            withOutputStrategy(new OutputAllineato()).build();
        SUT.create();
        assertThat(SUT.output()).isEqualTo("""
            Sopra   [0]
            la      [0, 1]
            panca   [0, 1]
            capra   [0]
            campa   [0]
            sotto   [1]""");
    }
}