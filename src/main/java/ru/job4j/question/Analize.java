package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> prev = new HashMap<>();
        Map<Integer, String> curr = new HashMap<>();
        for (User user : previous) {
            prev.put(user.getId(), user.getName());
        }
        for (User user : current) {
            curr.put(user.getId(), user.getName());
        }
        for (Map.Entry<Integer, String> el : curr.entrySet()) {
            if (!prev.containsKey(el.getKey())) {
                added++;
                result.setAdded(added++);
            }
        }
        for (Map.Entry<Integer, String> el1 : prev.entrySet()) {
            if (!curr.containsKey(el1.getKey())) {
                deleted++;
                result.setDeleted(deleted);
            }
            for (Map.Entry<Integer, String> el2 : curr.entrySet()) {
                if (el1.getKey().equals(el2.getKey())
                        && !el1.getValue().equals(el2.getValue())) {
                    changed++;
                    result.setChanged(changed);
                }
            }

        }
        return result;
    }
}