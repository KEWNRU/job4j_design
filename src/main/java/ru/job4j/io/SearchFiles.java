package ru.job4j.io;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;
public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> pathList = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }
    @Override
    public FileVisitResult visitFile(Path root, BasicFileAttributes attrs) {
        if (condition.test(root)) {
            pathList.add(root);
        }
        return CONTINUE;
    }

    public List<Path> getPaths() {
        return pathList;
    }
}