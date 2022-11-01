package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.exceptions.ModuleClassNotFoundException;

/**
 * Enters focus mode for the specified class.
 */
public class FocusCommand extends Command {

    public static final String COMMAND_WORD = "focus";

    public static final String MESSAGE_USAGE = "> Enters focus mode for the specified class.\n"
            + "Parameters: c/CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " c/CS1101S";
    public static final String MESSAGE_ENTERED_FOCUS_MODE = "Entered focus mode for [ %s ].";

    private final ModuleClass targetClass;

    /**
     * Creates an FocusCommand that enters focus mode for the specified {@code targetClass}.
     */
    public FocusCommand(ModuleClass targetClass) {
        requireNonNull(targetClass);
        this.targetClass = targetClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModuleClass existingModuleClass;

        try {
            existingModuleClass = model.getModuleClassWithSameName(targetClass);
        } catch (ModuleClassNotFoundException mcnfe) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        model.enterFocusMode(existingModuleClass);
        return new CommandResult(String.format(MESSAGE_ENTERED_FOCUS_MODE, existingModuleClass),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FocusCommand // instanceof handles nulls
                && targetClass.equals(((FocusCommand) other).targetClass));
    }

}
