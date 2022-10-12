package modtrekt.logic.commands;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;

/**
 * Changes the current module that is shown to the user.
 */
public class CdModuleCommand extends Command {
    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "<module code>: cds into specified module.";

    /*
    If called with "cd ..", isExit is set to true.
    Otherwise, moduleCode will be populated.
     */
    private final ModCode moduleCode;
    private final boolean isExit;

    /**
     * Creates a CdModuleCommand to enter the specified {@code moduleCode}
     */
    public CdModuleCommand(ModCode moduleCode) {
        this.moduleCode = moduleCode;
        this.isExit = false;
    }

    /**
     * Creates an empty CdModuleCommand that exits the current module
     */
    public CdModuleCommand() {
        this.moduleCode = null;
        this.isExit = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (isExit) {
            ModCode previousCode = model.getCurrentModule();
            if (previousCode == null) {
                throw new CommandException("Already showing all modules.");
            }
            model.setCurrentModule(null);
            return new CommandResult(String.format(
                    "Exited module %s, now showing all modules.", previousCode));
        }
        if (!model.hasModuleWithModCode(moduleCode)) {
            throw new CommandException(String.format("Module code %s does not exist.",
                    moduleCode.toString()));
        }
        model.setCurrentModule(moduleCode);
        return new CommandResult(String.format("Changed current module to %s", moduleCode.toString()));
    }
}
