package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.contact.FindContactCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     *
     * @param args Raw arguments from user.
     * @return FindContactCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public FindContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (arePrefixesEmpty(argMultimap, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        List<Name> names = getNames(argMultimap);
        List<Phone> phones = getPhones(argMultimap);
        List<Email> emails = getEmails(argMultimap);
        List<Address> addresses = getAddresses(argMultimap);

        return new FindContactCommand(new PersonContainsKeywordsPredicate(names, phones, emails, addresses));
    }

    private List<Name> getNames(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseNames(argMultimap.getValue(PREFIX_NAME).get());
    }

    private List<Phone> getPhones(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parsePhones(argMultimap.getValue(PREFIX_PHONE).get());
    }

    private List<Email> getEmails(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseEmails(argMultimap.getValue(PREFIX_EMAIL).get());
    }

    private List<Address> getAddresses(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_ADDRESS).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseAddresses(argMultimap.getValue(PREFIX_ADDRESS).get());
    }

    /**
     * Returns true if all the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
