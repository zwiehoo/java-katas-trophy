package pl.zwiehoo.katastrophy.kata01;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DictionaryPhraseSplitter implements PhraseSplitter {

    private static final int FIRST_WORD_IN_PHRASE_INDEX = 0;
    private final int maxWordsInPhrase;
    private final PhraseDictionary dictionary;

    public DictionaryPhraseSplitter(PhraseDictionary dictionary, int maxWordsInPhrase) {
        this.dictionary = dictionary;
        this.maxWordsInPhrase = maxWordsInPhrase;
    }

    @Override
    public List<String> split(String phraseToSplit) {
        if (isPhraseEmpty(phraseToSplit)) {
            return Collections.emptyList();
        }

        List<String> words = splitPhraseIntoWords(phraseToSplit);

        if (isSingleWordPhrase(words)) {
            return getFirstWordOfPhrase(words);
        } else {
            return splitByKnownPhrases(words);
        }
    }

    private boolean isPhraseEmpty(String phrase) {
        return phrase.equals("");
    }

    private ArrayList<String> splitPhraseIntoWords(String phrase) {
        return Lists.newArrayList(Splitter.on(" ").split(phrase));
    }

    private boolean isSingleWordPhrase(List<String> words) {
        return words.size() == 1;
    }

    private List<String> getFirstWordOfPhrase(List<String> words) {
        String firstWord = Iterables.getFirst(words, null);
        return Collections.singletonList(firstWord);
    }

    private List<String> splitByKnownPhrases(List<String> phraseWords) {
        List<String> splitPhrases = Lists.newArrayList();

        int cursor = FIRST_WORD_IN_PHRASE_INDEX;
        while (cursor < phraseWords.size()) {
            for (int subPhraseWordCount = maxWordsInPhrase; subPhraseWordCount > 0; subPhraseWordCount--) {
                if (hasReachedTheEndOfList(phraseWords, cursor, subPhraseWordCount)) {
                    continue;
                }

                String subPhrase = getSubphraseOfWords(phraseWords, cursor, subPhraseWordCount);

                if (isKnownPhrase(subPhrase)) {
                    splitPhrases.add(subPhrase);
                    cursor += subPhraseWordCount;
                    break;
                }

                if (subPhraseWordCount == 1) {
                    splitPhrases.add(getWordAtCursor(phraseWords, cursor));
                    cursor++;
                }
            }
        }

        return splitPhrases;
    }

    private boolean hasReachedTheEndOfList(List<String> phraseWords, int cursor, int subPhraseWordCount) {
        return cursor >= phraseWords.size() - subPhraseWordCount + 1;
    }

    private String getSubphraseOfWords(List<String> phraseWords, int offset, int wordCount) {
        return Joiner.on(" ").join(phraseWords.subList(offset, offset + wordCount));
    }

    private boolean isKnownPhrase(String multiWordPhrase) {
        return dictionary.contains(multiWordPhrase);
    }

    private String getWordAtCursor(List<String> phraseWords, int cursor) {
        return phraseWords.get(cursor);
    }
}
