package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.logic.parser.CliSyntax.PREFIX_STATUS;
import static java.util.Objects.requireNonNull;

import hobbylist.commons.core.index.Index;
import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.logic.commands.StatusCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.Status;

/**
 * Parses input arguments and creates a new StatusCommand object
 */
public class StatusCommandParser implements Parser<StatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatusCommand
     * and returns a StatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE), ive);
        }

        // Solution adapted from https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/124/files
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        return new StatusCommand(index, status);

    }
}
