package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenRoleNameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("1", new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteRoleThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.replace("1", new Role("1", "Maxim"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.replace("10", new Role("10", "Maxim"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.delete("1");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.delete("2");
        assertThat(result).isFalse();
    }
}
