package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Index;
import seedu.phu.commons.core.index.Indexes;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

public class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToView = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEXES_FIRST_INTERNSHIP);

        UniqueInternshipList listOfInternshipsToView = new UniqueInternshipList();
        listOfInternshipsToView.add(internshipToView);

        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS,
                listOfInternshipsToView);
        expectedModel.viewInternship(internshipToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(new Indexes(Set.of(outOfBoundIndex)));

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToView = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEXES_FIRST_INTERNSHIP);

        UniqueInternshipList listOfInternshipsToView = new UniqueInternshipList();
        listOfInternshipsToView.add(internshipToView);

        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS,
                listOfInternshipsToView);
        expectedModel.viewInternship(internshipToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        ViewCommand viewCommand = new ViewCommand(new Indexes(Set.of(outOfBoundIndex)));

        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getInternshipList().size());

        DeleteCommand deleteCommand = new DeleteCommand(new Indexes(Set.of(outOfBoundIndex)));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }
}
