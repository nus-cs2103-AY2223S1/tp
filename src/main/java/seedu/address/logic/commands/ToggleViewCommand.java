package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class ToggleViewCommand extends Command {
    public static final String COMMAND_WORD = "toggleView";

    public static final String MESSAGE_SUCCESS = "Place holder success message";

    @Override
    public CommandResult execute(Model model) {
        // Add a dummy input
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
