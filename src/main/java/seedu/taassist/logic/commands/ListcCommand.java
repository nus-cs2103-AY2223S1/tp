package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Lists all classes in TA-Assist to the user.
 */
public class ListcCommand extends Command {

    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_SUCCESS = "All classes:\n%1$s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<ModuleClass> moduleClasses = model.getModuleClassList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleClasses));
    }


}
