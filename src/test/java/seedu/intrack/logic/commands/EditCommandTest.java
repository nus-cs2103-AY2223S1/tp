package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.testutil.EditInternshipDescriptorBuilder;
import seedu.intrack.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship selectedInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());
        // An internship must be selected before Edit can be used
        model.updateSelectedInternship(a -> a.isSameInternship(selectedInternship));

        InternshipBuilder internshipInList = new InternshipBuilder(selectedInternship);
        Internship editedInternship = internshipInList.withName(VALID_NAME_MSFT).withSalary(VALID_SALARY_MSFT)
                .withTags(VALID_TAG_URGENT).build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_MSFT)
                .withSalary(VALID_SALARY_MSFT).withTags(VALID_TAG_URGENT).build();
        EditCommand editCommand = new EditCommand(descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        expectedModel.setInternship(selectedInternship, editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Internship selectedInternship = model.getFilteredInternshipList().get(0);
        // An internship must be selected before Edit can be used
        model.updateSelectedInternship(a -> a.isSameInternship(selectedInternship));

        EditCommand editCommand = new EditCommand(new EditInternshipDescriptor());
        Internship editedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship selectedInternship = model.getFilteredInternshipList().get(0);
        // An internship must be selected before Edit can be used
        model.updateSelectedInternship(a -> a.isSameInternship(selectedInternship));

        Internship internshipInFilteredList = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(internshipInFilteredList).withName(VALID_NAME_MSFT).build();
        EditCommand editCommand = new EditCommand(
                new EditInternshipDescriptorBuilder().withName(VALID_NAME_MSFT).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        expectedModel.updateFilteredInternshipList(a -> a.isSameInternship(selectedInternship));
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship secondInternship = model.getFilteredInternshipList().get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        // An internship must be selected before Edit can be used
        model.updateSelectedInternship(a -> a.isSameInternship(secondInternship));

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        EditCommand editCommand = new EditCommand(descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Internship firstInternship = model.getFilteredInternshipList().get(0);
        // An internship must be selected before Edit can be used
        model.updateSelectedInternship(a -> a.isSameInternship(firstInternship));

        // edit internship in filtered list into a duplicate in internship tracker
        Internship internshipInList = model.getInTrack().getInternshipList()
                .get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        EditCommand editCommand = new EditCommand(new EditInternshipDescriptorBuilder(internshipInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(DESC_AAPL);

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_AAPL);
        EditCommand commandWithSameValues = new EditCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(DESC_MSFT)));
    }

}
