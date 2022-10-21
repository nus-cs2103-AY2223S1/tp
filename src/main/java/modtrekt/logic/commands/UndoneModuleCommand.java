package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;

/**
 * Marks a module as undone.
 */
@Parameters(commandDescription = "Marks a module as done.")
public class UndoneModuleCommand extends Command {
    public static final String COMMAND_WORD = "undone";

    @Parameter(names = "-c", description = "Module code of the module to mark undone",
            required = true, converter = ModCodeConverter.class)

    private ModCode moduleCode;

    /**
     * Returns a new UndoneModuleCommand object, with no fields initialized, for use with JCommander.
     */
    public UndoneModuleCommand() {}

    /**
     * Returns a new UndoneModuleCommand object.
     *
     * @param moduleCode the module code of the module that wants to be marked as undone.
     */
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
