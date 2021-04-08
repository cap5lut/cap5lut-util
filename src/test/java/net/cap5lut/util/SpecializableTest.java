package net.cap5lut.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecializableTest {
    @Test
    void as() {
        class TestClass implements Specializable<Object> {}
        assertTrue(new TestClass().as(TestClass.class).isPresent());
        assertFalse(new TestClass().as(String.class).isPresent());
    }
}