package book;

import book.domain.AllBooks;
import book.domain.Verse;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        AllBooks allBooks = TestDataCreator.createAllBooks();

        // word count
        int numberOfWords = allBooks.getNumberOfWords();
        System.out.println("Number of all words: " + numberOfWords);

        // contain word
        List<Verse> verses = allBooks.getVersesContainingWord("God");
        System.out.println("Verses containing the word 'God':");
        for (Verse v : verses) {
            System.out.println(v.format());
        }
    }

}
