package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.COMPARATOR_GROUP_PATIENTS;

import seedu.address.model.Model;

/**
 * Group all patients in the address book to the user.
 */
public class GroupPatientCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String DESCRIPTOR_WORD = "patients";
    public static final String MESSAGE_SUCCESS_PATIENTS = "Grouped all patients.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + DESCRIPTOR_WORD
            + ": Group all patients by their tags.\n"
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD;

    public GroupPatientCommand() {

    };

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updatePersonComparator(COMPARATOR_GROUP_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS_PATIENTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupPatientCommand); // instanceof handles nulls
    }
}
