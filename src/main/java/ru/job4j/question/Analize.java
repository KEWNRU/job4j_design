package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        Info result = new Info(0, 0, 0);
        int added = 0;
        int changed = 0;
        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!map.containsKey(user.getId())) {
                added++;
                result.setAdded(added++);
            }
            if (map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                changed++;
                result.setChanged(changed);
            }
            map.remove(user.getId());

        }
        result.setDeleted(map.size());
        return result;
    }

}