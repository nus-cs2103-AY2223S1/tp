package seedu.rc4hdb.logic.parser.commandparsers;

import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

//@@author jq1836
/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface CommandParser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
