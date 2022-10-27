package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.commands.ViewCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * ViewCommand and returns a ViewCommand object for execution.
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        try {
            index = ParserUtil.parseIndex(args.trim());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE), ive);
        }

        return new ViewCommand(index);
    }
}
