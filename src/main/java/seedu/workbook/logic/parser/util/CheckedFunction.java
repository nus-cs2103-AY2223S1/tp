package seedu.workbook.logic.parser.util;

import seedu.workbook.logic.parser.exceptions.ParseException;

// Reused from CS2103T-T10-4
// https://github.com/AY2223S1-CS2103T-T10-4/tp/commit/118c73f20a9eac789f37778a2f05e225f76a1110
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
