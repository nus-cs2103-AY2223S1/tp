package seedu.workbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.workbook.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.core.Messages;
import seedu.workbook.commons.core.index.Index;
import seedu.workbook.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.testutil.EditInternshipDescriptorBuilder;
import seedu.workbook.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalWorkBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship editedInternship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(editedInternship).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new WorkBook(model.getWorkBook()), new UserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);
        expectedModel.commitWorkBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship lastInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        Internship editedInternship = internshipInList.withCompany(VALID_COMPANY_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastInternship, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new WorkBook(model.getWorkBook()), new UserPrefs());
        expectedModel.setInternship(lastInternship, editedInternship);
        expectedModel.commitWorkBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP, new EditInternshipDescriptor());
        Internship editedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new WorkBook(model.getWorkBook()), new UserPrefs());
        expectedModel.commitWorkBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipInFilteredList = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(internshipInFilteredList).withCompany(VALID_COMPANY_BOB)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new WorkBook(model.getWorkBook()), new UserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);
        expectedModel.commitWorkBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_INTERNSHIP, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        // edit internship in filtered list into a duplicate in work book
        Internship internshipInList = model.getWorkBook().getInternshipList()
                .get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder(internshipInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_BOB)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of work book
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of work book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkBook().getInternshipList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_INTERNSHIP, DESC_AMY);

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_INTERNSHIP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_INTERNSHIP, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_INTERNSHIP, DESC_BOB)));
    }

}
