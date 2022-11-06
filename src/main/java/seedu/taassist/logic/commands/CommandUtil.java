package seedu.taassist.logic.commands;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

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
    public static void requireModuleClassExists(ModuleClass moduleClass, Model model) throws CommandException {
        requireAllNonNull(moduleClass, model);
        if (!model.hasModuleClass(moduleClass)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }
    }

    /**
     * Checks if all ModuleClass objects in {@code moduleClass} exists in {@code model}.
     *
     * @param moduleClasses ModuleClass objects to check for existence.
     * @param model Model object to find the given {@code moduleClasses}
     * @throws CommandException if any ModuleClass object in {@code moduleClasses} does not exist in {@code model}.
     */
    public static void requireModuleClassesExist(Collection<ModuleClass> moduleClasses, Model model)
            throws CommandException {
        requireAllNonNull(moduleClasses, model);
        for (ModuleClass moduleClass : moduleClasses) {
            requireModuleClassExists(moduleClass, model);
        }
    }

    /**
     * Checks if {@code session} exists in {@code moduleClass}.
     *
     * @param session Session object to check for existence.
     * @param moduleClass ModuleClass object to find the given {@code session}
     * @throws CommandException if {@code session} does not exist in {@code moduleClass}.
     */
    public static void requireSessionExists(Session session, ModuleClass moduleClass) throws CommandException {
        requireAllNonNull(moduleClass, session);
        if (!moduleClass.hasSession(session)) {
            throw new CommandException(String.format(Messages.MESSAGE_SESSION_DOES_NOT_EXIST, session.getSessionName(),
                    moduleClass));
        }
    }

    /**
     * Checks if all Session objects {@code sessions} exist in {@code moduleClass}.
     *
     * @param sessions Session objects to check for existence.
     * @param moduleClass ModuleClass object to find the given {@code sessions}
     * @throws CommandException if any Session object in {@code sessions} does not exist in {@code moduleClass}.
     */
    public static void requireSessionsExists(Collection<Session> sessions, ModuleClass moduleClass)
            throws CommandException {
        requireAllNonNull(moduleClass, sessions);
        for (Session session : sessions) {
            requireSessionExists(session, moduleClass);
        }
    }

    /**
     * Checks if the {@code model} is in focus mode.
     *
     * @param model Model object to check if it is in focus mode.
     * @param commandWord Command word of the command that should only be executed in focus mode.
     * @throws CommandException if {@code model} is not in focus mode.
     */
    public static void requireFocusMode(Model model, String commandWord) throws CommandException {
        requireAllNonNull(model, commandWord);
        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(Messages.MESSAGE_NOT_IN_FOCUS_MODE, commandWord));
        }
    }
}
