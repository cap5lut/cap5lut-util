package net.cap5lut.util;

import java.util.Optional;

/**
 * Specializable interface.
 *
 * @param <T> specializable type
 */
public interface Specializable<T> {
    /**
     * Gets the specialized version of this instance.
     *
     * @param type specialized class
     * @param <U> specialized type
     * @return specialized version of this instance, or an empty optional
     */
    @SuppressWarnings("unchecked")
    default <U extends T> Optional<U> as(Class<U> type) {
        return type.isInstance(this) ? Optional.of((U) this) : Optional.empty();
    }
}
