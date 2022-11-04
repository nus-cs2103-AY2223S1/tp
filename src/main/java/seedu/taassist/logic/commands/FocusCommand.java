package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.commands.CommandUtil.requireModuleClassExists;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Enters focus mode for the specified class.
 */
public class FocusCommand extends Command {

    public static final String COMMAND_WORD = "focus";

    public static final String MESSAGE_USAGE = "> Enters focus mode for the specified class.\n"
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + " CS1101S";
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
        requireModuleClassExists(targetClass, model);
        model.enterFocusMode(targetClass);
        return new CommandResult(String.format(MESSAGE_ENTERED_FOCUS_MODE, targetClass),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FocusCommand // instanceof handles nulls
                && targetClass.equals(((FocusCommand) other).targetClass));
    }

}
