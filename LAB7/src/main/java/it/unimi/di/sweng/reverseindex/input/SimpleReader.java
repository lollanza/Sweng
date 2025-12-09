package it.unimi.di.sweng.reverseindex.input;

import it.unimi.di.sweng.reverseindex.Document;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleReader implements InputStrategy {
    private final Reader reader;
    public SimpleReader(Reader input) {
        reader = input;
    }

    @Override
    public List<Document> readDocuments() {
        List<Document> documents = new ArrayList<>();
        try (Scanner sc = new Scanner(reader).useDelimiter("\n")){
            int id = 0;
            while (sc.hasNext()){
                documents.add(new Document(id++,sc.next()));
            }
        }
        return documents;
    }
}
