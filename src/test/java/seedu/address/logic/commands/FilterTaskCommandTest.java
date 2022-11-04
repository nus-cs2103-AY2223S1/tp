package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASK_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.BENSON;
import static seedu.address.testutil.TypicalTasks.CARL;
import static seedu.address.testutil.TypicalTasks.DANIEL;
import static seedu.address.testutil.TypicalTasks.ELLE;
import static seedu.address.testutil.TypicalTasks.FIONA;
import static seedu.address.testutil.TypicalTasks.GEORGE;
import static seedu.address.testutil.TypicalTasks.HILLARY;
import static seedu.address.testutil.TypicalTasks.IVY;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.FilterInfo;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryAndDeadlinePredicate;
import seedu.address.model.task.TaskCategoryType;
import seedu.address.model.task.TaskDate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final TaskCategory testCat = new TaskCategory(TaskCategoryType.OTHERS);
    private final TaskDate testDate = new TaskDate(LocalDate.now());
    private final TaskCategory testCat2 = new TaskCategory(TaskCategoryType.BACKEND);
    private final TaskDate testDate2 = new TaskDate(LocalDate.of(2022, 9, 20));
    private final TaskCategory testCat3 = new TaskCategory(TaskCategoryType.FRONTEND);
    private final FilterInfo testFilterInfo = new FilterInfo();


    @Test
    public void equals() {
        TaskCategoryAndDeadlinePredicate firstPredicate =
                new TaskCategoryAndDeadlinePredicate(Optional.of(testCat), Optional.of(testDate));
        TaskCategoryAndDeadlinePredicate secondPredicate =
                new TaskCategoryAndDeadlinePredicate(Optional.of(testCat2), Optional.of(testDate2));
        FilterTaskCommand.FilterTaskDescriptor descriptor1 = new FilterTaskCommand.FilterTaskDescriptor();
        descriptor1.setCategory(firstPredicate.getCategory());
        descriptor1.setDate(firstPredicate.getDate());
        FilterTaskCommand.FilterTaskDescriptor descriptor2 = new FilterTaskCommand.FilterTaskDescriptor();
        descriptor2.setCategory(secondPredicate.getCategory());
        descriptor2.setDate(secondPredicate.getDate());

        FilterTaskCommand findFirstCommand = new FilterTaskCommand(descriptor1, testFilterInfo, testFilterInfo);
        FilterTaskCommand findSecondCommand = new FilterTaskCommand(descriptor2, testFilterInfo, testFilterInfo);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterTaskCommand findFirstCommandCopy = new FilterTaskCommand(descriptor1, testFilterInfo, testFilterInfo);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different FilterTaskCommand with different TaskDescriptor -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void executeFilterByFrontendNoTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASK_LISTED_OVERVIEW, 0);
        TaskCategoryAndDeadlinePredicate predicate = preparePredicate(testCat3, testDate2);
        FilterTaskCommand.FilterTaskDescriptor testDescriptor = new FilterTaskCommand.FilterTaskDescriptor();
        testDescriptor.setCategory(predicate.getCategory());
        testDescriptor.setDate(predicate.getDate());

        FilterTaskCommand command = new FilterTaskCommand(testDescriptor, testFilterInfo,
                testFilterInfo);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void executeFilterByOthersMultipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASK_LISTED_OVERVIEW, 7);
        TaskCategoryAndDeadlinePredicate predicate = new TaskCategoryAndDeadlinePredicate(Optional.of(testCat),
                Optional.empty());
        FilterTaskCommand.FilterTaskDescriptor testDescriptor = new FilterTaskCommand.FilterTaskDescriptor();
        testDescriptor.setCategory(predicate.getCategory());
        testDescriptor.setDate(predicate.getDate());
        FilterTaskCommand command = new FilterTaskCommand(testDescriptor, testFilterInfo,
                testFilterInfo);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredTaskList());
    }

    @Test
    public void executeFilterByBackendOneTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASK_LISTED_OVERVIEW, 2);
        TaskCategoryAndDeadlinePredicate predicate = new TaskCategoryAndDeadlinePredicate(Optional.of(testCat2),
                Optional.empty());
        FilterTaskCommand.FilterTaskDescriptor testDescriptor = new FilterTaskCommand.FilterTaskDescriptor();
        testDescriptor.setCategory(predicate.getCategory());
        testDescriptor.setDate(predicate.getDate());
        FilterTaskCommand command = new FilterTaskCommand(testDescriptor, testFilterInfo,
                testFilterInfo);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HILLARY, IVY), model.getFilteredTaskList());
    }

    @Test
    public void executeFilterByDeadlineNoTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASK_LISTED_OVERVIEW, 0);
        TaskCategoryAndDeadlinePredicate predicate = new TaskCategoryAndDeadlinePredicate(Optional.empty(),
                Optional.of(testDate2));
        FilterTaskCommand.FilterTaskDescriptor testDescriptor = new FilterTaskCommand.FilterTaskDescriptor();
        testDescriptor.setCategory(predicate.getCategory());
        testDescriptor.setDate(predicate.getDate());
        FilterTaskCommand command = new FilterTaskCommand(testDescriptor, testFilterInfo,
                testFilterInfo);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void executeFilterByDeadlineAllTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASK_LISTED_OVERVIEW, 9);
        TaskCategoryAndDeadlinePredicate predicate = new TaskCategoryAndDeadlinePredicate(Optional.empty(),
                Optional.of(testDate));
        FilterTaskCommand.FilterTaskDescriptor testDescriptor = new FilterTaskCommand.FilterTaskDescriptor();
        testDescriptor.setCategory(predicate.getCategory());
        testDescriptor.setDate(predicate.getDate());
        FilterTaskCommand command = new FilterTaskCommand(testDescriptor, testFilterInfo,
                testFilterInfo);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA,
                GEORGE, HILLARY, IVY), model.getFilteredTaskList());
    }

    @Test
    public void executeFilterByDeadlineAndCategoryNoTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASK_LISTED_OVERVIEW, 0);
        TaskCategoryAndDeadlinePredicate predicate = new TaskCategoryAndDeadlinePredicate(Optional.of(testCat3),
                Optional.of(testDate));
        FilterTaskCommand.FilterTaskDescriptor testDescriptor = new FilterTaskCommand.FilterTaskDescriptor();
        testDescriptor.setCategory(predicate.getCategory());
        testDescriptor.setDate(predicate.getDate());
        FilterTaskCommand command = new FilterTaskCommand(testDescriptor, testFilterInfo,
                testFilterInfo);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }
    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TaskCategoryAndDeadlinePredicate preparePredicate(TaskCategory cat, TaskDate date) {
        return new TaskCategoryAndDeadlinePredicate(Optional.of(cat), Optional.of(date));
    }
}
