package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESC_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESC_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_NAME_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipCommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInternshipCommand.EditInternshipDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.EditInternshipDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditInternshipCommand.
 */
class EditInternshipCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship editedInternship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(editedInternship).build();
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(INDEX_FIRST_INTERNSHIP, descriptor);

        String expectedMessage = String.format(EditInternshipCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editInternshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship lastInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        Internship editedInternship = internshipInList
                .withCompanyName(VALID_NAME_BOBBY)
                .withRole(VALID_ROLE_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY)
                .build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_NAME_BOBBY)
                .withInternshipRole(VALID_ROLE_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY).build();
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(indexLastInternship, descriptor);

        String expectedMessage = String.format(EditInternshipCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setInternship(lastInternship, editedInternship);

        assertCommandSuccess(editInternshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(
                INDEX_FIRST_INTERNSHIP, new EditInternshipDescriptor());
        Internship editedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        String expectedMessage = String.format(EditInternshipCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editInternshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipInFilteredList = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        Internship editedInternship = new InternshipBuilder(internshipInFilteredList)
                .withCompanyName(VALID_NAME_BOBBY).build();
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(
                INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_NAME_BOBBY).build());

        String expectedMessage = String.format(EditInternshipCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editInternshipCommand, model, expectedMessage, expectedModel);
    }

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(INDEX_SECOND_INTERNSHIP, descriptor);

        assertCommandFailure(editInternshipCommand, model, EditInternshipCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        // edit Internship in filtered list into a duplicate in address book
        Internship internshipInList = model.getAddressBook().getInternshipList()
                .get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(
                INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder(internshipInList).build());

        assertCommandFailure(editInternshipCommand, model, EditInternshipCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_NAME_BOBBY).build();
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editInternshipCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(outOfBoundIndex,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_NAME_BOBBY).build());

        assertCommandFailure(editInternshipCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditInternshipCommand standardCommand = new EditInternshipCommand(INDEX_FIRST_INTERNSHIP, DESC_ABC);

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_ABC);
        EditInternshipCommand commandWithSameValues = new EditInternshipCommand(INDEX_FIRST_INTERNSHIP, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditInternshipCommand(INDEX_SECOND_INTERNSHIP, DESC_ABC));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditInternshipCommand(INDEX_FIRST_INTERNSHIP, DESC_BOBBY));
    }


}
