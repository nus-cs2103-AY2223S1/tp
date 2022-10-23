package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.logic.commands.SelectCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectCommand object
 */
public class SelectCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the SelectCommand
     * and returns a SelectCommand object for execution.
     *
     * @param args Arguments
     * @return SelectCommand
     * @throws ParseException If the user input does not conform to the expected format
     */
    public SelectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectCommand.MESSAGE_USAGE), ive);
        }

        return new SelectCommand(index);
    }
}

