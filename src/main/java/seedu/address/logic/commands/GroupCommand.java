package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.COMPARATOR_GROUP_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_GROUP_PATIENTS;

import seedu.address.model.Model;

/**
 * Group all persons or appointments in the address book to the user.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_SUCCESS_PATIENTS = "Grouped all patients.";
    public static final String MESSAGE_SUCCESS_APPOINTMENTS = "Grouped all appointments.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " patients: Group all patients by their tags.\n"
            + COMMAND_WORD + " appts: Group all appointments by their tags.\n";

    private final String type;

    public GroupCommand(String type) {
        this.type = type;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (this.type.equals("patients")) {
            model.updatePersonComparator(COMPARATOR_GROUP_PATIENTS);
            return new CommandResult(MESSAGE_SUCCESS_PATIENTS);
        } else {
            model.updateAppointmentComparator(COMPARATOR_GROUP_APPOINTMENTS);
            return new CommandResult(MESSAGE_SUCCESS_APPOINTMENTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCommand // instanceof handles nulls
                && type.equals(((GroupCommand) other).type)); // state check
    }
}
