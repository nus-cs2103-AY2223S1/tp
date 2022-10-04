package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Enters focus mode for the specified class.
 */
public class ClassCommand extends Command {

    public static final String COMMAND_WORD = "class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Enters focus mode for the specified class.\n"
            + "Parameters: CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    private final ModuleClass targetClass;

    /**
     * Creates an ClassCommand that enters focus mode for the specified {@code targetClass}.
     */
    public ClassCommand(ModuleClass targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.enterFocusMode(targetClass);
        return new CommandResult(String.format(
                Messages.MESSAGE_ENTERED_FOCUS_MODE, targetClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassCommand // instanceof handles nulls
                && targetClass.equals(((ClassCommand) other).targetClass));
    }

}
