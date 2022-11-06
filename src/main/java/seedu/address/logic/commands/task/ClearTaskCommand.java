package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.Model;
import seedu.address.model.TaskPanel;

/**
 * Clears the task panel.
 */
public class ClearTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Clears the task panel.\n"
            + "Parameters: \n"
            + "Example: " + COMMAND_WORD_FULL;

    public static final String MESSAGE_SUCCESS = "Task Panel has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskPanel(new TaskPanel());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ClearTaskCommand;
    }
}
