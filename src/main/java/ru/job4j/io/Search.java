package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments length not equal 2");
        }
        Path start = Paths.get(args[0]);
        if (!Files.isDirectory(start) || !Files.exists(start)) {
            throw new IllegalArgumentException(String.format("Root folder does not exist"));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format("Not a file"));
        }
    }
}