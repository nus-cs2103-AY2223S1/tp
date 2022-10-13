package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Status;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StatusCommand.
 */
class StatusCommandTest {

    private static final String STATUS_STUB = "PROGRESS";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //not testing equals and deletestatus yet
    @Test
    void execute_updateStatusUnfilteredList_success() {

        Internship firstInternship = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(firstInternship)
                .withStatus(STATUS_STUB).build();

        StatusCommand statusCommand = new StatusCommand(INDEX_FIRST_INTERNSHIP,
                new Status(editedInternship.getStatus().value));

        String expectedMessage = String.format(statusCommand.MESSAGE_UPDATE_STATUS_SUCCESS,
                editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(statusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased()))
                .withStatus(STATUS_STUB).build();

        StatusCommand statusCommand = new StatusCommand(INDEX_FIRST_INTERNSHIP,
                new Status(editedInternship.getStatus().value));

        String expectedMessage = String.format(statusCommand.MESSAGE_UPDATE_STATUS_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(statusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        StatusCommand statusCommand = new StatusCommand(outOfBoundIndex, new Status(VALID_STATUS_BOB));

        assertCommandFailure(statusCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
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

        StatusCommand statusCommand = new StatusCommand(outOfBoundIndex, new Status(VALID_STATUS_BOB));

        assertCommandFailure(statusCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }


}
