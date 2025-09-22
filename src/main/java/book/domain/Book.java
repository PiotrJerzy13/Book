package book.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite node that groups {@link Part} instances (e.g., a book like "Genesis").
 */
public class Book implements Text {
    private final String title;
    private final List<Part> parts = new ArrayList<>();

    public Book(String title) {
        this.title = title;
    }

	/** The display title of this book (e.g., "Genesis"). */
    public String getTitle() {
        return title;
    }

	/** Adds a part to this book and sets its parent. */
    public void addPart(Part part) {
        part.setParent(this);
        parts.add(part);
    }

	/** {@inheritDoc} */
    @Override
    public int getNumberOfWords() {
        // Sum words of all parts (children)
        return parts.stream()
                    .mapToInt(Part::getNumberOfWords)
                    .sum();
    }

	/** {@inheritDoc} */
	@Override
	public List<Verse> getVersesContainingWord(String word) {
		if (word == null || word.trim().isEmpty()) {
        return List.of();
    }
    return parts.stream()
                .flatMap(p -> p.getVersesContainingWord(word).stream())
                .toList();
}

}
