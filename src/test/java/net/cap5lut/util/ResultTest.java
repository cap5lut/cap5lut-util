package net.cap5lut.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    @Test
    void getErroneous() {
        final var result = Result.getErroneous("error");
        assertTrue(result.getError().isPresent());
        assertEquals("error", result.getError().orElseThrow(AssertionError::new));
        assertFalse(result.getValue().isPresent());
    }

    @Test
    void getSuccessful() {
        final var result = Result.getSuccessful("test");
        assertFalse(result.getError().isPresent());
        assertTrue(result.getValue().isPresent());
        assertEquals("test", result.getValue().orElseThrow(AssertionError::new));
    }
}