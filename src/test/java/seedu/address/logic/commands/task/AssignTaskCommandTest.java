package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TEAMMATE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskPanel;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AssignTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(outOfBoundTaskIndex, new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>());

        assertCommandFailure(assignTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSingleTeammateAddIndex_throwsCommandException() {
        Index outOfBoundTeammateIndex = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(outOfBoundTeammateIndex)), new HashSet<>(), new HashSet<>(), new HashSet<>());

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_INDEX_CUSTOM,
                        outOfBoundTeammateIndex.getOneBased()) + '\n');
    }

    @Test
    public void execute_invalidTeammateDeleteIndex_throwsCommandException() {
        Index outOfBoundTeammateIndex = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK, new HashSet<>(),
                new HashSet<>(), new HashSet<>(List.of(outOfBoundTeammateIndex)), new HashSet<>());

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_INDEX_CUSTOM,
                        outOfBoundTeammateIndex.getOneBased()) + '\n');
    }

    @Test
    public void execute_invalidSingleTeammateAddName_throwsCommandException() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK, new HashSet<>(),
                new HashSet<>(List.of("NotAName")), new HashSet<>(), new HashSet<>());

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_NAME,
                        "NotAName") + "\n");
    }

    @Test
    public void execute_invalidTeammateDeleteName_throwsCommandException() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK, new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(List.of("NotAName")));

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_NAME,
                        "NotAName") + "\n");
    }

    @Test
    public void execute_invalidTeammateAddNameAndIndex_throwsCommandException() {
        Index outOfBoundTeammateIndex = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(outOfBoundTeammateIndex)), new HashSet<>(Arrays.asList("NotAName")),
                new HashSet<>(), new HashSet<>());

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_INDEX_CUSTOM,
                        outOfBoundTeammateIndex.getOneBased()) + "\n"
                        + String.format(Messages.MESSAGE_INVALID_TEAMMATE_NAME, "NotAName") + "\n");
    }

    @Test
    public void execute_invalidTeammateDeleteNameAndIndex_throwsCommandException() {
        Index outOfBoundTeammateIndex = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK, new HashSet<>(),
                new HashSet<>(), new HashSet<>(List.of(outOfBoundTeammateIndex)),
                new HashSet<>(Arrays.asList("NotAName")));

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_INDEX_CUSTOM,
                        outOfBoundTeammateIndex.getOneBased()) + "\n"
                        + String.format(Messages.MESSAGE_INVALID_TEAMMATE_NAME, "NotAName") + "\n");
    }

    @Test
    public void execute_invalidTeammateDeleteAddNameAndIndex_throwsCommandException() {
        Index outOfBoundTeammateIndexOne = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        Index outOfBoundTeammateIndexTwo = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(outOfBoundTeammateIndexOne)), new HashSet<>(Arrays.asList("NotAName")),
                new HashSet<>(List.of(outOfBoundTeammateIndexTwo)), new HashSet<>(Arrays.asList("NonName")));

        assertCommandFailure(assignTaskCommand, model,
                String.format(Messages.MESSAGE_INVALID_TEAMMATE_INDEX_CUSTOM,
                        outOfBoundTeammateIndexOne.getOneBased()) + "\n"
                        + String.format(Messages.MESSAGE_INVALID_TEAMMATE_INDEX_CUSTOM,
                        outOfBoundTeammateIndexTwo.getOneBased()) + "\n"
                        + String.format(Messages.MESSAGE_INVALID_TEAMMATE_NAME, "NotAName") + "\n"
                        + String.format(Messages.MESSAGE_INVALID_TEAMMATE_NAME, "NonName") + "\n");
    }

    @Test
    public void execute_validTeammateAddIndex_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(INDEX_SECOND_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>());

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts("Benson Meier", "Alice Pauline").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateAddName_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(List.of("Benson Meier")),
                new HashSet<>(), new HashSet<>());

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts("Benson Meier", "Alice Pauline").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateDeleteIndex_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(),
                new HashSet<>(List.of(INDEX_FIRST_TEAMMATE)), new HashSet<>());

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask = new TaskBuilder(taskToEdit).withContacts().build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateDeleteName_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(List.of("Alice Pauline")));

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts().build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateAddAndDeleteName_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(List.of("Benson Meier")),
                new HashSet<>(), new HashSet<>(List.of("Alice Pauline")));

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts("Benson Meier").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateAddAndDeleteIndex_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(INDEX_SECOND_TEAMMATE)), new HashSet<>(),
                new HashSet<>(List.of(INDEX_FIRST_TEAMMATE)), new HashSet<>());

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts("Benson Meier").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateAddAndDeleteMix_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(INDEX_SECOND_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>(List.of("Alice Pauline")));

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts("Benson Meier").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTeammateAddAndDeleteMultiple_success() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_SECOND_TEAMMATE, INDEX_THIRD_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>(List.of("Alice Pauline")));

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask =
                new TaskBuilder(taskToEdit).withContacts("Benson Meier", "Carl Kurz").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_teammateToAddAlreadyAdded_successWithInfo() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(INDEX_FIRST_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>());

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask = new TaskBuilder(taskToEdit).withContacts("Alice Pauline").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_REPEATED_CONTACT, "Alice Pauline") + "\n"
                + String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_teammateToDeleteNotAssigned_successWithInfo() {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(),
                new HashSet<>(List.of(INDEX_SECOND_TEAMMATE)), new HashSet<>());

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        Task editedTask = new TaskBuilder(taskToEdit).withContacts("Alice Pauline").build();

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_CONTACT_DOES_NOT_EXIT, "Benson Meier") + "\n"
                + String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTaskIndexAndAddTeammatesName_assignSuccessful() throws Exception {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(List.of("George Best")),
                new HashSet<>(), new HashSet<>());

        CommandResult commandResult = assignTaskCommand.execute(model);

        assertEquals(String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validTaskAndAddTeammatesIndexes_assignSuccessful() throws Exception {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(List.of(INDEX_SECOND_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>());

        CommandResult commandResult = assignTaskCommand.execute(model);

        assertEquals(String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validTaskAndDeleteTeammatesIndexes_assignSuccessful() throws Exception {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(),
                new HashSet<>(List.of(INDEX_FIRST_TEAMMATE)), new HashSet<>());

        CommandResult commandResult = assignTaskCommand.execute(model);

        assertEquals(String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validTaskIndexAndDeleteTeammatesName_assignSuccessful() throws Exception {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(List.of("Alice Pauline")));

        CommandResult commandResult = assignTaskCommand.execute(model);

        assertEquals(String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {

        AssignTaskCommand firstCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>());
        AssignTaskCommand secondCommand = new AssignTaskCommand(INDEX_SECOND_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE)), new HashSet<>(),
                new HashSet<>(), new HashSet<>());
        AssignTaskCommand thirdCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE, INDEX_THIRD_TEAMMATE)),
                new HashSet<>(), new HashSet<>(), new HashSet<>());
        AssignTaskCommand fourthCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE)), new HashSet<>(Arrays.asList(
                "Alex", "Bernice")),
                new HashSet<>(), new HashSet<>());
        AssignTaskCommand fifthCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE)), new HashSet<>(Arrays.asList(
                "Alex", "Bernice")),
                new HashSet<>(List.of(INDEX_THIRD_TEAMMATE)), new HashSet<>());
        AssignTaskCommand sixthCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE)), new HashSet<>(Arrays.asList(
                "Alex", "Bernice")),
                new HashSet<>(List.of(INDEX_THIRD_TEAMMATE)), new HashSet<>(Arrays.asList(
                "Charlotte", "David")));
        AssignTaskCommand sixthCommandCopy = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_TEAMMATE, INDEX_SECOND_TEAMMATE)), new HashSet<>(Arrays.asList(
                "Alex", "Bernice")),
                new HashSet<>(List.of(INDEX_THIRD_TEAMMATE)), new HashSet<>(Arrays.asList(
                "Charlotte", "David")));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        assertTrue(sixthCommand.equals(sixthCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different task -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different add contacts indexes -> returns false
        assertFalse(firstCommand.equals(thirdCommand));

        // different add contacts names -> returns false
        assertFalse(firstCommand.equals(fourthCommand));

        // different delete contacts indexes -> returns false
        assertFalse(fourthCommand.equals(fifthCommand));

        // different delete contacts names -> returns false
        assertFalse(fifthCommand.equals(sixthCommand));
    }

}
