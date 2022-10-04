package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Exits focus mode.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_NOT_IN_FOCUS_MODE = "Not in focus mode";
    public static final String MESSAGE_SUCCESS = "Exited focus mode for %s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!model.isInFocusMode()) {
            return new CommandResult(MESSAGE_NOT_IN_FOCUS_MODE);
        }
        ModuleClass focusedClass = model.getFocusedClass();
        model.exitFocusMode();
        return new CommandResult(String.format(MESSAGE_SUCCESS, focusedClass));
    }
}
