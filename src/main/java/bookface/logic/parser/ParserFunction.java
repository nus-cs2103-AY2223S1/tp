package bookface.logic.parser;

import bookface.logic.parser.exceptions.ParseException;

/**
 * An interface to represent a function that can parse a variable of
 * type T or throw a {@code ParseException}
 * @param <T> The type of the input
 * @param <R> The type of the output
 */
@FunctionalInterface
public interface ParserFunction<T, R> {
    R apply(T t) throws ParseException;
}
