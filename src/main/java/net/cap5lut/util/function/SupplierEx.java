package net.cap5lut.util.function;

import java.util.concurrent.CompletionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@link Function} which can throw exceptions.
 */
@FunctionalInterface
public interface SupplierEx<T, E extends Throwable> {
    /**
     * Creates a supplier for a given value.
     *
     * @param value value to supply
     * @param <T> value type
     * @param <E> exception type
     * @return value supplier
     */
    static <T, E extends Throwable> SupplierEx<T, E> of(T value) {
        return () -> value;
    }

    /**
     * Converts the checked supplier to an unchecked supplier.
     *
     * @param supplier checked supplier
     * @return unchecked supplier
     */
    static <T, E extends Throwable> Supplier<T> unchecked(SupplierEx<T, E> supplier) {
        return supplier.unchecked();
    }

    /**
     * Computes a value.
     *
     * @return computed values
     * @throws E possibly thrown error
     */
    T get() throws E;

    /**
     * Gets the unchecked variant.
     * Note: checked exceptions are wrapped in {@link CompletionException}s.
     *
     * @return unchecked variant
     */
    default Supplier<T> unchecked() {
        return () -> {
            try {
                return get();
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new CompletionException(e);
            }
        };
    }
}