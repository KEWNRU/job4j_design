package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String[] direct = args[0].split("=", 2);
        String[] exclude = args[1].split("=", 2);
        String[] output = args[2].split("=", 2);
        if (direct[1].isEmpty()) {
            throw new IllegalArgumentException("Directory not found");
        }
        if (exclude[1].isEmpty()) {
            throw  new IllegalArgumentException("Exclude files with the extension class");
        }
        if (output[1].isEmpty()) {
            throw new IllegalArgumentException("What we archive into");
        }

        List<Path> paths = Search.search(Paths.get(direct[1]), p -> p.toFile().getName().endsWith(exclude[1]));
        Zip zip = new Zip();
        zip.packFiles(paths, new File(output[1]));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}