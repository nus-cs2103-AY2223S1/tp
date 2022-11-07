package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.logic.parser.converters.ModCreditConverter;
import modtrekt.logic.parser.converters.ModNameConverter;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.ModTaskCount;
import modtrekt.model.module.Module;


/**
 * Edits a module in the module list.
 */
@Parameters(commandDescription = "Edits a a task in task book.")
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "edit module";
    public static final String COMMAND_ALIAS = "edit mod";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Module successfully edited: %1$s";

    @Parameter(description = "<alphanumeric mod code of 6-9 characters>", required = true,
            converter = ModCodeConverter.class)
    private ModCode targetModCode;

    @Parameter(names = "-c", description = "New module code to change to",
            converter = ModCodeConverter.class)
    private ModCode code;

    @Parameter(names = "-n", description = "New name for the module, quoted if longer than one word",
            converter = ModNameConverter.class)
    private ModName name;

    @Parameter(names = "-cr", description = "New credit for the module",
            converter = ModCreditConverter.class)
    private ModCredit credit;

    /**
     * Returns a new EditModuleCommand object, with no fields initialized, for use with JCommander.
     */
    public EditModuleCommand() {
    }

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}
     *
     * @param targetModCode the ModCode of the module
     * @param code          the module code that you want to change to
     * @param name          the module name that you want to change to
     * @param credit        the module credits that you want to change to
     */
    public EditModuleCommand(ModCode targetModCode, ModCode code, ModName name, ModCredit credit) {
        this.targetModCode = targetModCode;
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModuleWithModCode(targetModCode)) {
            throw new CommandException(String.format("Module code %s does not exist.",
                    targetModCode.toString()));
        }

        Module targetModule = model.parseModuleFromCode(targetModCode);

        if (code == null && name == null && credit == null) {
            throw new CommandException("Please enter a parameter to edit\n"
                    + "\t-c  New module code for the module\n"
                    + "\t-n  New name for the module\n"
                    + "\t-cr New credit for the module");
        }

        boolean isDone = targetModule.isDone();
        ModTaskCount newCount = targetModule.getTaskCount();
        if (code != null && model.hasModuleWithModCode(code)) {
            throw new CommandException(String.format("Module code %s already exists.",
                    code.toString()));
        }
        ModCode newCode = code != null ? code : targetModule.getCode();
        ModName newName = name != null ? name : targetModule.getName();
        ModCredit newCredit = credit != null ? credit : targetModule.getCredits();

        Module newModule = new Module(newCode, newName, newCredit, newCount, isDone);

        boolean isCurrentModule = false;
        if (model.getCurrentModule() != null && model.getCurrentModule().equals(targetModCode)) {
            isCurrentModule = true;
        }

        model.deleteModule(targetModule);
        model.addModule(newModule);

        if (!targetModCode.equals(newCode)) {
            model.updateTaskModule(targetModCode, newCode);
        }

        // If is current module, re-set it as selected
        if (isCurrentModule) {
            model.setCurrentModule(newCode);
        }

        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, newModule));
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetModCode, code, name, credit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof EditModuleCommand) {
            EditModuleCommand newOther = (EditModuleCommand) other;
            return ((targetModCode == null && newOther.targetModCode == null)
                    || targetModCode.equals(newOther.targetModCode))
                    && ((code == null && newOther.code == null)
                    || code.equals(newOther.code))
                    && ((name == null && newOther.name == null)
                    || name.equals(newOther.name))
                    && ((credit == null && newOther.credit == null)
                    || credit.equals(newOther.credit));
        }

        return false;
    }
}
