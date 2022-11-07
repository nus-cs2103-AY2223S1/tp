package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddBillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.BillDate;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddBillCommandParser implements Parser<AddBillCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     *      the expected format
     */
    public AddBillCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_BILL_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT, PREFIX_BILL_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBillCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddBillCommand.MESSAGE_USAGE), pe);
        }

        BillDate billDate = ParserUtil.parseBillDate(argMultimap.getValue(PREFIX_BILL_DATE).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());

        return new AddBillCommand(index, billDate, amount);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
