package ru.job4j.serialization.java.json;

public class Rank {

    private final String rank;

    public Rank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Rank{"
                + "rank'" + rank + '\''
                + '}';
    }

    public String getRank() {
        return rank;
    }
}
