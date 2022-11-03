package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.HashSet;
import java.util.Set;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Adds one or more classes to TA-Assist.
 */
public class AddcCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = "> Adds classes to TA-Assist.\n"
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1231" + " "
            + PREFIX_MODULE_CLASS + "CS1101S";

    public static final String MESSAGE_SUCCESS = "Added new class(es): [ %1$s ]";
    public static final String MESSAGE_DUPLICATE_MODULE_CLASS = "The class(es) [ %1$s ] already exists!";

    private final Set<ModuleClass> moduleClasses;

    /**
     * Creates an AddCommand to add the specified {@code Set&gt;ModuleClass&lt;}.
     */
    public AddcCommand(Set<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<ModuleClass> newClasses = new HashSet<>();
        Set<ModuleClass> duplicateClasses = new HashSet<>();

        for (ModuleClass moduleClass : moduleClasses) {
            if (model.hasModuleClass(moduleClass)) {
                ModuleClass existingModuleClass = model.getModuleClassWithSameName(moduleClass);
                duplicateClasses.add(existingModuleClass);
            } else {
                newClasses.add(moduleClass);
            }
        }
        model.addModuleClasses(newClasses);

        String message = getCommandMessage(newClasses, duplicateClasses);
        return new CommandResult(message);
    }

    public static String getCommandMessage(Set<ModuleClass> newClasses, Set<ModuleClass> duplicateClasses) {
        StringBuilder outputString = new StringBuilder();
        if (!newClasses.isEmpty()) {
            outputString.append(getClassesAddedMessage(newClasses)).append("\n");
        }

        if (!duplicateClasses.isEmpty()) {
            outputString.append(getDuplicateClassesMessage(duplicateClasses)).append("\n");
        }

        // remove trailing newline character
        outputString.setLength(outputString.length() - 1);
        return outputString.toString();
    }

    private static String getClassesAddedMessage(Set<ModuleClass> newClasses) {
        requireAllNonNull(newClasses);
        String newClassesStr = commaSeparate(newClasses, ModuleClass::toString);
        return String.format(MESSAGE_SUCCESS, newClassesStr);
    }

    private static String getDuplicateClassesMessage(Set<ModuleClass> duplicateClasses) {
        requireAllNonNull(duplicateClasses);
        String duplicateClassesStr = commaSeparate(duplicateClasses, ModuleClass::toString);
        return String.format(MESSAGE_DUPLICATE_MODULE_CLASS, duplicateClassesStr);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddcCommand // instanceof handles nulls
                && moduleClasses.equals(((AddcCommand) other).moduleClasses));
    }
}
