package ru.job4j.list;

import java.util.List;

public class RepositionElement {
    public static List<String> changePosition(List<String> list, int index) {
        for (index = 0; index < list.size(); index++) {
            list.remove(index);
            list.set(index, "el");
        }
        return list;
    }
}