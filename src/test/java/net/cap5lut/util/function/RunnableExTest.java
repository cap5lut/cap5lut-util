package net.cap5lut.util.function;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RunnableExTest {
    @Test
    void staticUnchecked() {
        class ExpectedException extends Exception {}
        assertThrows(
                CompletionException.class,
                () -> RunnableEx.unchecked(
                        () -> {
                            throw new ExpectedException();
                        }
                ).run()
        );
    }

    @Test
    void unchecked() {
        final class ExpectedException extends Exception {}
        final RunnableEx<Exception> runnable = () -> {
            throw new ExpectedException();
        };
        assertThrows(ExpectedException.class, runnable::run);
        assertThrows(CompletionException.class, () -> runnable.unchecked().run());
    }
}