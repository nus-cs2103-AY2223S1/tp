package seedu.address.logic.commands.deletecommands;
/*
//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
//import static seedu.address.testutil.TypicalPets.getTypicalPetsAddressBook;

//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.pet.Pet;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePetCommand}.
 */
/*
public class DeletePetCommandTest {

    private Model modelForPets = new ModelManager(getTypicalPetsAddressBook(), new UserPrefs());

    // Pet List Tests
    @Test
    public void execute_validIndexUnfilteredPetList_success() {
        Pet petToDelete = modelForPets.getFilteredPetList().get(INDEX_FIRST.getZeroBased());
        DeletePetCommand deletePetCommand = new DeletePetCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeletePetCommand.MESSAGE_DELETE_PET_SUCCESS, petToDelete);

        ModelManager expectedModel = new ModelManager(modelForPets.getAddressBook(), new UserPrefs());
        expectedModel.deletePet(petToDelete);

        assertCommandSuccess(deletePetCommand, modelForPets, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredPetList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForPets.getFilteredPetList().size() + 1);
        DeletePetCommand deletePetCommand = new DeletePetCommand(outOfBoundIndex);

        assertCommandFailure(deletePetCommand, modelForPets, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredPetList_success() {
        showPetAtIndex(modelForPets, INDEX_FIRST);

        Pet personToDelete = modelForPets.getFilteredPetList().get(INDEX_FIRST.getZeroBased());
        DeletePetCommand deletePetCommand = new DeletePetCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeletePetCommand.MESSAGE_DELETE_PET_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForPets.getAddressBook(), new UserPrefs());
        expectedModel.deletePet(personToDelete);
        showNoPet(expectedModel);

        assertCommandSuccess(deletePetCommand, modelForPets, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredPetList_throwsCommandException() {
        showPetAtIndex(modelForPets, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelForPets.getAddressBook().getPetList().size());

        DeletePetCommand deletePetCommand = new DeletePetCommand(outOfBoundIndex);

        assertCommandFailure(deletePetCommand, modelForPets, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_pet() {
        DeletePetCommand deleteFirstCommand = new DeletePetCommand(INDEX_FIRST);
        DeletePetCommand deleteSecondCommand = new DeletePetCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePetCommand deleteFirstCommandCopy = new DeletePetCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        }

    /**
     * Updates {@code model}'s filtered list to show no Pets.
     */
/*
    private void showNoPet(Model model) {
        model.updateFilteredPetList(p -> false);

        assertTrue(model.getFilteredPetList().isEmpty());
    }
}
*/
