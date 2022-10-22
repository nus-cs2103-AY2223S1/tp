package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteRemarkCommand;
import seedu.address.logic.commands.DeleteTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }

        String mode = argMultimap.getValue(PREFIX_MODE).orElse("");

        switch (mode) {
        case "client":
            return new DeleteClientCommand(index);
        case "transaction":
            return new DeleteTransactionCommand(index);
        case "company":
            return new DeleteRemarkCommand(index);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

}
