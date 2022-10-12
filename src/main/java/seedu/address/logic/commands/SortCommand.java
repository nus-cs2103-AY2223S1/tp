package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts data in Address Book.
 */
public class SortCommand extends Command {
    public static final CommandWord COMMAND_WORD = new CommandWord("sort");
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Sorts the list of people according to the specified field."
            + "by alphabetical order.\n"
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: FIELD (must not be empty) "
            + "Example: " + COMMAND_WORD + "name";

    public static final String MESSAGE_SORT_SUCCESS = "Sorted according to %1$s";

    private final String criteria;

    /**
     * @param criteria to be sorted by
     */
    public SortCommand(String criteria) {
        requireAllNonNull(criteria);
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.criteria.toLowerCase().equals("name")) {
            NameComparator nameComparator = new NameComparator();
            model.sort(nameComparator);
        } else if (this.criteria.toLowerCase().equals("phone")) {
            PhoneComparator phoneComparator = new PhoneComparator();
            model.sort(phoneComparator);
        } else if (this.criteria.toLowerCase().equals("email")) {
            EmailComparator emailComparator = new EmailComparator();
            model.sort(emailComparator);
        } else if (this.criteria.toLowerCase().equals("address")) {
            AddressComparator addressComparator = new AddressComparator();
            model.sort(addressComparator);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_SORT_CRITERIA);
        }

        String message = String.format(MESSAGE_SORT_SUCCESS, this.criteria);
        return new CommandResult(message);
    }

    /**
     * Compares two persons by name.
     */
    public class NameComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            return first.getName().toString().compareTo(second.getName().toString());
        }
    }

    /**
     * Compares two persons by phone.
     */
    public class PhoneComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            return first.getPhone().toString().compareTo(second.getPhone().toString());
        }
    }

    /**
     * Compares two persons by email.
     */
    public class EmailComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            return first.getEmail().toString().compareTo(second.getEmail().toString());
        }
    }

    /**
     * Compares two persons by address.
     */
    public class AddressComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            return first.getAddress().toString().compareTo(second.getAddress().toString());
        }
    }
}
