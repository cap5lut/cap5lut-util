package net.cap5lut.util.function;

import java.util.concurrent.CompletionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@link BiConsumer} which can throw exceptions.
 */
@FunctionalInterface
public interface BiConsumerEx<T, U, E extends Throwable> {
    /**
     * Converts the checked consumer to an unchecked consumer.
     *
     * @param consumer checked consumer
     * @return unchecked consumer
     */
    static <T, U, E extends Throwable> BiConsumer<T, U> unchecked(BiConsumerEx<T, U, E> consumer) {
        return consumer.unchecked();
    }

    /**
     * Consumes two values.
     *
     * @param value0 value 0 to consume
     * @param value1 value 1 to consume
     * @throws E possibly thrown error
     */
    void accept(T value0, U value1) throws E;

    /**
     * Appends another consumer.
     *
     * @param after consumer to append
     * @return combined consumer
     */
    default BiConsumerEx<T, U, E> andThen(BiConsumerEx<T, U, E> after) {
        return (value0, value1) -> {
            this.accept(value0, value1);
            after.accept(value0, value1);
        };
    }

    /**
     * Gets the unchecked variant.
     * Note: checked exceptions are wrapped in {@link CompletionException}s.
     *
     * @return unchecked variant
     */
    default BiConsumer<T, U> unchecked() {
        return (value0, value1) -> {
            try {
                accept(value0, value1);
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new CompletionException(e);
            }
        };
    }
}