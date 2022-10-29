package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BILLS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.address.model.Model;

/**
 * Lists all patients in the HealthContact to the user.
 */
public class ListCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("list", "ls");

    public static final String MESSAGE_SUCCESS = "Listed all patients, appointments and bills";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.updateFilteredBillList(PREDICATE_SHOW_ALL_BILLS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
