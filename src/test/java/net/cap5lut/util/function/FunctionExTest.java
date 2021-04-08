package net.cap5lut.util.function;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExTest {
    @Test
    void staticUnchecked() {
        class ExpectedException extends Exception {}
        assertThrows(
                CompletionException.class,
                () -> FunctionEx.unchecked(
                        value -> {
                            throw new ExpectedException();
                        }
                ).apply(null)
        );
    }

    @Test
    void unchecked() {
        final class ExpectedException extends Exception {}
        final FunctionEx<Object, Object, Exception> function = value -> {
            throw new ExpectedException();
        };
        assertThrows(ExpectedException.class, () -> function.apply(null));
        assertThrows(CompletionException.class, () -> function.unchecked().apply(null));
    }
}