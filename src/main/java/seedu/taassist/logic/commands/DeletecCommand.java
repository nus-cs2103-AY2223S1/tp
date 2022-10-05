package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Deletes a moduleClass identified using it's className from TA-Assist.
 */
public class DeletecCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the class name.\n"
            + "Parameters: CLASS_NAME (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    public static final String MESSAGE_DELETE_MODULE_CLASS_SUCCESS = "Deleted class: %1$s";

    private final ModuleClass targetModuleClass;

    public DeletecCommand(ModuleClass targetModuleClass) {
        this.targetModuleClass = targetModuleClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<ModuleClass> moduleClassList = model.getModuleClassList();
        if (!moduleClassList.contains(targetModuleClass)) {
            throw new CommandException(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST);
        }
        model.deleteModuleClass(targetModuleClass);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_CLASS_SUCCESS, targetModuleClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && targetModuleClass.equals(((DeletecCommand) other).targetModuleClass)); // state check
    }
}

