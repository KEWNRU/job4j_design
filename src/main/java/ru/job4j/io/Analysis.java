package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder builder = new StringBuilder();
            boolean server = false;
            while (read.ready()) {
                String line = read.readLine();
                if (server == (line.startsWith("200") || line.startsWith("300"))) {
                    server = !server;
                   builder.append(line.substring(4)).append(server ? ";" : System.lineSeparator());
                }
            }
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}