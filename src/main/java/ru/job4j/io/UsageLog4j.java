package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte list = 11;
        float list1 = 33.1f;
        double list2 = 22.5d;
        boolean list3 = false;
        char a = 'a';
        long list4 = -2L;
        short list5 = 21;
        LOG.debug("User info name : {}, age : {}, list : {}, list1 {}, list2 {}, list3 {}, list4 {}, list5 {}, char {}",
                name, age, list, list1, list2, list3, list4, list5, a);
    }
}