package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.hideAllTasks;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import java.util.Arrays;
import java.util.HashSet;
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
        assertCommandSuccess(
                new ListTasksCommand(Optional.empty(), new HashSet<>()),
                model,
                String.format(ListTasksCommand.MESSAGE_SUCCESS, expectedModel.getFilteredTaskList().size(), "", ""),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        hideAllTasks(model);
        assertCommandSuccess(
                new ListTasksCommand(Optional.empty(), new HashSet<>()),
                model,
                String.format(ListTasksCommand.MESSAGE_SUCCESS, expectedModel.getFilteredTaskList().size(), "", ""),
                expectedModel
        );
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Set<Index> personIndexes = new HashSet<>(Arrays.asList(outOfBoundIndex));
        ListTasksCommand c = new ListTasksCommand(Optional.empty(), personIndexes);

        assertCommandFailure(c, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_keyword_singleResult() {
        hideAllTasks(model);

        Predicate<Task> filter = new TitleContainsKeywordPredicate("ass");
        expectedModel.updateFilteredTaskList(filter);

        assertCommandSuccess(
                new ListTasksCommand(Optional.of("ass"), new HashSet<>()),
                model,
                String.format(ListTasksCommand.MESSAGE_SUCCESS, 1, "containing 'ass'", ""),
                expectedModel
        );
    }

    @Test
    public void execute_keyword_noResults() {
        hideAllTasks(model);

        Predicate<Task> filter = new TitleContainsKeywordPredicate("test");
        expectedModel.updateFilteredTaskList(filter);

        assertCommandSuccess(
                new ListTasksCommand(Optional.of("test"), new HashSet<>()),
                model,
                String.format(ListTasksCommand.MESSAGE_SUCCESS, 0, "containing 'test'", ""),
                expectedModel
        );
    }

    @Test
    public void execute_contact_singleResults() throws CommandException {
        hideAllTasks(model);
        Set<Index> personIndexes = new HashSet<>(Arrays.asList(TypicalIndexes.INDEX_FIRST_PERSON));

        Predicate<Task> keywordPredicate = new TitleContainsKeywordPredicate("ass");
        AssignedToContactsPredicate assignedToContactsPredicate =
                new AssignedToContactsPredicate(expectedModel, personIndexes);

        expectedModel.updateFilteredTaskList(keywordPredicate.and(assignedToContactsPredicate));

        assertCommandSuccess(
                new ListTasksCommand(Optional.empty(), personIndexes),
                model,
                String.format(
                        ListTasksCommand.MESSAGE_SUCCESS,
                        1,
                        "",
                        String.format("that are assigned to %s", assignedToContactsPredicate.getContactNames())
                ),
                expectedModel
        );
    }

    @Test
    public void execute_keywordAndContact_noResults() throws CommandException {
        hideAllTasks(model);
        Set<Index> personIndexes = new HashSet<>(Arrays.asList(TypicalIndexes.INDEX_FIRST_PERSON));
        AssignedToContactsPredicate filter = new AssignedToContactsPredicate(expectedModel, personIndexes);

        expectedModel.updateFilteredTaskList(filter);

        assertCommandSuccess(
                new ListTasksCommand(Optional.of("ass"), personIndexes),
                model,
                String.format(
                        ListTasksCommand.MESSAGE_SUCCESS,
                        0,
                        "containing 'ass'",
                        String.format("that are assigned to %s", filter.getContactNames())
                ),
                expectedModel
        );
    }

}
