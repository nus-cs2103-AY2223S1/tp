package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Clears the task list
 */
public class ClearTasksCommand extends Command {

    public static final String COMMAND_WORD = "cleartasks";
    public static final String MESSAGE_SUCCESSS = "Task list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Module> moduleList =  model.getAddressBook().getModuleList();
        AddressBook newAddressBook = new AddressBook();
        newAddressBook.setModules(moduleList);
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESSS);
    }
}
