package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import com.beust.jcommander.Parameter;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;

/**
 * Deletes a module identified using it's displayed index from the module list.
 */
public class RemoveModuleCommand extends Command {
    public static final String COMMAND_WORD = "remove module";
    public static final String[] COMMAND_ALIASES = {"remove mod", "rm module", "rm mod"};

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task/module identified by the module code.\n"
            + "Prefixes: " + CliSyntax.PREFIX_MODULE + ": Modules, " + CliSyntax.PREFIX_TASK + ": Tasks\n"
            + "Format: " + COMMAND_WORD + " " + CliSyntax.PREFIX_MODULE + " <MODULE CODE>";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "I successfully deleted the module: %1$s!";

    @Parameter(description = "<alphanumeric mod code of 6-9 characters>", required = true,
        converter = ModCodeConverter.class)
    private ModCode code;

    /**
     *     Empty constructor that instantiates a RemoveCommand object, for use with JCommander.
     */
    public RemoveModuleCommand() {}

    public RemoveModuleCommand(ModCode code) {
        this.code = code;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!(model.hasModuleWithModCode(code))) {
            throw new CommandException(String.format("Module code %s does not exist.", code));
        }
        Module moduleToDelete = model.parseModuleFromCode(code);
        model.deleteModule(moduleToDelete);
        model.deleteTasksOfModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveModuleCommand // instanceof handles nulls
                && code.equals(((RemoveModuleCommand) other).code)); // state check
    }
}
