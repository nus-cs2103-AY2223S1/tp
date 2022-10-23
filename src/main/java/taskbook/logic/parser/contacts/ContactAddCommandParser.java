package taskbook.logic.parser.contacts;

import java.util.Set;
import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.contacts.ContactAddCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.ParserUtil;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Address;
import taskbook.model.person.Email;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.person.Phone;
import taskbook.model.tag.Tag;

/**
 * Parses input arguments and creates a new ContactAddCommand object
 */
public class ContactAddCommandParser implements Parser<ContactAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ContactAddCommand
     * and returns an ContactAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ContactAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS,
                        CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(
                argMultimap,
                CliSyntax.PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, ContactAddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()
                ? argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()
                : Phone.NO_PHONE_PROVIDED);
        Email email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()
                ? argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()
                : Email.NO_EMAIL_PROVIDED);
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()
                ? argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get()
                : Address.NO_ADDRESS_PROVIDED);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        Person person = new Person(name, phone, email, address, tagList);

        return new ContactAddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
