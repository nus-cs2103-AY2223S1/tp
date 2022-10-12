package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Adds one or more classes to TA-Assist.
 */
public class AddcCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds classes to TA-Assist. "
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1231" + " "
            + PREFIX_MODULE_CLASS + "CS1101S";

    public static final String MESSAGE_SUCCESS = "New class(es) added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE_CLASS = "The class(es) %1$s already exist(s) in TA-Assist";

    private final Set<ModuleClass> moduleClasses;

    /**
     * Creates an AddCommand to add the specified {@code Set&gt;ModuleClass&lt;}.
     */
    public AddcCommand(Set<ModuleClass> moduleClasses) {
        requireNonNull(moduleClasses);
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<ModuleClass> newClasses = new HashSet<>();
        Set<ModuleClass> duplicateClasses = new HashSet<>();

        for (ModuleClass moduleClass : moduleClasses) {
            if (model.hasModuleClass(moduleClass)) {
                duplicateClasses.add(moduleClass);
            } else {
                newClasses.add(moduleClass);
                model.addModuleClass(moduleClass);
            }
        }
        StringBuilder outputString = new StringBuilder();
        if (!newClasses.isEmpty()) {
            String newClassesStr = newClasses.stream().map(Object::toString).collect(Collectors.joining(" "));
            outputString.append(String.format(MESSAGE_SUCCESS, newClassesStr)).append('\n');
        }
        if (!duplicateClasses.isEmpty()) {
            String duplicateClaasesStr = duplicateClasses.stream().map(Object::toString)
                    .collect(Collectors.joining(" "));
            outputString.append(String.format(MESSAGE_DUPLICATE_MODULE_CLASS, duplicateClaasesStr)).append('\n');
        }
        outputString.setLength(outputString.length() - 1);
        return new CommandResult(outputString.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddcCommand // instanceof handles nulls
                && moduleClasses.equals(((AddcCommand) other).moduleClasses));
    }
}
