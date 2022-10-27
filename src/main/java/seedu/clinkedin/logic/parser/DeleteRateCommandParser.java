package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.commands.DeleteRateCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRateCommand object
 */
public class DeleteRateCommandParser implements Parser<DeleteRateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteRateCommand
     */
    public DeleteRateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteRateCommand.MESSAGE_USAGE), ive);
        }
        return new DeleteRateCommand(index);
    }
}
