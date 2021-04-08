package net.cap5lut.util.function;

import java.util.concurrent.CompletionException;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * {@link Function} which can throw exceptions.
 */
@FunctionalInterface
public interface FunctionEx<T, R, E extends Throwable> {
    /**
     * Converts the checked function to an unchecked function.
     *
     * @param function checked function
     * @return unchecked function
     */
    static <T, R, E extends Throwable> Function<T, R> unchecked(FunctionEx<T, R, E> function) {
        return function.unchecked();
    }

    /**
     * Computes a value from a given value.
     *
     * @param value value
     * @return computed result
     * @throws E possibly thrown error
     */
    R apply(T value) throws E;

    /**
     * Gets the unchecked variant.
     * Note: checked exceptions are wrapped in {@link CompletionException}s.
     *
     * @return unchecked variant
     */
    default Function<T, R> unchecked() {
        return value -> {
            try {
                return apply(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new CompletionException(e);
            }
        };
    }
}