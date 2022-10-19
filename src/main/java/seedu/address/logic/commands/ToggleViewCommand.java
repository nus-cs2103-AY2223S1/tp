package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles application between showing and hiding students' parent details
 */
public class ToggleViewCommand extends Command {
    public static final String COMMAND_WORD = "toggleView";

    public static final String MESSAGE_SUCCESS_HIDE = "Hiding parent details";

    public static final String MESSAGE_SUCCESS_SHOW = "Showing parent details";

    @Override
    public CommandResult execute(Model model) {
        model.toggleStudentListInfoConcise();
        boolean isShowingParentDetails = !model.isStudentListInfoConcise();

        if (isShowingParentDetails) {
            return new CommandResult(MESSAGE_SUCCESS_SHOW, false, true, false);
        }
        return new CommandResult(MESSAGE_SUCCESS_HIDE, false, true, false);
    }

}
