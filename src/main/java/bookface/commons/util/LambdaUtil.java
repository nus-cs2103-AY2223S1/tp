package bookface.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

//@@author parth-io-reused
// referenced from https://stackoverflow.com/a/30974991/13742805
/**
 * A wrapper class for a lambda function to allow it to throw a checked {@code Exception}
 */
public class LambdaUtil {
    /**
     *  An interface to represent a function that can parse a variable of
     *  type T or throw a checked {@code Exception}
     * @param <T> The type of the input
     * @param <R> The type of the output
     * @param <E> The type of the Exception
     */
    @FunctionalInterface
    public interface ExceptionFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    /**
     * Returns a function that throws a checked exception.
     * @param function The function to convert
     * @param <T> The type of the input
     * @param <R> The type of the output
     * @param <E> The type of the Exception
     * @return A {@code Function} that can throw a checked exceptioon
     * @throws E A checked exception
     */
    public static <T, R, E extends Exception> Function<T, R>
            getExceptionThrowingFunction(ExceptionFunction<T, R, E> function) throws E {
        requireNonNull(function);
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
}
