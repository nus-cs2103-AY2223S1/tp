package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.ColorContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PriceContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.SpeciesContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.VaccinationStatusPredicate;
import seedu.address.testutil.TypicalPets;

public class FilterPetCommandTest {
    private Model pModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
    private Model pExpectedModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterPetCommand firstCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("white")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(5.6)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("cat")),
                new VaccinationStatusPredicate<>(true));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("brown")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(5.6)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("cat")),
                new VaccinationStatusPredicate<>(false));

        assertTrue(firstCommand.equals(firstCommand));

        FilterPetCommand firstCommandCopy = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("white")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(5.6)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("cat")),
                new VaccinationStatusPredicate<>(true));

        assertFalse(firstCommand.equals(1));

        assertFalse(firstCommand.equals(null));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void generatePredicate_zeroKeywords_noPetsFound() {
        ColorContainsKeywordsPredicate<Pet> cPredicate = new ColorContainsKeywordsPredicate<>(Arrays.asList("white"));
        PetNameContainsKeywordsPredicate<Pet> nPredicate = new PetNameContainsKeywordsPredicate<>(
                Arrays.asList("first"));
        PriceContainsKeywordsPredicate<Pet> pPredicate = new PriceContainsKeywordsPredicate<>(Arrays.asList(5.6));
        SpeciesContainsKeywordsPredicate<Pet> sPredicate = new SpeciesContainsKeywordsPredicate<>(
                Arrays.asList("cat"));
        VaccinationStatusPredicate<Pet> vPredicate = new VaccinationStatusPredicate<>(true);

        FilterPetCommand command = new FilterPetCommand(cPredicate, nPredicate, pPredicate, sPredicate, vPredicate);
        pModel.updateFilteredPetList(command.generatePredicate());
        assertEquals(Collections.emptyList(), pModel.getFilteredPetList());
    }

    @Test
    public void execute_zeroKeywords_noPetFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ColorContainsKeywordsPredicate<Pet> cPredicate = new ColorContainsKeywordsPredicate<>(Arrays.asList("white"));
        PetNameContainsKeywordsPredicate<Pet> nPredicate = new PetNameContainsKeywordsPredicate<>(
                Arrays.asList("first"));
        PriceContainsKeywordsPredicate<Pet> pPredicate = new PriceContainsKeywordsPredicate<>(Arrays.asList(5.6));
        SpeciesContainsKeywordsPredicate<Pet> sPredicate = new SpeciesContainsKeywordsPredicate<>(
                Arrays.asList("cat"));
        VaccinationStatusPredicate<Pet> vPredicate = new VaccinationStatusPredicate<>(true);

        FilterPetCommand command = new FilterPetCommand(cPredicate, nPredicate, pPredicate, sPredicate, vPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedMessage, pExpectedModel);
        assertEquals(Collections.emptyList(), pModel.getFilteredPetList());
    }

    @Test
    public void execute_multipleKeywords_multiplePetsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
        };
        SpeciesContainsKeywordsPredicate<Pet> sPredicate = new SpeciesContainsKeywordsPredicate<>(
                Arrays.asList("cat"));

        FilterPetCommand command = new FilterPetCommand(defaultPredicate, defaultPredicate, defaultPredicate,
                sPredicate, defaultPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedMessage, pExpectedModel);
        assertEquals(Arrays.asList(TypicalPets.DOJA, TypicalPets.PLUM), pModel.getFilteredPetList());
    }

}
