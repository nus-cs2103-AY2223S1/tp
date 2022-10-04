package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.moduleclass.ModuleClass;

/**
 * Exits focus mode.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_ALREADY_EXITED = "Already exited focus mode";
    public static final String MESSAGE_SUCCESS = "Exited focus mode for %s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!model.isInFocusMode()) {
            return new CommandResult(MESSAGE_ALREADY_EXITED);
        }
        ModuleClass focusedClass = model.getFocusedClass();
        model.exitFocusMode();
        return new CommandResult(String.format(MESSAGE_SUCCESS, focusedClass));
    }
}
