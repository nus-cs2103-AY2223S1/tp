package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.Objects;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.logic.parser.converters.ModCreditConverter;
import modtrekt.logic.parser.converters.ModNameConverter;
import modtrekt.logic.parser.module.ModuleParser;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.ModTaskCount;
import modtrekt.model.module.Module;


/**
 * Adds a module to the module list.
 */
@Parameters(commandDescription = "Add module to the module list")
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "add module";
    public static final String COMMAND_ALIAS = "add mod";

    public static final String MESSAGE_SUCCESS = "Yay! I added a new module: %1$s!";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <MODULE CODE>";
    public static final String VERBOSE_MESSAGE_USAGE = COMMAND_WORD + " <MODULE CODE> \n"
            + "-n <MODULE NAME> \n"
            + "-cr <MODULE CREDITS>";

    @Parameter(description = "<alphanumeric mod code of 6-9 characters>", required = true,
            converter = ModCodeConverter.class)
    private ModCode modCode;

    @Parameter(names = "-n", description = "Module name",
            converter = ModNameConverter.class)
    private ModName name;

    @Parameter(names = "-cr", description = "Module credits",
            converter = ModCreditConverter.class)
    private ModCredit credit;

    /**
     * Constructor with no parameters, for JCommander usage.
     */
    public AddModuleCommand() {

    }

    /**
     * Constructor taking in ModCode only, for modules fetched via NUSMods API.
     */
    public AddModuleCommand(ModCode code) {
        requireNonNull(code);
        this.modCode = code;
    }

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(ModCode code, ModName name, ModCredit credit) {
        requireNonNull(code);
        requireNonNull(name);
        requireNonNull(credit);
        this.modCode = code;
        this.name = name;
        this.credit = credit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /* Module equality validated only via modCode, as some modules have the same name. */
        if (model.hasModuleWithModCode(modCode)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        if (this.name == null && this.credit == null) {
            try {
                Module module = ModuleParser.fetchModule(modCode);

                if (module == null) {
                    throw new CommandException("Module code does not exist");
                }
                model.addModule(module);
                return new CommandResult(String.format(MESSAGE_SUCCESS, module));
            } catch (IOException | InterruptedException e) {
                throw new CommandException("Error fetching module data from NUSMods, "
                        + "please try inputting manually using the following syntax: \n"
                        + VERBOSE_MESSAGE_USAGE);
            }
        } else if (this.name == null || this.credit == null) {
            throw new CommandException("Invalid syntax, please see below for the command usage \n\n"
                    + "Usage: " + MESSAGE_USAGE + "\n"
                    + "OR \n"
                    + VERBOSE_MESSAGE_USAGE);
        } else {
            Module module = new Module(modCode, name, credit, new ModTaskCount("0"));
            model.addModule(module);
            return new CommandResult(String.format(MESSAGE_SUCCESS, module));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(modCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && modCode.equals(((AddModuleCommand) other).modCode));
    }
}
