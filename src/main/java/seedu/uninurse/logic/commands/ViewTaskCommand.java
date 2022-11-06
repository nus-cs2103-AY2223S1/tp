package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

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
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + ": Shows a patient's task list.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2";

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
