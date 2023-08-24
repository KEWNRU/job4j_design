package ru.job4j.io;

import java.io.FileOutputStream;

public class Calculator {
    public static void main(String[] args) {
        int sixDivTwo = 6 / 2;
        System.out.println(sixDivTwo);
        int fiveMinusTwo = 5 - 2;
        System.out.println(fiveMinusTwo);
        int fourTimeTwo = 4 * 2;
        System.out.println(fourTimeTwo);

        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write((String.valueOf(sixDivTwo)).getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write((String.valueOf(fiveMinusTwo)).getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write((String.valueOf(fourTimeTwo)).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

