package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;

/**
 * Marks a module as undone.
 */
public class UndoneModuleCommand extends Command {
    public static final String COMMAND_WORD = "undone";

    public static final String MESSAGE_USAGE =
            String.format("Format: %s %s <module code>", COMMAND_WORD, PREFIX_MOD_CODE);

    public static final String MESSAGE_UNDONE_MODULE_SUCCESS = "Marked Module %1$s as undone!";

    private final ModCode moduleCode;

    public UndoneModuleCommand(ModCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModCode previousCode = model.getCurrentModule();

        if (previousCode != null) {
            throw new CommandException("Please exit current module first!");
        }

        if (!model.hasModuleWithModCode(moduleCode)) {
            throw new CommandException(String.format("Module code %s does not exist.",
                    moduleCode.toString()));
        }

        Module target = model.parseModuleFromCode(moduleCode);

        // Check that the module is not already unarchived.
        if (!target.isDone()) {
            throw new CommandException(String.format("Module %s is already marked as undone.", moduleCode.toString()));
        }

        // Undone the module.
        model.setModule(target, target.undone());
        return new CommandResult("Marked module as undone.");
    }
}
