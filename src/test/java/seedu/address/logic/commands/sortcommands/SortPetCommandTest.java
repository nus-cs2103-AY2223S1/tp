package seedu.address.logic.commands.sortcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.SortCommandParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.TypicalPets;

public class SortPetCommandTest {
    private Model pModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
    private Model pExpectedModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());

    private final Integer firstAttributePos = 0;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortPetCommand(null));
    }

    @Test
    public void execute_noAttributes_noDifference() {
        CommandResult expectedResult = new CommandResult(SortPetCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Pet> comparator = getPetComparator("");
            SortPetCommand command = new SortPetCommand(comparator);
            assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
            assertEquals(pModel.getFilteredPetList(), pExpectedModel.getFilteredPetList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_oneAttributes_sortedAccordingToThatAttribute() {
        CommandResult expectedResult = new CommandResult(SortPetCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Pet> comparator = getPetComparator("Species");
            SortPetCommand command = new SortPetCommand(comparator);
            pExpectedModel.sortPet(comparator);
            assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
            assertEquals(pModel.getFilteredPetList(), pExpectedModel.getFilteredPetList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_multipleAttributes_sortedAccordingToThoseAttributes() {
        CommandResult expectedResult = new CommandResult(SortPetCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Pet> comparator = getPetComparator("Weight Height Species");
            SortPetCommand command = new SortPetCommand(comparator);
            pExpectedModel.sortPet(comparator);
            assertCommandSuccess(command, pModel, expectedResult, pExpectedModel);
            assertEquals(pModel.getFilteredPetList(), pExpectedModel.getFilteredPetList());
        } catch (ParseException e) {
            assert false;
        }
    }

    private Comparator<Pet> getPetComparator(String attributes) throws ParseException {
        String[] attributesArr = attributes.split("\\s+");
        assertAlphabets(attributesArr[firstAttributePos]);
        Comparator<Pet> comparator = SortCommandParserUtil.parseToSelectedPetComparator(
                attributesArr[firstAttributePos]);
        for (int i = 1; i < attributesArr.length; i++) {
            assertAlphabets(attributesArr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedPetComparator(attributesArr[i]));
        }
        return comparator;
    }

    private boolean isAlphabets(String attribute) {
        return attribute != null && attribute.matches("^[a-zA-Z]*$");
    }

    private void assertAlphabets(String attribute) throws ParseException {
        if (!isAlphabets(attribute)) {
            throw new ParseException(String.format(SortCommand.MESSAGE_ONLY_ALPHABET_PARAMETERS, attribute));
        }
    }
}
