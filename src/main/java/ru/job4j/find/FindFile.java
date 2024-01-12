package ru.job4j.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import ru.job4j.serialization.java.json.A;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FindFile {
    private Search search;

    private static List<Path> searchBy(ArgsName argsName) throws IOException {
        List<Path> list = new ArrayList<>();
        if ("name".equals(argsName.get("t"))) {
            list = Search.search(Paths.get(argsName.get("d")),
                    path -> path.toFile().getName().endsWith(argsName.get("n")));
        }
        if ("regex".equals(argsName.get("t"))) {
            list = Search.search(Paths.get(argsName.get("d")),
                    path -> path.toFile().getName().matches("n"));
        }
        if ("mask".equals(argsName.get("t"))) {
            list = Search.search(Paths.get(argsName.get("d")),
                    path -> path.toFile().getName().matches(argsName.get("n")
                            .replace("*", ".+")
                            .replace("?", ".")
                            .replace(".", "[.]")));
        }
        return list;
    }

    public static void validation(ArgsName argsName) {
        File d = Paths.get(argsName.get("d")).toFile();
        if (!d.exists()) {
            throw new IllegalArgumentException("don't exist");
        }
        if (!d.isDirectory()) {
            throw new IllegalArgumentException("don't directory");
        }
        String[] s = argsName.get("o").split("\\.");
        if (s.length != 2 || !argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("not file.txt");
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException("not file name, regex, mask");
        }
        if (argsName.get("t").isEmpty()) {
            throw new IllegalArgumentException("search  must be by name, regex or mask");
        }
    }

    private static void saveLog(String out, List<Path> find) {
        try (PrintStream log = new PrintStream(new FileOutputStream(out))) {
            find.forEach(log::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter 4 arguments");
        }
        ArgsName argsName = new ArgsName();
        searchBy(argsName);
        validation(argsName);
        saveLog(argsName.get("o"), searchBy(argsName));
    }
}
