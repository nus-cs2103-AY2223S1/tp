package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.DeleteRemarkCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRemarkCommand object.
 */
public class DeleteRemarkCommandParser implements Parser<DeleteRemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRemarkCommand
     * and returns a DeleteRemarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());

            return new DeleteRemarkCommand(indices.get(0), indices.get(1));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRemarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
