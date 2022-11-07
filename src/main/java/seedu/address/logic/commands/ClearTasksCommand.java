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
 * Clears the task list
 */
public class ClearTasksCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";
    public static final String MESSAGE_TASK_LIST_ALREADY_EMPTY = "The task list is already empty!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Task> taskList = model.getFilteredTaskList();
        if (taskList.isEmpty()) {
            throw new CommandException(MESSAGE_TASK_LIST_ALREADY_EMPTY);
        }

        ObservableList<Module> moduleList = model.getFilteredModuleList();
        ObservableList<Exam> examList = model.getFilteredExamList();

        AddressBook newAddressBook = new AddressBook();
        newAddressBook.setModules(moduleList);
        newAddressBook.setExams(examList);
        newAddressBook.resetAllTaskCount();

        model.setAddressBook(newAddressBook);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
