package ru.job4j.serialization.java.json;

import java.util.Arrays;

public class Gamer {
    private final boolean game;
    private final int age;
    private final Rank rank;
    private final String[] status;

    public Gamer(boolean game, int age, Rank rank, String[] status) {
        this.game = game;
        this.age = age;
        this.rank = rank;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Gamer{"
                + "game=" + game
                + ", age=" + age
                + ", rank=" + rank
                + ", status=" + Arrays.toString(status)
                + '}';
    }
}
