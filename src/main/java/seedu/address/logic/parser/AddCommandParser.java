package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTHLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.tag.NormalTag.MESSAGE_MAX_TAGS;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_INCOME, PREFIX_MONTHLY, PREFIX_RISKTAG,
                        PREFIX_PLANTAG, PREFIX_CLIENTTAG, PREFIX_TAG,
                        PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_INCOME, PREFIX_MONTHLY, PREFIX_PLANTAG, PREFIX_RISKTAG, PREFIX_CLIENTTAG)
                || !argMultimap.getPreamble().isEmpty()
                || arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        IncomeLevel incomeLevel = ParserUtil.parseIncomeLevel(argMultimap.getValue(PREFIX_INCOME).get());
        Monthly monthly = ParserUtil.parseMonthly(argMultimap.getValue(PREFIX_MONTHLY).get());
        RiskTag riskTag = ParserUtil.parseRiskTag(argMultimap.getValue(PREFIX_RISKTAG).get());
        PlanTag planTag = ParserUtil.parsePlanTag(argMultimap.getValue(PREFIX_PLANTAG).get());
        ClientTag clientTag = ParserUtil.parseClientTag(argMultimap.getValue(PREFIX_CLIENTTAG).get());
        Set<NormalTag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (tagList.size() > 5) {
            throw new ParseException(MESSAGE_MAX_TAGS);
        }
        Person person = new Person(name, phone, email, address, incomeLevel, monthly,
                riskTag, planTag, clientTag, tagList);

        return new AddCommand(person);
    }
}
