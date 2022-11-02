package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.person.Patient;

/**
 * Shows all tasks associcated with the given patient in the uninurse book to the user.
 */
public class ViewTaskCommand extends DisplayTasksGenericCommand {
    public static final String COMMAND_WORD = "viewTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of tasks associated with the patient identified by the index number\n"
            + "used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Showing Tasks for Patient: %1$s\n%2$s";

    public static final CommandType VIEW_TASK_COMMAND_TYPE = CommandType.TASK;

    private final Index targetIndex;

    public ViewTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient person = lastShownList.get(targetIndex.getZeroBased());
        model.setPatientOfInterest(person);
        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName(), person.getTasks().toString()),
                VIEW_TASK_COMMAND_TYPE);
    }
}
