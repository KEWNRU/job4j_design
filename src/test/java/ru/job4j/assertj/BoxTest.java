package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisNumberOfVertices() {
        Box box = new Box(4, 8);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    void isThisNumberOfVertices0() {
        Box box = new Box(2, 8);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(8, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(2, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void intGetArea() {
        Box box = new Box(4, 10);
        int rsl = (int) box.getArea();
        assertThat(rsl).isEqualTo(173);
    }
}