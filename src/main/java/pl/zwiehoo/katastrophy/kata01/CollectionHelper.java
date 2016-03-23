package pl.zwiehoo.katastrophy.kata01;

import java.util.List;

public class CollectionHelper {
    public static <T> List<T> take(int numElements, List<T> list) {
        if (numElements > list.size()) {
            numElements = list.size();
        }
        return list.subList(0, numElements);
    }

    public static <T> List<T> drop(int numElements, List<T> list) {
        return list.subList(numElements, list.size());
    }
}
