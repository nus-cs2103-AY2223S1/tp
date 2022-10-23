package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Shows student's grade distribution in ModQuik.
 */
public class ShowGradeCommand extends Command {

    public static final String COMMAND_WORD = "show grade";

    public static final String MESSAGE_SUCCESS = "Show grade pie chart";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
