package net.cap5lut.util.function;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class BiConsumerExTest {
    @Test
    void staticUnchecked() {
        class ExpectedException extends Exception {}
        assertThrows(
                CompletionException.class,
                () -> BiConsumerEx.unchecked(
                        (left, right) -> {
                            throw new ExpectedException();
                        }
                ).accept(null, null)
        );
    }

    @Test
    void andThen() {
        final var counter = new int[] {0};
        final BiConsumerEx<Object, Object, RuntimeException> consumer = (left, right) -> counter[0]++;
        consumer.andThen(consumer).accept(null, null);
        assertEquals(2, counter[0]);
    }

    @Test
    void unchecked() {
        class ExpectedException extends Exception {}
        final BiConsumerEx<Object, Object, Exception> consumer = (left, right) -> {
            throw new ExpectedException();
        };
        assertThrows(ExpectedException.class, () -> consumer.accept(null, null));
        assertThrows(CompletionException.class, () -> consumer.unchecked().accept(null, null));
    }
}