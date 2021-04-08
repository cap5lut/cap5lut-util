package net.cap5lut.util.function;

import java.util.concurrent.CompletionException;

/**
 * {@link Runnable} interface that could throw an error.
 *
 * @param <E> error type
 */
@FunctionalInterface
public interface RunnableEx<E extends Throwable> {
    /**
     * Converts the checked runnable to an unchecked runnable.
     *
     * @param runnable checked runnable
     * @return unchecked runnable
     */
    static Runnable unchecked(RunnableEx<?> runnable) {
        return runnable.unchecked();
    }

    /**
     * Executes the runnable.
     *
     * @throws E possibly thrown error
     */
    void run() throws E;

    /**
     * Gets the unchecked variant.
     * Note: checked exceptions are wrapped in {@link CompletionException}s.
     *
     * @return unchecked variant
     */
    default Runnable unchecked() {
        return () -> {
            try {
                run();
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new CompletionException(e);
            }
        };
    }
}
