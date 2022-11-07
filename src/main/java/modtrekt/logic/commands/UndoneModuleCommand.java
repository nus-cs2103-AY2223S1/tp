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
    public static final String COMMAND_WORD = "undone module";
    public static final String COMMAND_ALIAS = "undone mod";

    @Parameter(description = "<alphanumeric mod code of 6-9 characters>",
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
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModCode previousCode = model.getCurrentModule();

        if (previousCode != null) {
            throw new CommandException("Please exit the current module using 'cd ..' command!");
        }

        if (!model.hasModuleWithModCode(moduleCode)) {
            throw new CommandException(String.format("Module code %s does not exist.",
                    moduleCode.toString()));
        }

        Module target = model.parseModuleFromCode(moduleCode);

        // Check that the module is not already undone.
        if (!target.isDone()) {
            throw new CommandException(String.format("Module %s is already marked as undone.", moduleCode.toString()));
        }

        // Undone the module.
        Module undoneTarget = target.undone();
        model.setModule(target, undoneTarget);
        return new CommandResult("I successfully marked this module as not done!");
    }
}
