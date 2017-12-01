package ru.job4j.iterator;

import java.util.Iterator;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            Iterator<Integer> innerIterator;

            @Override
            public boolean hasNext() {
                return it.hasNext() || (innerIterator != null && innerIterator.hasNext());
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    if (innerIterator == null || !innerIterator.hasNext()) {
                        innerIterator = it.next();
                    }
                    return innerIterator.next();
                } else {
                    return null;
                }
            }
        };
    }
}
