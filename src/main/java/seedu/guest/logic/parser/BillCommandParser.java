package seedu.guest.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_BILL;

import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.BillCommand;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.guest.Bill;

/**
 * Parses input arguments and creates a new {@code BillCommand} object
 */
public class BillCommandParser implements Parser<BillCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code BillCommand}
     * and returns a {@code BillCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public BillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BILL);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BillCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_BILL).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BillCommand.MESSAGE_USAGE));
        }

        Bill bill = ParserUtil.parseBill(argMultimap.getValue(PREFIX_BILL).get());

        return new BillCommand(index, bill);
    }
}
