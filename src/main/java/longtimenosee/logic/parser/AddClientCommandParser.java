package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_INCOME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_RISK_APPETITE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import longtimenosee.logic.commands.AddClientCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.client.Birthday;
import longtimenosee.model.client.Client;
import longtimenosee.model.client.Income;
import longtimenosee.model.client.RiskAppetite;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Phone;
import longtimenosee.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddClientCommandParser implements Parser<AddClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                PREFIX_BIRTHDAY, PREFIX_INCOME, PREFIX_RISK_APPETITE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_BIRTHDAY,
                PREFIX_INCOME, PREFIX_RISK_APPETITE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get());
        Income income = ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get());
        RiskAppetite ra = ParserUtil.parseRA(argMultimap.getValue(PREFIX_RISK_APPETITE).get());

        Client client = new Client(name, phone, email, address, tagList, birthday, income, ra);

        return new AddClientCommand(client);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
