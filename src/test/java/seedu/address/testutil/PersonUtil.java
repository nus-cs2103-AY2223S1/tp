package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.ContactType;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );

        if (person.getContacts().containsKey(ContactType.TELEGRAM)) {
            sb.append(PREFIX_TELEGRAM + person.getContacts().get(ContactType.TELEGRAM).getValue() + " ");
        }

        if (person.getContacts().containsKey(ContactType.SLACK)) {
            sb.append(PREFIX_SLACK + person.getContacts().get(ContactType.SLACK).getValue() + " ");
        }

        if (person.getContacts().containsKey(ContactType.PHONE)) {
            sb.append(PREFIX_PHONE + person.getContacts().get(ContactType.PHONE).getValue() + " ");
        }

        if (person.getContacts().containsKey(ContactType.EMAIL)) {
            sb.append(PREFIX_EMAIL + person.getContacts().get(ContactType.EMAIL).getValue() + " ");
        }

        person.getRole().ifPresent(r -> sb.append(PREFIX_ROLE + r.role + " "));
        person.getTimezone().ifPresent(t -> sb.append(PREFIX_TIMEZONE + t.timezone + " "));
        return sb.toString();
    }
}
