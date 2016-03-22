package pl.zwiehoo.katastrophy.kata01;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SetPhraseDictionaryTest {

    public static final String ANY_PHRASE = "any phrase";

    @Test
    public void shouldReturnFalseForEmptyDictionary() {
        // Given
        Set<String> emptyDictionary = Sets.newHashSet();
        PhraseDictionary setPhraseDictionary = new SetPhraseDictionary(emptyDictionary);

        // When
        boolean contains = setPhraseDictionary.contains(ANY_PHRASE);

        // Then
        assertFalse(contains);
    }

    @Test
    public void shouldReturnFalseForUnknownPhrase() {
        // Given
        Set<String> dictionary = Sets.newHashSet("known", "words");
        PhraseDictionary setPhraseDictionary = new SetPhraseDictionary(dictionary);

        // When
        boolean contains = setPhraseDictionary.contains("unknown");

        // Then
        assertFalse(contains);
    }

    @Test
    public void shouldReturnTrueForKnownPhrase() {
        // Given
        Set<String> dictionary = Sets.newHashSet("known", "words");
        PhraseDictionary setPhraseDictionary = new SetPhraseDictionary(dictionary);

        // When
        boolean contains = setPhraseDictionary.contains("known");

        // Then
        assertTrue(contains);
    }

    @Test
    public void shouldReturnTrueIfKnownWordIsGivenInDifferntCase() {
        // Given
        Set<String> dictionary = Sets.newHashSet("known", "words");
        PhraseDictionary setPhraseDictionary = new SetPhraseDictionary(dictionary);

        // When
        boolean contains = setPhraseDictionary.contains("KnOwN");

        // Then
        assertTrue(contains);
    }
}