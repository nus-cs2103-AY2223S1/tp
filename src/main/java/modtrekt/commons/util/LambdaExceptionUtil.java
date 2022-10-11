package modtrekt.commons.util;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Allows throwing of checked exceptions from lambda expressions.
 * Source: https://stackoverflow.com/questions/27644361/how-can-i-throw-checked-exceptions-from-inside-java-8-streams/
 *
 * @author PaoloC
 */
public final class LambdaExceptionUtil {
    /**
     * Wraps a Consumer to allow for re-throwing checked exceptions.
     */
    public static <T, E extends Exception> Consumer<T> rethrowConsumer(ConsumerWithExceptions<T, E> consumer) throws E {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception exception) {
                throwActualException(exception);
            }
        };
    }

    /**
     * Wraps a Function to allow for re-throwing checked exceptions.
     */
    public static <T, R, E extends Exception> Function<T, R> rethrowFunction(FunctionWithExceptions<T, R, E> function)
            throws E {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception exception) {
                throwActualException(exception);
                return null;
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static <E extends Exception> void throwActualException(Exception exception) throws E {
        throw (E) exception;
    }

    /**
     * Wraps the Consumer functional interface to allow for re-throwing checked exceptions.
     */
    @FunctionalInterface
    public interface ConsumerWithExceptions<T, E extends Exception> {
        void accept(T t) throws E;
    }

    /**
     * Wraps the Function functional interface to allow for re-throwing checked exceptions.
     */
    @FunctionalInterface
    public interface FunctionWithExceptions<T, R, E extends Exception> {
        R apply(T t) throws E;
    }
}
