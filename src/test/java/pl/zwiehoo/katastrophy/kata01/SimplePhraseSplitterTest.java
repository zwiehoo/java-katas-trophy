package pl.zwiehoo.katastrophy.kata01;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimplePhraseSplitterTest {

    private static final String EMPTY_PHRASE = "";
    private static final String SINGLE_WORD = "word";
    private static final String UNKNOWN_WORD = "unknown";
    private static final String KNOWN_PHRASES = "known phrases";
    private static final String THREE_WORDS_PHRASE = "one two three";
    private static final String COUNT_WORD = "count";
    private static final String FOR_WORD = "for";
    private static final String ME_WORD = "me";
    private static final int MAX_WORDS_COUNT = 3;

    private RecursivePhraseSplitter createPhraseSplitter() {
        HashSet<String> dictionary = Sets.newHashSet(KNOWN_PHRASES, THREE_WORDS_PHRASE);
        return new RecursivePhraseSplitter(dictionary, MAX_WORDS_COUNT);
    }

    @Test
    public void testEmptyPhrase() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split(EMPTY_PHRASE);

        // Then
        assertEquals(Collections.emptyList(), split);
    }

    @Test
    public void testSingleWord() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split(SINGLE_WORD);

        // Then
        assertEquals(Collections.singletonList(SINGLE_WORD), split);
    }

    @Test
    public void testTwoUnknownWords() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split("unknown words");

        // Then
        assertEquals(Arrays.asList(UNKNOWN_WORD, "words"), split);
    }

    @Test
    public void testOneKnownPhraseInTheBeginning() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split("known phrases found");

        // Then
        assertEquals(Arrays.asList(KNOWN_PHRASES, "found"), split);
    }

    @Test
    public void testOneKnownPhrase() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split("unknown known phrases unknown");

        // Then
        assertEquals(Arrays.asList(UNKNOWN_WORD, KNOWN_PHRASES, UNKNOWN_WORD), split);
    }

    @Test
    public void testOnwKnownPhraseAtTheEnd() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split("unknown word known phrases");

        // Then
        assertEquals(Arrays.asList(UNKNOWN_WORD, SINGLE_WORD, KNOWN_PHRASES), split);
    }

    @Test
    public void testThreeWordsPhrase() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split("count one two three for me");

        // Then
        assertEquals(Arrays.asList(COUNT_WORD, THREE_WORDS_PHRASE, FOR_WORD, ME_WORD), split);
    }

    @Test
    public void testPhraseWithUppercaseLetters() {
        // Given
        RecursivePhraseSplitter recursivePhraseSplitter = createPhraseSplitter();

        // When
        List<String> split = recursivePhraseSplitter.split("Count oNe twO tHree for Me");

        // Then
        assertEquals(Arrays.asList("Count", "oNe twO tHree", "for", "Me"), split);
    }
}