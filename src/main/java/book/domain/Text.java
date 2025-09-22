package book.domain;

import java.util.List;

/**
 * Common contract for every node in the text hierarchy
 * ({@link AllBooks}, {@link Book}, {@link Part}, {@link Verse}).
 *
 * <p><b>Rules:</b>
 * <ul>
 *   <li><b>Word count:</b> ignore punctuation, split on whitespace.</li>
 *   <li><b>Search:</b> match whole words (case-insensitive).</li>
 * </ul>
 */
public interface Text {

    /**
     * Returns the total number of words in this node (and all descendants for composites).
     *
     * @return total word count (never negative)
     */
    int getNumberOfWords();

    /**
     * Finds all {@link Verse} leaves that contain the given whole word (case-insensitive).
     *
     * @param word the word to search for; if {@code null} or blank, returns an empty list
     * @return an immutable list of matching verses (may be empty)
     */
    List<Verse> getVersesContainingWord(String word);
}

