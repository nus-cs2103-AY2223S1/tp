package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Sorts Patient data in HealthContact.
 */
public class SortPatientCommand extends Command {
    public static final CommandWord COMMAND_WORD = new CommandWord("sortpatient", "sop");
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Sorts the list of patients according to the specified field"
                    + "by alphabetical order.\n"
                    + "Parameters: c/CRITERIA (name, phone, email, address) o/ORDER (asc, desc)\n"
                    + "Example: " + COMMAND_WORD + " " + "c/name o/asc";

    public static final String MESSAGE_SORT_SUCCESS = "Sorted according to %1$s";

    private final String criteria;
    private final boolean isAscending;

    /**
     * @param criteria to be sorted by
     */
    public SortPatientCommand(String criteria, boolean isAscending) {
        requireAllNonNull(criteria);
        this.criteria = criteria;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.criteria.toLowerCase().equals("name")) {
            NameComparator nameComparator = new NameComparator();
            model.sortPatients(nameComparator, this.isAscending);
        } else if (this.criteria.toLowerCase().equals("phone")) {
            PhoneComparator phoneComparator = new PhoneComparator();
            model.sortPatients(phoneComparator, this.isAscending);
        } else if (this.criteria.toLowerCase().equals("email")) {
            EmailComparator emailComparator = new EmailComparator();
            model.sortPatients(emailComparator, this.isAscending);
        } else if (this.criteria.toLowerCase().equals("address")) {
            AddressComparator addressComparator = new AddressComparator();
            model.sortPatients(addressComparator, this.isAscending);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_SORT_CRITERIA);
        }

        String message = String.format(MESSAGE_SORT_SUCCESS, this.criteria);
        return new CommandResult(message);
    }

    /**
     * Compares two patients by name.
     */
    public class NameComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient first, Patient second) {
            return first.getName().toString().compareToIgnoreCase(second.getName().toString());
        }
    }

    /**
     * Compares two patients by phone.
     */
    public class PhoneComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient first, Patient second) {
            return first.getPhone().toString().compareTo(second.getPhone().toString());
        }
    }

    /**
     * Compares two patients by email.
     */
    public class EmailComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient first, Patient second) {
            return first.getEmail().toString().compareToIgnoreCase(second.getEmail().toString());
        }
    }

    /**
     * Compares two patients by address.
     */
    public class AddressComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient first, Patient second) {
            return first.getAddress().toString().compareToIgnoreCase(second.getAddress().toString());
        }
    }
}
