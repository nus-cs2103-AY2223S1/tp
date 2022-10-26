package seedu.address.logic.parser;

import seedu.address.logic.commands.CommandArchive;
import seedu.address.logic.parser.exceptions.ParseException;

public interface ArchiveParser<T extends CommandArchive> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
