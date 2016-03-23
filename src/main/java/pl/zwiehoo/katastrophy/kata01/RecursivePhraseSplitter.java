package pl.zwiehoo.katastrophy.kata01;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.*;

public class RecursivePhraseSplitter implements PhraseSplitter {
    public static final String WORD_SEPARATOR = " ";
    private final Set<String> dictionary;
    private final int maxWordsCount;

    public RecursivePhraseSplitter(Set<String> dictionary, int maxWordsCount) {
        this.dictionary = dictionary;
        this.maxWordsCount = maxWordsCount;
    }

    @Override
    public List<String> split(String phrase) {
        if (phrase.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> words = splitToSingleWords(phrase);
        List<String> result = Lists.newArrayList();

        while (someWordsLeft(words)) {
            List<String> resultPhraseWords = splitByWordCount(words, maxWordsCount);
            result.add(toPhrase(resultPhraseWords));

            words = CollectionHelper.drop(resultPhraseWords.size(), words);
        }

        return result;
    }

    private ArrayList<String> splitToSingleWords(String phrase) {
        return Lists.newArrayList(
                Splitter.on(" ").split(phrase)
        );
    }

    private List<String> splitByWordCount(List<String> words, int wordCount) {
        List<String> sublistOfWords = CollectionHelper.take(wordCount, words);

        if (isSingleWordPhrase(sublistOfWords)) {
            return sublistOfWords;
        }

        String phrase = toPhrase(sublistOfWords).toLowerCase();

        if (isKnownPhrase(phrase)) {
            return sublistOfWords;
        } else {
            return splitByWordCount(words, wordCount - 1);
        }
    }

    private boolean isSingleWordPhrase(Collection<String> words) {
        return words.size() == 1;
    }

    private String toPhrase(List<String> words) {
        return Joiner.on(WORD_SEPARATOR).join(words);
    }

    private boolean isKnownPhrase(String phrase) {
        return dictionary.contains(phrase);
    }

    private boolean someWordsLeft(List<String> words) {
        return !words.isEmpty();
    }
}
