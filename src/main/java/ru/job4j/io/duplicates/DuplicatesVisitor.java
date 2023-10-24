package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty files = new FileProperty(attrs.size(), file.getFileName().toString());
            if (map.containsKey(files)) {
                map.get(files).add(file);
            }
            map.putIfAbsent(files, new ArrayList<Path>(Arrays.asList(file)));
        }

        return super.visitFile(file, attrs);
    }
    public void rsl() {
        for (FileProperty fp : map.keySet()) {
            if (map.get(fp).size() > 1) {
                for (Path patch : map.get(fp)) {
                    System.out.print(patch.toString());
                }
            }
        }
    }

}