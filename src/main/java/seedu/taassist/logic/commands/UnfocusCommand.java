package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.logic.commands.actions.UiAction.UI_UNFOCUS;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Exits focus mode.
 */
public class UnfocusCommand extends Command {

    public static final String COMMAND_WORD = "unfocus";

    public static final String MESSAGE_SUCCESS = "Exited focus mode for [ %s ].";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }
        ModuleClass focusedClass = model.getFocusedClass();
        model.exitFocusMode();
        return new CommandResult(String.format(MESSAGE_SUCCESS, focusedClass), UI_UNFOCUS);
    }
}
