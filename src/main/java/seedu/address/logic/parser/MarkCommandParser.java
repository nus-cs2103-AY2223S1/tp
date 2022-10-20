package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns an MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPLICATION_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_APPLICATION_STATUS).isPresent()) {
            ApplicationStatus applicationStatus = ParserUtil.parseApplicationStatus(
                    argMultimap.getValue(PREFIX_APPLICATION_STATUS).get());
            return new MarkCommand(index, applicationStatus);
        } else {
            throw new ParseException(MarkCommand.MESSAGE_NOT_EDITED);
        }
    }
}
