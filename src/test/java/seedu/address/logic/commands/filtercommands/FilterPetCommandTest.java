package seedu.address.logic.commands.filtercommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
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
                new ColorContainsKeywordsPredicate<>(Arrays.asList("yellow")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("pikachu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("red")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("charizard")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(71594.4)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));

        assertTrue(firstCommand.equals(firstCommand));

        FilterPetCommand firstCommandCopy = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("yellow")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("pikachu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));

        assertTrue(firstCommand.equals(firstCommandCopy));

        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentColor_returnsFalse() {
        FilterPetCommand firstCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange-alola")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentName_returnsFalse() {
        FilterPetCommand firstCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("charmander")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentPrice_returnsFalse() {
        FilterPetCommand firstCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(16764.878)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")),
                new VaccinationStatusPredicate<>(true));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentSpecies_returnsFalse() {
        FilterPetCommand firstCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new VaccinationStatusPredicate<>(true));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("alolan raichu")),
                new VaccinationStatusPredicate<>(true));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentVaccination_returnsFalse() {
        FilterPetCommand firstCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new VaccinationStatusPredicate<>(false));
        FilterPetCommand secondCommand = new FilterPetCommand(
                new ColorContainsKeywordsPredicate<>(Arrays.asList("orange")),
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new PriceContainsKeywordsPredicate<>(Arrays.asList(109.54)),
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("raichu")),
                new VaccinationStatusPredicate<>(true));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void generatePredicate_noMatchingKeywords_noPetsFound() {
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_PETS_LISTED_OVERVIEW, 0),
                false, false, true, ListCommand.LIST_PET, false,
                null, false, null, null);

        ColorContainsKeywordsPredicate<Pet> colorContainsKeywordsPredicate =
                new ColorContainsKeywordsPredicate<>(Arrays.asList("turquoise"));
        PetNameContainsKeywordsPredicate<Pet> petNameContainsKeywordsPredicate =
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("blastoise"));
        PriceContainsKeywordsPredicate<Pet> priceContainsKeywordsPredicate =
                new PriceContainsKeywordsPredicate<>(Arrays.asList(1763.4));
        SpeciesContainsKeywordsPredicate<Pet> speciesContainsKeywordsPredicate =
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon"));
        VaccinationStatusPredicate<Pet> vaccinationStatusPredicate = new VaccinationStatusPredicate<>(true);

        FilterPetCommand command = new FilterPetCommand(colorContainsKeywordsPredicate,
                petNameContainsKeywordsPredicate, priceContainsKeywordsPredicate, speciesContainsKeywordsPredicate,
                vaccinationStatusPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
        assertEquals(Collections.emptyList(), pModel.getFilteredPetList());
    }

    @Test
    public void generatePredicate_matchingColor_onePetFound() {
        ColorContainsKeywordsPredicate<Pet> colorContainsKeywordsPredicate =
                new ColorContainsKeywordsPredicate<>(Arrays.asList("white"));
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
        };

        FilterPetCommand command = new FilterPetCommand(colorContainsKeywordsPredicate, defaultPredicate,
                defaultPredicate, defaultPredicate, defaultPredicate);
        command.execute(pModel);

        for (Pet pet : pModel.getFilteredPetList()) {
            assertTrue(TypicalPets.DOJA.isSamePet(pet));
        }
    }

    @Test
    public void generatePredicate_matchingPetName_onePetFound() {
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_PETS_LISTED_OVERVIEW, 1),
                false, false, true, ListCommand.LIST_PET, false,
                null, false, null, null);

        PetNameContainsKeywordsPredicate<Pet> petNameContainsKeywordsPredicate =
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("Doja"));
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
        };

        FilterPetCommand command = new FilterPetCommand(defaultPredicate, petNameContainsKeywordsPredicate,
                defaultPredicate, defaultPredicate, defaultPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
        assertEquals(Arrays.asList(TypicalPets.DOJA), pModel.getFilteredPetList());
    }

    @Test
    public void generatePredicate_matchingPrice_onePetFound() {
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_PETS_LISTED_OVERVIEW, 1),
                false, false, true, ListCommand.LIST_PET, false,
                null, false, null, null);
        PriceContainsKeywordsPredicate<Pet> priceContainsKeywordsPredicate =
                new PriceContainsKeywordsPredicate<>(Arrays.asList(600.00));
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
        };

        FilterPetCommand command = new FilterPetCommand(defaultPredicate, defaultPredicate,
                priceContainsKeywordsPredicate, defaultPredicate, defaultPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
        assertEquals(Arrays.asList(TypicalPets.PLUM), pModel.getFilteredPetList());
    }

    @Test
    public void generatePredicate_matchingSpecies_twoPetsFound() {
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_PETS_LISTED_OVERVIEW, 2),
                false, false, true, ListCommand.LIST_PET, false,
                null, false, null, null);

        SpeciesContainsKeywordsPredicate<Pet> speciesContainsKeywordsPredicate =
                new SpeciesContainsKeywordsPredicate<>(Arrays.asList("cat"));
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
        };

        FilterPetCommand command = new FilterPetCommand(defaultPredicate, defaultPredicate, defaultPredicate,
                speciesContainsKeywordsPredicate, defaultPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
        assertEquals(TypicalPets.getTypicalPets(), pModel.getFilteredPetList());
    }

    @Test
    public void generatePredicate_matchingVaccinationStatus_onePetFound() {
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_PETS_LISTED_OVERVIEW, 2),
                false, false, true, ListCommand.LIST_PET, false,
                null, false, null, null);

        VaccinationStatusPredicate<Pet> vaccinationStatusPredicate = new VaccinationStatusPredicate<>(true);
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
        };

        FilterPetCommand command = new FilterPetCommand(defaultPredicate, defaultPredicate, defaultPredicate,
                defaultPredicate, vaccinationStatusPredicate);
        pExpectedModel.updateFilteredPetList(command.generatePredicate());
        assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
        assertEquals(TypicalPets.getTypicalPets(), pModel.getFilteredPetList());
    }
}
