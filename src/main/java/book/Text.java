package book;

import book.domain.Verse;
import java.util.List;

public interface Text {
    int getNumberOfWords();

    List<Verse> getVersesContainingWord(String word);
}
