package book.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Root composite representing a collection of {@link Book}s.
 */
public class AllBooks implements Text {
    private final List<Book> books = new ArrayList<>();

	/**
     * Adds a book to the collection.
     *
     * @param book the book to add (must not be {@code null})
     */
    public void addBook(Book book) {
        books.add(book);
    }

	/** {@inheritDoc} */
	@Override
    public int getNumberOfWords() {
        return books.stream()
                    .mapToInt(Book::getNumberOfWords)
                    .sum();
    }

	/** {@inheritDoc} */
	@Override
	public List<Verse> getVersesContainingWord(String word) {
		if (word == null || word.trim().isEmpty()) {
        return List.of();
    }
    return books.stream()
                .flatMap(b -> b.getVersesContainingWord(word).stream())
                .toList();
	}
}
