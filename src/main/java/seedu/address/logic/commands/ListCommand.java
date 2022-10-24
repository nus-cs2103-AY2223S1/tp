package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.HiddenPredicateSingleton;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_PATIENTS = "Listed all patients.";
    public static final String MESSAGE_SUCCESS_APPOINTMENTS = "Listed all appointments.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " patients: List down all patients.\n"
            + COMMAND_WORD + " appts: List down all appointments.\n";

    private final String type;

    public ListCommand(String type) {
        this.type = type;
    }


    @Override
    public CommandResult execute(Model model) {
        if (this.type.equals("patients")) {
            requireNonNull(model);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            HiddenPredicateSingleton.clearHiddenPatients();
            return new CommandResult(MESSAGE_SUCCESS_PATIENTS);
        } else {
            requireNonNull(model);
            model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
            HiddenPredicateSingleton.clearHiddenAppts();
            return new CommandResult(MESSAGE_SUCCESS_APPOINTMENTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && type.equals(((ListCommand) other).type)); // state check
    }
}
