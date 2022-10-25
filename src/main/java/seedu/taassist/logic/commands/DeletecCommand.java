package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.Set;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Deletes a moduleClass identified using it's className from TA-Assist.
 */
public class DeletecCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class(es) identified by the class name(s).\n"
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME... (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1101S" + " "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_DELETE_MODULE_CLASS_SUCCESS = "Deleted class(es): %1$s";

    private final Set<ModuleClass> moduleClasses;

    /**
     * Creates a DeletecCommand to delete the given classes.
     */
    public DeletecCommand(Set<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasModuleClasses(moduleClasses)) {
            throw new CommandException(String.format(MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        model.deleteModuleClasses(moduleClasses);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_CLASS_SUCCESS, moduleClasses));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && moduleClasses.equals(((DeletecCommand) other).moduleClasses)); // state check
    }
}

