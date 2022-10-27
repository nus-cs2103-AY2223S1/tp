package seedu.pennyWise.logic.parser;

import static seedu.pennyWise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.pennyWise.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.pennyWise.logic.commands.AddCommand;
import seedu.pennyWise.logic.parser.exceptions.ParseException;
import seedu.pennyWise.model.entry.Amount;
import seedu.pennyWise.model.entry.Date;
import seedu.pennyWise.model.entry.Description;
import seedu.pennyWise.model.entry.EntryType;
import seedu.pennyWise.model.entry.Expenditure;
import seedu.pennyWise.model.entry.Income;
import seedu.pennyWise.model.entry.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_DATE,
                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_DATE, PREFIX_TAG)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        EntryType entryType = ParserUtil.parseEntryType(argMultimap.getValue(PREFIX_TYPE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Tag tag = ParserUtil.parseTag(entryType, argMultimap.getValue(PREFIX_TAG).get());

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            Expenditure expenditure = new Expenditure(description, date, amount, tag);
            return new AddCommand(expenditure, entryType);
        case INCOME:
            Income income = new Income(description, date, amount, tag);
            return new AddCommand(income, entryType);
        default:
            //should never reach here
            break;
        }
        throw new ParseException(EntryType.MESSAGE_CONSTRAINTS);
    }
}
