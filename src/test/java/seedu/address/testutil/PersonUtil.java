package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.model.attribute.PrefixedAttribute;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddPersonCommand.getFullCommand(AddPersonCommand.SUBCOMMAND_WORD) + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        person.getAttributes().stream().forEach(
            attr -> {
                if (attr instanceof PrefixedAttribute) {
                    sb.append(((PrefixedAttribute) attr).getPrefix());
                    sb.append(attr.getAttributeContent() + " ");
                }
            });
        person.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();

    }
}
