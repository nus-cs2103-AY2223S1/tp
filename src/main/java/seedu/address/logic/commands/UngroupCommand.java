package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.COMPARATOR_UNGROUP_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_UNGROUP_PATIENTS;

import seedu.address.model.Model;

/**
 * Group all persons or appointments in the address book to the user.
 */
public class UngroupCommand extends Command {

    public static final String COMMAND_WORD = "ungroup";

    public static final String MESSAGE_SUCCESS_PATIENTS = "Ungrouped all patients.";
    public static final String MESSAGE_SUCCESS_APPOINTMENTS = "Ungrouped all appointments.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " patients: Ungroup all patients by their tags.\n"
            + COMMAND_WORD + " appts: Ungroup all appointments by their tags.\n";

    private final String type;

    public UngroupCommand(String type) {
        this.type = type;
    }


    @Override
    public CommandResult execute(Model model) {
        if (this.type.equals("patients")) {
            requireNonNull(model);
            model.updatePersonComparator(COMPARATOR_UNGROUP_PATIENTS);
            return new CommandResult(MESSAGE_SUCCESS_PATIENTS);
        } else {
            requireNonNull(model);
            model.updateAppointmentComparator(COMPARATOR_UNGROUP_APPOINTMENTS);
            return new CommandResult(MESSAGE_SUCCESS_APPOINTMENTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UngroupCommand // instanceof handles nulls
                && type.equals(((UngroupCommand) other).type)); // state check
    }
}
