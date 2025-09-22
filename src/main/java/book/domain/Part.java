package book.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite node that groups {@link Verse} instances (e.g., a chapter).
 */

	public class Part implements Text {
    private Book parent;
    private final int number;
    private final List<Verse> verses = new ArrayList<>();

    public Part(int number) {
        this.number = number;
    }

	/** Sets the parent {@link Book} for this part. */
    public void setParent(Book parent) {
        this.parent = parent;
    }
	/** Part number within the book. */
    public int getNumber() {
        return number;
    }
	/** The parent {@link Book} of this part (may be {@code null} if not attached). */
	public Book getParent() { return parent; }

	/**
     * Adds a verse to this part and sets its parent.
     *
     * @param verse the verse to add (must not be {@code null})
     */
    public void addVerse(Verse verse) {
        verse.setParent(this);
        verses.add(verse);
    }

	/** {@inheritDoc} */
	@Override
    public int getNumberOfWords() {
        return verses.stream()
                     .mapToInt(Verse::getNumberOfWords)
                     .sum();
    }

	/**
     * {@inheritDoc}
     */
    @Override
	public List<Verse> getVersesContainingWord(String word) {
		if (word == null || word.trim().isEmpty()) {
        return List.of();
    }
    return verses.stream()
                 .flatMap(v -> v.getVersesContainingWord(word).stream())
                 .toList();
}


}
