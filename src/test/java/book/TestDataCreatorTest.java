package book;

import book.domain.AllBooks;
import book.domain.Book;
import book.domain.Part;
import book.domain.Verse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TestDataCreatorTest {

    private AllBooks all;

    @BeforeEach
    void setUp() {
        all = TestDataCreator.createAllBooks();
        assertNotNull(all, "createAllBooks() must not return null");
    }

    @Test
    @DisplayName("AllBooks contains two books with expected titles")
    void allBooksHasExpectedTitles() {
        assertEquals(2, all.getBooks().size(), "Expected exactly two books");

        assertTrue(all.getBooks().stream().anyMatch(b -> "Data Structures 101".equals(b.getTitle())),
                "Missing 'Data Structures 101'");
        assertTrue(all.getBooks().stream().anyMatch(b -> "Design Patterns".equals(b.getTitle())),
                "Missing 'Design Patterns'");
    }

    @Test
    @DisplayName("'Data Structures 101' has parts 1 and 3 with expected verses")
    void dataStructuresStructureAndContent() {
        Book ds = findBook("Data Structures 101");
        assertNotNull(ds);

        assertTrue(ds.getParts().stream().anyMatch(p -> p.getNumber() == 1), "Missing part 1");
        assertTrue(ds.getParts().stream().anyMatch(p -> p.getNumber() == 3), "Missing part 3");

        Part p1 = findPart(ds, 1);
        Part p3 = findPart(ds, 3);

        assertEquals(2, p1.getVerses().size(), "Part 1 should have 2 verses");
        assertEquals(2, p3.getVerses().size(), "Part 3 should have 2 verses");

        assertTrue(containsContent(p1, "Arrays store elements in contiguous memory"),
                "Part 1 should contain Arrays verse");
        assertTrue(containsContent(p1, "Linked lists excel"),
                "Part 1 should contain Linked lists verse");

        assertTrue(containsContent(p3, "hash table maps keys"),
                "Part 3 should contain Hash Table verse");
        assertTrue(containsContent(p3, "Balanced trees"),
                "Part 3 should contain Balanced trees verse");
    }

    @Test
    @DisplayName("'Design Patterns' has parts 1 and 2 with expected verses")
    void designPatternsStructureAndContent() {
        Book dp = findBook("Design Patterns");
        assertNotNull(dp);

        Part p1 = findPart(dp, 1);
        Part p2 = findPart(dp, 2);

        assertEquals(3, p1.getVerses().size(), "Part 1 should have 3 verses");
        assertEquals(2, p2.getVerses().size(), "Part 2 should have 2 verses");

        assertTrue(containsContent(p1, "Factory Method delegates object creation"),
                "Part 1 should contain Factory Method");
        assertTrue(containsContent(p1, "Strategy encapsulates interchangeable algorithms"),
                "Part 1 should contain Strategy");
        assertTrue(containsContent(p1, "Observer establishes one-to-many updates"),
                "Part 1 should contain Observer");

        assertTrue(containsContent(p2, "Dependency Inversion favors abstractions"),
                "Part 2 should contain Dependency Inversion");
        assertTrue(containsContent(p2, "Builder assembles complex objects"),
                "Part 2 should contain Builder");
    }

    // --- helpers ---

    private Book findBook(String title) {
        Optional<Book> b = all.getBooks().stream()
                .filter(x -> title.equals(x.getTitle()))
                .findFirst();
        return b.orElse(null);
    }

    private Part findPart(Book book, int number) {
        return book.getParts().stream()
                .filter(p -> p.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Part " + number + " not found in " + book.getTitle()));
    }

    private boolean containsContent(Part part, String snippet) {
        return part.getVerses().stream()
                .map(Verse::getContent)
                .anyMatch(c -> c != null && c.contains(snippet));
    }
}

