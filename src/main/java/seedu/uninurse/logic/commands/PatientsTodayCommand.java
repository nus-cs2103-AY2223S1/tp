package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.model.Model.PREDICATE_SHOW_PATIENTS_FOR_TODAY;

import seedu.uninurse.model.Model;

/**
 * Lists all patients for today.
 */
public class PatientsTodayCommand extends Command {

    public static final String COMMAND_WORD = "patientsToday";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients with tasks today";

    public static final String MESSAGE_SUCCESS = "Listed all patients for today";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_PATIENTS_FOR_TODAY);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.SCHEDULE);
    }
}
