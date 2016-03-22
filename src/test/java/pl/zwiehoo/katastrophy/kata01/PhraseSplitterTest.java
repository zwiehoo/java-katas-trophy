package pl.zwiehoo.katastrophy.kata01;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PhraseSplitterTest {

    private static final String SINGLE_WORD = "word";
    private static final String COMPLEX_PHRASE = "The best preparation for tomorrow is doing your best today";

    private PhraseSplitter createPhraseSplitter(Set<String> knownPhrases) {
        // Replace with your own instance of PhraseSplitter
        return Mockito.mock(PhraseSplitter.class);
    }

    @Test
    public void shouldReturnSingleWordGivenSingleWord() {
        // Given
        PhraseSplitter phraseSplitter = createPhraseSplitter(Sets.newHashSet());

        // When
        List<String> words = phraseSplitter.split(SINGLE_WORD);

        // Then
        assertEquals(Collections.singletonList(SINGLE_WORD), words);
    }

    @Test
    public void shouldReturnPhraseCorrectlySplitByGivenComplexPhrase() {
        // Given
        HashSet<String> knownPhrases = Sets.newHashSet("the best", "is doing");
        PhraseSplitter phraseSplitter = createPhraseSplitter(knownPhrases);

        // When
        List<String> splitResult = phraseSplitter.split(COMPLEX_PHRASE);

        // Then
        assertEquals(
                Sets.newHashSet("The best", "preparation", "for", "tomorrow", "is doing", "your", "best", "today"),
                splitResult
        );
    }
}