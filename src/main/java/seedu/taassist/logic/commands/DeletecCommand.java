package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

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
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1101S";

    public static final String MESSAGE_DELETE_MODULE_CLASS_SUCCESS = "Deleted class: %1$s";

    private final ModuleClass moduleClass;

    public DeletecCommand(ModuleClass moduleClass) {
        this.moduleClass = moduleClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasModuleClass(moduleClass)) {
            throw new CommandException(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST);
        }
        model.deleteModuleClass(moduleClass);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_CLASS_SUCCESS, moduleClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && moduleClass.equals(((DeletecCommand) other).moduleClass)); // state check
    }
}

