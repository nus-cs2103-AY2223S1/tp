package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Adds a class to TA-Assist.
 */
public class AddcCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a class to TA-Assist. "
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE_CLASS = "This class already exists in TA-Assist";

    private final ModuleClass moduleClass;

    /**
     * Creates an AddCommand to add the specified {@code ModuleClass}.
     */
    public AddcCommand(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        this.moduleClass = moduleClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasModuleClass(moduleClass)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE_CLASS);
        }
        model.addModuleClass(moduleClass);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddcCommand // instanceof handles nulls
                && moduleClass.isSame(((AddcCommand) other).moduleClass));
    }
}
