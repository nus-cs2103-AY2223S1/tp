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

    public static final String MESSAGE_ALL_MODULE_CLASSES_SUCCESS = "Listed all classes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<ModuleClass> moduleClasses = model.getModuleClassList();
        return new CommandResult(moduleClasses + "\n" + MESSAGE_ALL_MODULE_CLASSES_SUCCESS);
    }


}
