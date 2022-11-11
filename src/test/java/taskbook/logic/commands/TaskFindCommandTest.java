package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.commands.tasks.TaskFindCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;
import taskbook.testutil.TypicalTaskBook;

public class TaskFindCommandTest {
    @Test
    public void execute_allFieldsPresent_success() {
        Predicate<Task> pred = (t -> t.isDone() == true);
        pred = pred.and(t -> t.isAssignmentCorrect(Assignment.TO));
        pred = pred.and(t -> t.isQueryInTask("ea"));

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateFilteredTaskListPredicate(pred);
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        TaskFindCommand command = new TaskFindCommand(pred, "ea", "TO", "X");
        assertCommandSuccess(command, model, String.format(TaskFindCommand.MESSAGE_SUCCESS + "\n"
                + expectedModel.getFilteredTaskList().size() + " tasks listed.\n"
                + "Query: ea\nAssignment: TO\nTasks are done."), expectedModel);
    }

    @Test
    public void execute_allFieldsPresent_incorrectModel() {
        Predicate<Task> pred = (t -> t.isDone() == true);
        pred = pred.and(t -> t.isAssignmentCorrect(Assignment.TO));
        pred = pred.and(t -> t.isQueryInTask("ea"));

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        TaskFindCommand command = new TaskFindCommand(pred, "ea", "TO", "X");
        try {
            command.execute(model);
        } catch (CommandException e) {
            assert false;
        }
        assertFalse(model.equals(expectedModel));
    }

    @Test
    public void execute_oneFieldPresent_success() {
        Predicate<Task> pred = (t -> t.isDone() == true);

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateFilteredTaskListPredicate(pred);
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        TaskFindCommand command = new TaskFindCommand(pred, null, null, "X");
        assertCommandSuccess(command, model, String.format(TaskFindCommand.MESSAGE_SUCCESS + "\n"
                + expectedModel.getFilteredTaskList().size() + " tasks listed.\n"
                + "Tasks are done."), expectedModel);
    }

    @Test
    public void execute_oneFieldPresent_incorrectModel() {
        Predicate<Task> pred = (t -> t.isDone() == true);

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        TaskFindCommand command = new TaskFindCommand(pred, null, null, "X");
        try {
            command.execute(model);
        } catch (CommandException e) {
            assert false;
        }
        assertFalse(model.equals(expectedModel));
    }
}
