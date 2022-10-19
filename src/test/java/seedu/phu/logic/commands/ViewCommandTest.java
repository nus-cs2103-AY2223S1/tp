package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.logic.commands.ViewCommand.MESSAGE_SUCCESS;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Index;
import seedu.phu.commons.core.index.Indexes;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.ExactMatchPredicate;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

public class ViewCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void equals() throws ParseException {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        List<Internship> forPredicate = new ArrayList<>();
        forPredicate.add(firstInternship);

        ViewCommand firstViewCommand = new ViewCommand(INDEXES_FIRST_INTERNSHIP);
        ViewCommand secondViewCommand = new ViewCommand(INDEXES_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same categories and keywords -> returns true
        ViewCommand firstViewCommandCopy = new ViewCommand(INDEXES_FIRST_INTERNSHIP);
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different target index -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }

    @Test
    public void execute_validMessage_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        assertCommandSuccess(new ViewCommand(INDEXES_FIRST_INTERNSHIP), model,
                commandHistory, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        List<Internship> forPredicate = new ArrayList<>();
        forPredicate.add(firstInternship);
        model.updateViewItem(new ExactMatchPredicate(forPredicate));

        Internship internshipToView = model.getViewItem().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEXES_FIRST_INTERNSHIP);

        UniqueInternshipList listOfInternshipsToView = new UniqueInternshipList();
        listOfInternshipsToView.add(internshipToView);

        String expectedMessage = String.format(MESSAGE_SUCCESS,
                listOfInternshipsToView);
        expectedModel.viewInternship(internshipToView);

        assertCommandSuccess(viewCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(new Indexes(Set.of(outOfBoundIndex)));

        assertCommandFailure(viewCommand, model, commandHistory, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        showInternshipAtIndex(expectedModel, INDEX_FIRST_INTERNSHIP);

        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        List<Internship> forPredicate = new ArrayList<>();
        forPredicate.add(firstInternship);
        model.updateViewItem(new ExactMatchPredicate(forPredicate));

        ViewCommand viewCommand = new ViewCommand(INDEXES_FIRST_INTERNSHIP);

        UniqueInternshipList listOfInternshipsToView = new UniqueInternshipList();
        listOfInternshipsToView.add(firstInternship);

        String expectedMessage = String.format(MESSAGE_SUCCESS,
                listOfInternshipsToView);
        expectedModel.viewInternship(firstInternship);

        assertCommandSuccess(viewCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        ViewCommand viewCommand = new ViewCommand(new Indexes(Set.of(outOfBoundIndex)));

        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getInternshipList().size());

        assertCommandFailure(viewCommand, model, commandHistory, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }
}
