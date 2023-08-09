package ru.job4j.map;

import com.sun.jdi.connect.Connector;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            capacity *= 2;
            expand();
        }
        int hashCodeKey = hash(Objects.hashCode(key));
        int index = indexFor(hashCodeKey);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                int index = getIndex(map.key);
                newTable[index] = map;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (key != null) {
           Objects.equals(key, null);
        }
        if (table[index] != null && table[index].key == key) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (key != null) {
            Objects.equals(key, null);
        }
        if (table[index] != null) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = 0;
            int exprctionModCount = modCount;

            @Override
            public boolean hasNext() {
                if (exprctionModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}