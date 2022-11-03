package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import javafx.collections.transformation.FilteredList;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;

/**
 * Marks a module as done.
 */
@Parameters(commandDescription = "Marks a module as done.")
public class DoneModuleCommand extends Command {
    public static final String COMMAND_WORD = "done module";
    public static final String COMMAND_ALIAS = "done mod";

    @Parameter(description = "<module code>",
            required = true, converter = ModCodeConverter.class)

    private ModCode moduleCode;

    /**
     * Returns a new DoneModuleCommand object, with no fields initialized, for use with JCommander.
     */
    public DoneModuleCommand() {}

    /**
     * Returns a new DoneModuleCommand object.
     *
     * @param moduleCode the module code of the module that wants to be marked as done.
     */
    public DoneModuleCommand(ModCode moduleCode) {
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

        FilteredList<Task> modulesTasks = model.getFilteredTaskList()
                .filtered(task -> task.getModule().equals(moduleCode) && !task.isDone());

        if (target.isDone() && modulesTasks.size() == 0) {
            throw new CommandException(String.format("Module %s is already marked as done.", moduleCode.toString()));
        }

        // Done the module.
        Module doneTarget = target.done();
        model.setModule(target, doneTarget);
        model.setDoneModuleTasksAsDone(moduleCode);
        return new CommandResult("Yay! I successfully marked this module as done!");
    }
}
