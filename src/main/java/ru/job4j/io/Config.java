package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while (read.ready()) {
                String s = read.readLine();
                if (!s.startsWith("#") && s.length() != 0) {
                    if (s.endsWith("=") && s.indexOf("=") == s.lastIndexOf("=")) {
                        throw new IllegalArgumentException("String not have key and value");
                    }
                    if (s.contains("=") && s.startsWith("=")) {
                        throw new IllegalArgumentException("String not have key");
                    }
                    String[] r = s.split("=", 2);
                    values.put(r[0], r[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}