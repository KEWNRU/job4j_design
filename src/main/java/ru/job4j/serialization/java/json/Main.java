package ru.job4j.serialization.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Gamer gamer = new Gamer(true, 26, new Rank("level5"),
                new String[]{"CS2, Faceit, Play"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(gamer));

        final String personJson =
                "{"
                        + "\"game\":true,"
                        + "\"age\":26,"
                        + "\"rank\":"
                        + "{"
                        + "\"rank\":\"level5\""
                        + "},"
                        + "\"status\":"
                        + "[\"CS2\",\"Faceit\",\"Play\"]"
                        + "}";
        final Gamer personMod = gson.fromJson(personJson, Gamer.class);
        System.out.println(personMod);
    }
}