package seedu.taassist.logic.commands;

import java.util.List;
import java.util.Set;

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

    public static final String MESSAGE_DELETE_MODULE_CLASS_SUCCESS = "Deleted class(es): %1$s";

    private final Set<ModuleClass> moduleClasses;

    public DeletecCommand(Set<ModuleClass> moduleClasses) {
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!areAllExistingModuleClasses(model, moduleClasses)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        // Each module class in guaranteed to exist
        for (ModuleClass mc : moduleClasses) {
            model.deleteModuleClass(mc);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_CLASS_SUCCESS, moduleClasses));
    }

    public boolean areAllExistingModuleClasses(Model model, Set<ModuleClass> moduleClasses) {
        requireNonNull(model);
        return moduleClasses.stream().allMatch(model::hasModuleClass);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && moduleClasses.equals(((DeletecCommand) other).moduleClasses)); // state check
    }
}

