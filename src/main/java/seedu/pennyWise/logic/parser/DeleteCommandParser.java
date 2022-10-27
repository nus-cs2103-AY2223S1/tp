package seedu.pennyWise.logic.parser;

import static seedu.pennyWise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.pennyWise.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.pennyWise.commons.core.index.Index;
import seedu.pennyWise.logic.commands.DeleteCommand;
import seedu.pennyWise.logic.parser.exceptions.ParseException;
import seedu.pennyWise.model.entry.EntryType;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            EntryType entryType = ParserUtil.parseEntryType(argMultimap.getValue(PREFIX_TYPE).get());
            return new DeleteCommand(index, entryType);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
