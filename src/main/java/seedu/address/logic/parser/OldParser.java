package seedu.address.logic.parser;

import seedu.address.logic.commands.OldCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a OldParser that is able to parse user input into a {@code OldCommand} of type {@code T}.
 */
public interface OldParser<T extends OldCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
