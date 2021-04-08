package net.cap5lut.util.function;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsumerExTest {
    @Test
    void staticUnchecked() {
        class ExpectedException extends Exception {}
        assertThrows(
                CompletionException.class,
                () -> ConsumerEx.unchecked(
                        value -> {
                            throw new ExpectedException();
                        }
                ).accept(null)
        );
    }

    @Test
    void andThen() {
        final var counter = new int[] {0};
        final ConsumerEx<Object, RuntimeException> consumer = value -> counter[0]++;
        consumer.andThen(consumer).accept(null);
        assertEquals(2, counter[0]);
    }

    @Test
    void unchecked() {
        final class ExpectedException extends Exception {}
        final ConsumerEx<Object, Exception> consumer = value -> {
            throw new ExpectedException();
        };
        assertThrows(ExpectedException.class, () -> consumer.accept(null));
        assertThrows(CompletionException.class, () -> consumer.unchecked().accept(null));
    }
}