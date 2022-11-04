package seedu.taassist.logic.commands;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Contains utility methods used by commands.
 */
public class CommandUtil {
    /**
     * Checks if {@code moduleClass} exists in {@code model}.
     *
     * @param moduleClass ModuleClass object to check for existence.
     * @param model Model object to find the given {@code moduleClass}
     * @throws CommandException if {@code moduleClass} does not exist in {@code model}.
     */
    public static void checkModuleClassExists(ModuleClass moduleClass, Model model) throws CommandException {
        if (!model.hasModuleClass(moduleClass)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }
    }
}
