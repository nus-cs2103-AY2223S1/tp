package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.SPECIAL_CHARACTER_TODAY;
import static seedu.uninurse.model.Model.PREDICATE_SHOW_PATIENTS_FOR_TODAY;

import java.time.LocalDateTime;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.task.DateTime;

/**
 * Lists all patients for today.
 */
public class PatientsTodayCommand extends DisplayTasksGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SPECIAL_CHARACTER_TODAY
            + ": Shows all patients with tasks today.";
    public static final String MESSAGE_SUCCESS = "Listed all patients for today";
    public static final CommandType COMMAND_TYPE = CommandType.SCHEDULE;

    @Override
    public CommandResult execute(Model model) {
        requireAllNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_PATIENTS_FOR_TODAY);
        model.setSchedule(new Schedule(model.getPatientList(), new DateTime(LocalDateTime.now())));
        return new CommandResult(MESSAGE_SUCCESS, COMMAND_TYPE);
    }
}
