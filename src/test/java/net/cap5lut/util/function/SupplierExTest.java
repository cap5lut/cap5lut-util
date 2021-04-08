package net.cap5lut.util.function;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupplierExTest {
    @Test
    void staticOf() {
        assertEquals("test", SupplierEx.<String, RuntimeException>of("test").get());
    }
    @Test
    void staticUnchecked() {
        class ExpectedException extends Exception {}
        assertThrows(
                CompletionException.class,
                () -> SupplierEx.unchecked(
                        () -> {
                            throw new ExpectedException();
                        }
                ).get()
        );
    }

    @Test
    void unchecked() {
        final class ExpectedException extends Exception {}
        final SupplierEx<Object, Exception> supplier = () -> {
            throw new ExpectedException();
        };
        assertThrows(ExpectedException.class, supplier::get);
        assertThrows(CompletionException.class, () -> supplier.unchecked().get());
    }
}