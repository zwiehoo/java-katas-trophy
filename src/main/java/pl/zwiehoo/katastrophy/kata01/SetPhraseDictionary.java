package pl.zwiehoo.katastrophy.kata01;

import java.util.Set;

public class SetPhraseDictionary implements PhraseDictionary {
    private final Set<String> dictionary;

    public SetPhraseDictionary(Set<String> dictionary) {
        this.dictionary = dictionary;
    }


    @Override
    public boolean contains(String phrase) {
        return dictionary.contains(phrase.toLowerCase());
    }
}
