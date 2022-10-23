package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import javafx.collections.ObservableList;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;

/**
 * Marks a module as done.
 */
@Parameters(commandDescription = "Marks a module as done.")
public class DoneModuleCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private static int totalCredits;

    @Parameter(names = "-c", description = "Module code of the module to mark done",
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
            throw new CommandException("Please exit current module first!");
        }

        if (!model.hasModuleWithModCode(moduleCode)) {
            throw new CommandException(String.format("Module code %s does not exist.",
                    moduleCode.toString()));
        }

        Module target = model.parseModuleFromCode(moduleCode);

        // Check that the module is not already unarchived.
        if (target.isDone()) {
            throw new CommandException(String.format("Module %s is already marked as done.", moduleCode.toString()));
        }

        // Done the module.
        model.setModule(target, target.done());
        // Sum up credits.
        ObservableList<Module> modules = model.getModuleList().getModuleList();
        refresh(modules);
        return new CommandResult("Marked module as done.", true);
    }

    /**
     * Counting the number of MC Completed.
     * @param modules module list.
     */
    public static void refresh(ObservableList<Module> modules) {
        // Sum up credits.
        Stream<Module> doneModules = modules.stream().filter(x -> x.isDone());
        int totalCreditsDone = doneModules.reduce(0, (creditsResult, mod) ->
                creditsResult + mod.getCredits().getIntValue(), Integer::sum);
        totalCredits = totalCreditsDone;
    }

    /**
     * Getter method for totalCredits.
     * @return totalCredits.
     */
    public static int getTotalCredits() {
        return totalCredits;
    }
}
