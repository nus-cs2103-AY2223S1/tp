package seedu.rc4hdb.logic.parser;

import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Represents a generic Parser that is able to parse user input into a value of type {@code T}.
 */
public interface Parser<T> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
