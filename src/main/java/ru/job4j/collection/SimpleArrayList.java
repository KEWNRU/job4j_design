package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

/** Добавляем элемент в коллекцию  и увеличиваем раземер масива */
    @Override
    public void add(T value) {
        if (container.length <= size) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    /** Изминение элемента по индексу */
    @Override
    public T set(int index, T newValue) {
        T old = get(index);
        container[index] = newValue;
        return old;
    }

    /** Удаление элемента по индексу */
    @Override
    public T remove(int index) {
        T old = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return old;
    }

    /** Поиск элемента по индексу*/
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;
            int exprctionModCount = modCount;

            @Override
            public boolean hasNext() {
                if (exprctionModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
    /** Увелечение массива */
    public T[] grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 2);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
        return container;
    }
}