package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_ROLE,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SLACK, PREFIX_TELEGRAM);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Map<ContactType, Contact> contacts = new HashMap<>();
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            contacts.put(ContactType.PHONE, ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            contacts.put(ContactType.EMAIL, ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_SLACK).isPresent()) {
            contacts.put(ContactType.SLACK, ParserUtil.parseSlack(argMultimap.getValue(PREFIX_SLACK).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            contacts.put(ContactType.TELEGRAM, ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }

        Timezone timezone = null;
        if (argMultimap.getValue(PREFIX_TIMEZONE).isPresent()) {
            timezone = ParserUtil.parseTimezone(argMultimap.getValue(PREFIX_TIMEZONE).get());
        }

        Role role = null;
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        }

        Person person = new Person(name, address, tagList, contacts, role, timezone);

        return new AddCommand(person);
    }

}
