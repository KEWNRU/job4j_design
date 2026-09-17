package ru.job4j.iterator;

import java.awt.dnd.DragSourceListener;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    int i;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    public boolean hasNext() {
        if (!data.isEmpty()) {
            return true;
        }
        return false;
    }
    public T next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        T rsl = data.get(i);
        i++;
        if (i == data.size()) {
            i = 0;
        }
        return rsl;
    }
}