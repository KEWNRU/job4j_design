package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    //Проверяем что длина масива равно размеру
    //Копируем старый масив и увеличиваем новый
    //Увеличиваем размер и счетчик
    @Override
    public void add(T value) {
        if (container.length == size) {
            container = grow();
        }
        size++;
        modCount++;
    }

    // Находим элемент по индексу и меняем его
    // В противном случае выкидываем исключение
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T old = get(index);
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T old = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return old;
    }

    //Ищем элемент по индексу в противном случае выкидываем исключение
    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
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
            final int exprctionModCount = modCount;

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
    public T[] grow() {
        return container.length == 0 ? Arrays.copyOf(
                container, container.length + 2)
                : Arrays.copyOf(container, container.length * 2);
    }
}