package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.hideAllTasks;
import static seedu.address.logic.commands.CommandTestUtil.showTeammateAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.AssignedToContactsPredicate;
import seedu.address.model.task.ContainsProjectsPredicate;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineIsAfterPredicate;
import seedu.address.model.task.DeadlineIsBeforePredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordPredicate;
import seedu.address.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTasksCommand.
 */
public class ListTasksCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        hideAllTasks(model);

        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTeammateAtIndex(model, INDEX_FIRST_TEAMMATE);

        Index outOfBoundIndex = INDEX_SECOND_TEAMMATE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTeammateList().size());

        Set<Index> teammateIndexes = new HashSet<>(Arrays.asList(outOfBoundIndex));

        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        teammateIndexes
                );

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_keyword_singleResult() {
        hideAllTasks(model);

        Predicate<Task> filter = Model.PREDICATE_INCOMPLETE_TASKS.and(new TitleContainsKeywordPredicate("ass"));
        expectedModel.updateFilteredTaskList(filter);

        ListTasksCommand command =
                new ListTasksCommand(
                        "ass",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_keyword_noResults() {
        hideAllTasks(model);

        Predicate<Task> filter = Model.PREDICATE_INCOMPLETE_TASKS.and(new TitleContainsKeywordPredicate("test"));
        expectedModel.updateFilteredTaskList(filter);

        ListTasksCommand command =
                new ListTasksCommand(
                        "test",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_afterDeadline() {
        Predicate<Task> filter =
                new DeadlineIsAfterPredicate(Deadline.of(LocalDate.of(2022, 9, 20)));

        expectedModel.updateFilteredTaskList(filter);

        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(ListTasksCommand.ALL_FLAG),
                        Optional.empty(),
                        Optional.of(Deadline.of(LocalDate.of(2022, 9, 20))),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_beforeDeadline() {
        Predicate<Task> filter =
                new DeadlineIsBeforePredicate(Deadline.of(LocalDate.of(2022, 9, 20)));

        expectedModel.updateFilteredTaskList(filter);

        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(ListTasksCommand.ALL_FLAG),
                        Optional.of(Deadline.of(LocalDate.of(2022, 9, 20))),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_contact_singleResults() throws CommandException {
        hideAllTasks(model);
        Set<Index> teammateIndexes = new HashSet<>(Arrays.asList(TypicalIndexes.INDEX_FIRST_TEAMMATE));
        AssignedToContactsPredicate filter = new AssignedToContactsPredicate(expectedModel, teammateIndexes);

        expectedModel.updateFilteredTaskList(filter);

        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        teammateIndexes
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_keywordAndContact_noResults() throws CommandException {
        hideAllTasks(model);
        Set<Index> teammateIndexes = new HashSet<>(Arrays.asList(TypicalIndexes.INDEX_FIRST_TEAMMATE));

        Predicate<Task> basePredicate = Model.PREDICATE_INCOMPLETE_TASKS.and(new TitleContainsKeywordPredicate("ass"));
        AssignedToContactsPredicate assignedToContactsPredicate =
                new AssignedToContactsPredicate(expectedModel, teammateIndexes);

        expectedModel.updateFilteredTaskList(basePredicate.and(assignedToContactsPredicate));

        ListTasksCommand command =
                new ListTasksCommand(
                        "ass",
                        List.of(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        teammateIndexes
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_keywordAndProject_noResults() throws CommandException {
        hideAllTasks(model);
        Predicate<Task> basePredicate = Model.PREDICATE_INCOMPLETE_TASKS.and(new TitleContainsKeywordPredicate("ass"));

        List<String> projectNames = List.of("Test Project");
        ContainsProjectsPredicate projectsPredicate = new ContainsProjectsPredicate(projectNames);

        expectedModel.updateFilteredTaskList(basePredicate.and(projectsPredicate));

        ListTasksCommand command =
                new ListTasksCommand(
                        "ass",
                        projectNames,
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }

    @Test
    public void execute_multipleProjects_noResults() throws CommandException {
        hideAllTasks(model);
        Predicate<Task> basePredicate = Model.PREDICATE_INCOMPLETE_TASKS;

        List<String> projectNames = List.of("Test Project", "Another Project");
        ContainsProjectsPredicate projectsPredicate = new ContainsProjectsPredicate(projectNames);

        expectedModel.updateFilteredTaskList(basePredicate.and(projectsPredicate));

        ListTasksCommand command =
                new ListTasksCommand(
                        "",
                        projectNames,
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        new HashSet<>()
                );

        assertCommandSuccess(
                command,
                model,
                command.getSuccessMessage(),
                expectedModel
        );
    }
}
