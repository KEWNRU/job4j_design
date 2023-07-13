package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<T>(value, null);
        } else {
           Node<T> last = head;
            for (int i = 1; i < size; i++) {
                last = last.next;
            }
            last.next = new Node<T>(value, null);
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        Node<T> temp = new Node<>(value, head);
        head = temp;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> last = head;
        for (int i = 0; i < index; i++) {
            last = last.next;
        }
        return last.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T element = head.item;
        Node<T> node = head.next;
        head.item = null;
        head.next = null;
        head = node;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> last = head;
            int exprctionModCount = modCount;

            @Override
            public boolean hasNext() {
                if (exprctionModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return last != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = last.item;
                last = last.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}