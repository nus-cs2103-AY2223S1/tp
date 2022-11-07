package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * Clears task, exam and module lists.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clearall";
    public static final String MESSAGE_SUCCESS = "Task, exam and module lists have been cleared!";
    public static final String MESSAGE_ALL_LISTS_ALREADY_EMPTY = "Task, exam and module lists are already empty!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Task> taskList = model.getFilteredTaskList();
        ObservableList<Module> moduleList = model.getFilteredModuleList();
        ObservableList<Exam> examList = model.getFilteredExamList();
        if (taskList.isEmpty() && moduleList.isEmpty() && examList.isEmpty()) {
            throw new CommandException(MESSAGE_ALL_LISTS_ALREADY_EMPTY);
        }

        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
