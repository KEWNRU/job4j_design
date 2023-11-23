package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Random random = new Random();
        List<String> phrases = readPhrases();
        List<String> logs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        boolean chek = true;
        while (!OUT.equals(line)) {
            logs.add(line);
            if (STOP.equals(line)) {
                chek = false;
            } else if (CONTINUE.equals(line)) {
                chek = true;
            }
            if (chek) {
                String answer = phrases.get(random.nextInt(phrases.size()));
                System.out.println(answer);
                logs.add(answer);
            }
            line = scanner.nextLine();
        }
        logs.add(line);
        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            list = read.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logs.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
