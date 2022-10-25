package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
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
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add module";
    public static final String COMMAND_WORD_SHORTHAND = "add mod";
    public static final String COMMAND_IDENTIFIER = "-m";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the module list. \n"
            + "Usage 1: " + COMMAND_WORD + " " + COMMAND_IDENTIFIER + " "
            + CliSyntax.PREFIX_MOD_NAME + "<NAME> "
            + CliSyntax.PREFIX_MOD_CODE + "<CODE> "
            + CliSyntax.PREFIX_MOD_CREDIT + "<CREDIT> "
            + "\n"
            + "Usage 2: " + COMMAND_WORD + " " + COMMAND_IDENTIFIER + " "
            + CliSyntax.PREFIX_MOD_CODE + "<CODE>";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list";

    @Parameter(names = "-c", description = "ModCode", required = true,
            converter = ModCodeConverter.class)
    private ModCode modCode;

    @Parameter(names = "-n", description = "Module name", required = false,
            converter = ModNameConverter.class)
    private ModName name;

    @Parameter(names = "-cr", description = "Module credits", required = false,
            converter = ModCreditConverter.class)
    private ModCredit credit;

    /**
     * Constructor with no parameters, for JCommander usage.
     */
    public AddCommand() {

    }

    /**
     * Constructor taking in ModCode only, for modules fetched via NUSMods API.
     */
    public AddCommand(ModCode code) {
        requireNonNull(code);
        this.modCode = code;
    }

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(ModCode code, ModName name, ModCredit credit) {
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
        if (model.hasModuleWithModCode(modCode) || model.hasModuleWithModCode(modCode)) {
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
                throw new CommandException(
                        "Error fetching module data from NUSMods, please try inputting manually");
            }
        } else if (this.name == null || this.credit == null) {
            throw new CommandException("Invalid command");
        } else {
            Module module = new Module(modCode, name, credit, new ModTaskCount("0"));
            model.addModule(module);
            return new CommandResult(String.format(MESSAGE_SUCCESS, module));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && modCode.equals(((AddCommand) other).modCode));
    }
}
