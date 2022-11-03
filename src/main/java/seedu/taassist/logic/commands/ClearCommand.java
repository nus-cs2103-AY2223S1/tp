package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.commands.actions.UiAction.UI_UNFOCUS;

import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.TaAssist;

/**
 * Clears TA-Assist.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TA-Assist has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        boolean isInFocusMode = model.isInFocusMode();
        model.setTaAssist(new TaAssist());
        if (isInFocusMode) {
            return new CommandResult(MESSAGE_SUCCESS, UI_UNFOCUS);
        }
        return new CommandResult(MESSAGE_SUCCESS);

    }
}
