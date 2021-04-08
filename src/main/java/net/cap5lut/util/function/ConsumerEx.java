package net.cap5lut.util.function;

import java.util.concurrent.CompletionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@link Consumer} which can throw exceptions.
 */
@FunctionalInterface
public interface ConsumerEx<T, E extends Throwable> {
    /**
     * Converts the checked consumer to an unchecked consumer.
     *
     * @param consumer checked consumer
     * @return unchecked consumer
     */
    static <T, E extends Throwable> Consumer<T> unchecked(ConsumerEx<T, E> consumer) {
        return consumer.unchecked();
    }

    /**
     * Consumes a value.
     *
     * @param value value to consume
     * @throws E possibly thrown error
     */
    void accept(T value) throws E;

    /**
     * Appends another consumer.
     *
     * @param after consumer to append
     * @return combined consumer
     */
    default ConsumerEx<T, E> andThen(ConsumerEx<T, E> after) {
        return value -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * Gets the unchecked variant.
     * Note: checked exceptions are wrapped in {@link CompletionException}s.
     *
     * @return unchecked variant
     */
    default Consumer<T> unchecked() {
        return value -> {
            try {
                accept(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new CompletionException(e);
            }
        };
    }
}