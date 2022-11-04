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
     * Checks if a module class exists.
     */
    public static void checkModuleClassExists(ModuleClass moduleClass, Model model) throws CommandException {
        if (!model.hasModuleClass(moduleClass)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }
    }
}
