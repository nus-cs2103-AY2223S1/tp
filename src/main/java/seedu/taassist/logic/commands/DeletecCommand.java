package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.HashSet;
import java.util.Set;

import seedu.taassist.logic.commands.actions.UiAction;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Deletes {@code ModuleClass}-es identified by their classNames from TA-Assist.
 */
public class DeletecCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = "> Deletes the class(es) identified by the class name(s).\n"
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1101S" + " "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Deleted class(es): [ %1$s ]";
    public static final String MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST = "The class(es) [ %1$s ] do(es) not exist!";

    private final Set<ModuleClass> moduleClasses;

    /**
     * Creates a DeletecCommand to delete the given classes.
     *
     * @param moduleClasses ModuleClass objects to delete.
     */
    public DeletecCommand(Set<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<ModuleClass> existingClasses = new HashSet<>();
        Set<ModuleClass> nonExistentClasses = new HashSet<>();

        for (ModuleClass moduleClass : moduleClasses) {
            if (model.hasModuleClass(moduleClass)) {
                existingClasses.add(moduleClass);
            } else {
                nonExistentClasses.add(moduleClass);
            }
        }

        boolean isDeletingFocusedClass = model.isInFocusMode()
                && existingClasses.stream().anyMatch(c -> c.isSame(model.getFocusedClass()));
        if (isDeletingFocusedClass) {
            model.exitFocusMode();
        }

        model.removeModuleClasses(existingClasses);

        String message = getCommandMessage(existingClasses, nonExistentClasses);
        if (isDeletingFocusedClass) {
            return new CommandResult(message, UiAction.UNFOCUS);
        }
        return new CommandResult(message);
    }

    /**
     * Returns the command message on successful execution of the command.
     *
     * @param existingClasses ModuleClass objects that were deleted.
     * @param nonExistentClasses ModuleClass objects that were not deleted because they do not exist.
     * @return Command message showing which ModuleClass objects were deleted and which were not.
     */
    public static String getCommandMessage(Set<ModuleClass> existingClasses, Set<ModuleClass> nonExistentClasses) {
        requireAllNonNull(existingClasses, nonExistentClasses);
        StringBuilder outputString = new StringBuilder();
        if (!existingClasses.isEmpty()) {
            outputString.append(getClassesDeletedMessage(existingClasses)).append("\n");
        }

        if (!nonExistentClasses.isEmpty()) {
            outputString.append(getNonExistentClassesMessage(nonExistentClasses)).append("\n");
        }

        // remove trailing newline character
        outputString.setLength(outputString.length() - 1);
        return outputString.toString();
    }

    private static String getClassesDeletedMessage(Set<ModuleClass> deletedClasses) {
        requireAllNonNull(deletedClasses);
        String deletedClassesStr = commaSeparate(deletedClasses, ModuleClass::toString);
        return String.format(MESSAGE_SUCCESS, deletedClassesStr);
    }

    private static String getNonExistentClassesMessage(Set<ModuleClass> nonExistentClasses) {
        requireAllNonNull(nonExistentClasses);
        String nonExistentClassesStr = commaSeparate(nonExistentClasses, ModuleClass::toString);
        return String.format(MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST, nonExistentClassesStr);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && moduleClasses.equals(((DeletecCommand) other).moduleClasses)); // state check
    }
}
