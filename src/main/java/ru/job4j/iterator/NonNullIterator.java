package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index = -1;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findNextNonNull() < data.length;
    }

    @Override
    public Integer next() {
        int found = findNextNonNull();
        if(found >= data.length) {
            throw new NoSuchElementException();
        }
            index = found;
            return data[index];
    }

    private int findNextNonNull() {
        int i = index + 1;
        while (i < data.length && data[i] == null) {
            i++;
        }
        return i;
    }

}