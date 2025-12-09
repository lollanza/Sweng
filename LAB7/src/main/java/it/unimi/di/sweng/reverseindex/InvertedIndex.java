package it.unimi.di.sweng.reverseindex;

import it.unimi.di.sweng.reverseindex.input.InputStrategy;
import it.unimi.di.sweng.reverseindex.input.SimpleReader;
import it.unimi.di.sweng.reverseindex.output.OutputSempliceStrategy;
import it.unimi.di.sweng.reverseindex.output.OutputStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;
import java.io.StringReader;
import java.util.*;

public class InvertedIndex {

    private final List<Document> documenti = new ArrayList<>();
    private final Map<String,WordRefs> index = new LinkedHashMap<>();
    private OutputStrategy strategyOutput;

    @Deprecated
    public InvertedIndex(@NotNull Reader input) {
        this(input,false);
    }

    @Deprecated
    public InvertedIndex(@NotNull String input) {
        this(new StringReader(input),false);
    }

    @Deprecated
    public InvertedIndex(@NotNull Reader input, boolean noPunteggiatura){
        try (Scanner sc = new Scanner(input).useDelimiter("\n")){
            int id = 0;
            while (sc.hasNext()){
                String line = sc.next();
                if(noPunteggiatura)
                    line = line.replaceAll("\\p{Punct}","");
                documenti.add(new Document(id++,line));
            }
        }
    }

    @Deprecated
    public InvertedIndex (@NotNull InputStrategy inputStrategy){
        documenti.addAll(inputStrategy.readDocuments());
    }

    @Deprecated
    public InvertedIndex(StringReader input, OutputStrategy outputStrategy) {
        this(input);
        strategyOutput = outputStrategy;
    }

    @Deprecated
    public InvertedIndex(InputStrategy inputStrategy, OutputStrategy outputStrategy) {
        documenti.addAll(inputStrategy.readDocuments());
        strategyOutput = outputStrategy;
    }

    public static class BuiliderInvertedIndex{
        private OutputStrategy outputStrategy;
        private InputStrategy inputStrategy;

        public BuiliderInvertedIndex(Reader reader){
            inputStrategy = new SimpleReader(reader);
            outputStrategy = new OutputSempliceStrategy();
        }

        public BuiliderInvertedIndex withInputStrategy(InputStrategy inputStrategy){
            this.inputStrategy = inputStrategy;
            return this;
        }

        public BuiliderInvertedIndex withOutputStrategy(OutputStrategy outputStrategy) {
            this.outputStrategy = outputStrategy;
            return this;
        }

        public InvertedIndex build() {
            return new InvertedIndex(this);
        }
    }

    private InvertedIndex(BuiliderInvertedIndex builiderInvertedIndex) {
        documenti.addAll(builiderInvertedIndex.inputStrategy.readDocuments());
        this.strategyOutput = builiderInvertedIndex.outputStrategy;


    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Document s : documenti) {
            joiner.add(s.toString());
        }
        return joiner.toString();
    }


    public void create() {
        for (Document d : documenti){
            try(Scanner sc = new Scanner(d.body())){
                while (sc.hasNext()){
                    String word = sc.next();
                    index.computeIfAbsent(word,
                            w -> new WordRefs(w,new TreeSet<>())).add(d.id());
                }
            }
        }
    }

    public String output(@Nullable Comparator<WordRefs> comparator) {
        StringJoiner joiner = new StringJoiner("\n");
        List<WordRefs> wordRefs = new ArrayList<>(index.values());
        if(comparator!=null)
            wordRefs.sort(comparator);
        for (var entry : wordRefs) {
            joiner.add("%s %s".formatted(entry.term(),entry.refs()));
        }
        return joiner.toString();
    }
    public String output(){
        return strategyOutput.output(index);
    }
}
