package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.person.github.User;
import seedu.address.model.tag.Tag;
import seedu.address.ui.MainPanelName;

/**
 * Deletes the specified contact of a given Person.
 */
public class DeleteAttributeCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Contact Attribute successfully deleted";
    public static final String MESSAGE_USAGE = " Deletes a person's specified contact. "
            + "Allowed Parameters are: email, phone, slack, telegram, role, timezone, address\n"
            + "Example: " + COMMAND_WORD + " email";
    public static final String MESSAGE_NO_SUCH_ATTRIBUTE = "No such attribute exists for person %s.";
    public static final String MESSAGE_CANNOT_DELETE_NAME = "You can't delete a person on this page!\n"
            + "To delete this person, go back first";

    private final Prefix prefixToDelete;

    /**
     * Constructor for DeleteAttributeCommand
     */
    public DeleteAttributeCommand(Prefix prefixToDelete) {
        requireNonNull(prefixToDelete);
        this.prefixToDelete = prefixToDelete;
    }

    /**
     * Creates a new Person after deleting specified attribute.
     * Sets it to the previous person
     *
     * @return CommandResult depicting success of delete command
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Get the Person whose attribute will be deleted
        Person toDelete = model.getSelectedPerson().get();
        assert toDelete != null;

        // Check if there is existing value for given prefix
        // If not, return MESSAGE_NO_SUCH_ATTRIBUTE
        if (!checkAttributePresence(toDelete, this.prefixToDelete)) {
            throw new CommandException(MESSAGE_NO_SUCH_ATTRIBUTE);
        }

        // Update the Person with the Deletion
        Person personAfterDeletion = createPersonAfterDeletion(toDelete);

        // Check for Duplicates
        model.setPerson(toDelete, personAfterDeletion);
        return new CommandResult(MESSAGE_DELETE_SUCCESS);
    }

    /**
     * Returns if DeleteAttributeCommand can execute at given Panel name.
     * @return true if Detail Page, false otherwise.
     */
    public static boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.Detail);
    }

    /**
     * Creates new Person Object after deleting specified attribute (prefixToDelete)
     *
     * @param toDelete The Person whose attribute will be deleted
     * @return a Person after the deletion has been done
     */
    private Person createPersonAfterDeletion(Person toDelete) {
        Name name = toDelete.getName();
        Set<Tag> tags = prefixToDelete.equals(PREFIX_TAG) ? null : toDelete.getTags();
        Role role = prefixToDelete.equals(PREFIX_ROLE) ? null : toDelete.getRole().orElse(null);
        Address address = prefixToDelete.equals(PREFIX_ADDRESS) ? null
                : toDelete.getAddress().orElse(null);
        Timezone timezone = prefixToDelete.equals(PREFIX_TIMEZONE) ? null
                : toDelete.getTimezone().orElse(null);

        User githubUser = prefixToDelete.equals(PREFIX_GITHUB) ? null
                : toDelete.getGithubUser().orElse(null);

        Map<ContactType, Contact> updatedContacts = new HashMap<>(toDelete.getContacts());

        if (prefixToDelete.equals(PREFIX_EMAIL)) {
            updatedContacts.remove(ContactType.EMAIL);
        } else if (prefixToDelete.equals(PREFIX_PHONE)) {
            updatedContacts.remove(ContactType.PHONE);
        } else if (prefixToDelete.equals(PREFIX_SLACK)) {
            updatedContacts.remove(ContactType.SLACK);
        } else if (prefixToDelete.equals(PREFIX_TELEGRAM)) {
            updatedContacts.remove(ContactType.TELEGRAM);
        }

        return new Person(name, address, tags, updatedContacts, role, timezone, githubUser);
    }

    private boolean checkAttributePresence(Person toDelete, Prefix attribute) throws CommandException {
        if (attribute.equals(PREFIX_TAG)) {
            return toDelete.getTags().size() > 0;
        } else if (attribute.equals(PREFIX_ADDRESS)) {
            return toDelete.getAddress().isPresent();
        } else if (attribute.equals(PREFIX_ROLE)) {
            return toDelete.getRole().isPresent();
        } else if (attribute.equals(PREFIX_GITHUB)) {
            return toDelete.getGithubUser().isPresent();
        } else if (attribute.equals(PREFIX_TIMEZONE)) {
            return toDelete.getTimezone().isPresent();
        } else if (attribute.equals(PREFIX_PHONE)) {
            return toDelete.getContacts().containsKey(ContactType.PHONE);
        } else if (attribute.equals(PREFIX_SLACK)) {
            return toDelete.getContacts().containsKey(ContactType.SLACK);
        } else if (attribute.equals(PREFIX_EMAIL)) {
            return toDelete.getContacts().containsKey(ContactType.EMAIL);
        } else if (attribute.equals(PREFIX_TELEGRAM)) {
            return toDelete.getContacts().containsKey(ContactType.TELEGRAM);
        } else if (attribute.equals(PREFIX_NAME)) {
            throw new CommandException(MESSAGE_CANNOT_DELETE_NAME);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeleteAttributeCommand) {
            DeleteAttributeCommand otherCommand = (DeleteAttributeCommand) other;
            return otherCommand.prefixToDelete.equals(this.prefixToDelete);
        } else {
            return false;
        }
    }
}
