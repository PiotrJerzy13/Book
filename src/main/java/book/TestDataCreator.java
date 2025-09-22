package book;

import book.domain.AllBooks;
import book.domain.Book;
import book.domain.Part;
import book.domain.Verse;

public class TestDataCreator {
    public static AllBooks createAllBooks() {
        AllBooks newAllBooks = new AllBooks();

        newAllBooks.addBook(createBookGenesis());
        newAllBooks.addBook(createBookMatthew());

        return newAllBooks;
    }

    private static Book createBookGenesis() {
        Book dataStructures = new Book("Data Structures 101");

        Part part1 = new Part(1);
        Part part3 = new Part(3);

        Verse v1 = new Verse(1,
                "Arrays store elements in contiguous memory, enabling O(1) random access.");
        Verse v2 = new Verse(2,
                "Linked lists excel at O(1) insertions/deletions given a node, but have O(n) access.");
        Verse v7 = new Verse(7,
                "A hash table maps keys to buckets via a hash function; good distribution limits collisions.");
        Verse v9 = new Verse(9,
                "Balanced trees (e.g., AVL, Red-Black) maintain O(log n) operations via structural invariants.");

        dataStructures.addPart(part1);
        part1.addVerse(v1);
        part1.addVerse(v2);

        dataStructures.addPart(part3);
        part3.addVerse(v7);
        part3.addVerse(v9);

        return dataStructures;
    }

    private static Book createBookMatthew() {
        Book designPatterns = new Book("Design Patterns");

        Part part1 = new Part(1);
        Part part2 = new Part(2);

        Verse v21 = new Verse(21,
                "Factory Method delegates object creation to subclasses, reducing coupling to concrete types.");
        Verse v22 = new Verse(22,
                "Strategy encapsulates interchangeable algorithms behind a common interface.");
        Verse v23 = new Verse(23,
                "Observer establishes one-to-many updates when subject state changes.");
        Verse v3  = new Verse(3,
                "Dependency Inversion favors abstractions, enabling testability and flexible composition.");
        Verse v4  = new Verse(4,
                "Builder assembles complex objects step by step with a fluent, readable API.");

        designPatterns.addPart(part1);
        part1.addVerse(v21);
        part1.addVerse(v22);
        part1.addVerse(v23);

        designPatterns.addPart(part2);
        part2.addVerse(v3);
        part2.addVerse(v4);

        return designPatterns;
    }
}
