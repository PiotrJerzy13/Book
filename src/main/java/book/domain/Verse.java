package book.domain;
import java.util.List;

/**
 * Leaf node representing a single verse.
 */
public class Verse implements Text{
    private Part parent;
    private final int number;
    private final String content;

    public Verse(int number, String content) {
        this.number = number;
        this.content = content;
    }
	/** Verse text content. */
    public String getContent() {
        return content;
    }
	/** Verse number within its part. */
	public int getNumber() {
        return number;
    }
	/** Sets the parent {@link Part} of this verse. */
    public void setParent(Part parent) {
		this.parent = parent;
    }
	/** The parent {@link Part} of this verse (may be {@code null} if not attached). */
	public Part getParent() { return parent; }

	/** {@inheritDoc} */
	@Override
	public int getNumberOfWords() {
		String cleaned = content.replaceAll("[\\p{Punct}]", " ").trim();
		if (cleaned.isEmpty()) return 0;
		return cleaned.split("\\s+").length;
	}

	/** {@inheritDoc} */
	@Override
	public List<Verse> getVersesContainingWord(String word) {
    if (word == null || word.trim().isEmpty()) return List.of();
    var p = java.util.regex.Pattern.compile(
        "\\b" + java.util.regex.Pattern.quote(word) + "\\b",
        java.util.regex.Pattern.CASE_INSENSITIVE
        | java.util.regex.Pattern.UNICODE_CASE
        | java.util.regex.Pattern.UNICODE_CHARACTER_CLASS
    );
    return p.matcher(content).find() ? List.of(this) : List.of();
	}

	    /**
     * Formats the verse location and content in the usual Bible style, e.g.:
     * <pre>{@code Genesis 2,13 "I have set my rainbow in the clouds"}</pre>
     *
     * @return formatted string {@code <Book> <part>,<verse> "<content>"};
     *         if parents are missing, book/part may be omitted.
     */

	public String format() {
    if (parent == null || parent.getParent() == null) {
        throw new IllegalStateException("Verse must belong to a Part and a Book to be formatted.");
    }
    Book book = parent.getParent();
    return book.getTitle() + " " + parent.getNumber() + "," + number + " \"" + content + "\"";
}
}
