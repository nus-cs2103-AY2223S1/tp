package seedu.address.logic.parser.utils;

import seedu.address.logic.parser.exceptions.ParseException;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws ParseException;
}
