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
    public static final String MESSAGE_EMPTY_CLASS_LIST = "No classes have been added. "
            + "Add classes with [ " + AddcCommand.COMMAND_WORD + " ] command.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<ModuleClass> moduleClasses = model.getModuleClassList();
        if (moduleClasses.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_CLASS_LIST);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleClasses));
    }
}
