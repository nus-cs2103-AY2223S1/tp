package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectPersonCommand object
 */
public class SelectPersonCommandParser implements Parser<SelectPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectPersonCommand
     * and returns a SelectPersonCommand object for execution.
     *
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     *                                                              the expected format
     */
    public SelectPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectPersonCommand.MESSAGE_USAGE), pe);
        }

        return new SelectPersonCommand(index);
    }
}
