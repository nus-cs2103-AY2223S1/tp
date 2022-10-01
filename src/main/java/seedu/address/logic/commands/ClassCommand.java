package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Enters focus mode for the specified class
 * TODO: replace tag with class
 */
public class ClassCommand extends Command {

    public static final String COMMAND_WORD = "class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Enters focus mode for the specified class.\n"
            + "Parameters: CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    //TODO: replace with class
    private final Tag targetClass;

    /**
     * Creates an ClassCommand that enters focus mode for the specified {@code Tag}.
     * TODO: replace with class
     */
    public ClassCommand(Tag targetClass) {
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
