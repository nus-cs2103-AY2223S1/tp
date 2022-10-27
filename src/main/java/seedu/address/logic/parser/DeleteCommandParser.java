package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMARK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX;
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

        Index index = null;
        String[] arguments = argMultimap.getPreamble().split(" ", 2);
        if (!isValidInput(arguments)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        String mode = argMultimap.getValue(PREFIX_MODE).orElse("");

        switch (mode) {
        case "client":
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                new ParseException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX, pe);
            }
            return new DeleteClientCommand(index);
        case "transaction":
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX, pe);
            }
            return new DeleteTransactionCommand(index);
        case "remark":
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(MESSAGE_INVALID_REMARK_DISPLAYED_INDEX, pe);
            }
            return new DeleteRemarkCommand(index);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

    private boolean isValidInput(String[] input) {
        if (input.length != 1) {
            return false;
        }
        try {
            int index = Integer.parseInt(input[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
