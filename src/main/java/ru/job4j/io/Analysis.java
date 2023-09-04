package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            boolean server = false;
            while (read.ready()) {
                String line = read.readLine();
                String[] status = line.split(" ");
                if (server && (!line.startsWith("400") && !line.startsWith("500"))) {
                    server = false;
                    writer.write(String.format("%s%s%s", status[1], "; ", System.lineSeparator()));
                }
                if (!server && (line.startsWith("400") || line.startsWith("500"))) {
                    server = true;
                    writer.write(String.format("%s%s", status[1], "; "));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}