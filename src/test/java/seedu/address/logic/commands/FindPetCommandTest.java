package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalPets;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

public class FindPetCommandTest {
    private Model model = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindPetCommand findFirstCommand = new FindPetCommand(
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")));
        FindPetCommand findSecondCommand = new FindPetCommand(
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("second")));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPetCommand findFirstCommandCopy = new FindPetCommand(
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPetFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PetNameContainsKeywordsPredicate<Pet> predicate = preparePredicatePet("    ");
        FindPetCommand command = new FindPetCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPetList());
    }

    @Test
    public void execute_multipleKeywords_multiplePetsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PetNameContainsKeywordsPredicate<Pet> predicate = preparePredicatePet("doja plum");
        FindPetCommand command = new FindPetCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPets.DOJA, TypicalPets.PLUM),
                model.getFilteredPetList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PetNameContainsKeywordsPredicate<Pet> preparePredicatePet(String userInput) {
        return new PetNameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }

}
