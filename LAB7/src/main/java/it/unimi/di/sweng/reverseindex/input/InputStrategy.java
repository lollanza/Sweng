package it.unimi.di.sweng.reverseindex.input;

import it.unimi.di.sweng.reverseindex.Document;

import java.util.List;

public interface InputStrategy {
    List<Document> readDocuments();
}
