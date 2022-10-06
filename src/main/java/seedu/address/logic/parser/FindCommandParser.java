package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        List<Name> names = getNames(argMultimap);
        List<Phone> phones = getPhones(argMultimap);
        List<Email> emails = getEmails(argMultimap);
        List<Address> addresses = getAddresses(argMultimap);

        return new FindCommand(new PersonContainsKeywordsPredicate(names, phones, emails, addresses));
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

}
