package seedu.address.logic.parser.utils;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This is a functional interface which represents a function that can throw {@code ParseException}.
 *
 * @param <T> the type of the exception thrown by the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws ParseException;
}
