package net.cap5lut.util;

import java.util.Optional;

/**
 * Result.
 *
 * @param <T> result type
 * @param <E> error type
 */
public interface Result<T, E> {
    /**
     * Creates a new erroneous result.
     *
     * @param error error value
     * @param <T> result type
     * @param <E> error type
     * @return new erroneous result
     */
    static <T, E> Result<T, E> getErroneous(E error) {
        return new Result<>() {
            @Override
            public Optional<T> getValue() {
                return Optional.empty();
            }

            @Override
            public Optional<E> getError() {
                return Optional.of(error);
            }
        };
    }

    /**
     * Creates a new successful result.
     *
     * @param result result value
     * @param <T> result type
     * @param <E> error type
     * @return new successful result
     */
    static <T, E> Result<T, E> getSuccessful(T result) {
        return new Result<>() {
            @Override
            public Optional<T> getValue() {
                return Optional.of(result);
            }

            @Override
            public Optional<E> getError() {
                return Optional.empty();
            }
        };
    }

    /**
     * Gets the result value.
     *
     * @return result value
     */
    Optional<T> getValue();

    /**
     * Gets the error.
     *
     * @return error
     */
    Optional<E> getError();
}