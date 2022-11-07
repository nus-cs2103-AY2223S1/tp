package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ClientTagContainsKeywordsPredicate;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.IncomeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NormalTagContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PlanTagContainsKeywordsPredicate;
import seedu.address.model.person.RiskTagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private static FindPredicate firstPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("first"));
    private static FindPredicate secondPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("second"));

    private static FindPredicate thirdPredicate =
            new NormalTagContainsKeywordsPredicate(Collections.singletonList("friends"));

    private static FindPredicate fourthPredicate =
            new RiskTagContainsKeywordsPredicate(Collections.singletonList("medium"));

    private static FindPredicate fifthPredicate =
            new PlanTagContainsKeywordsPredicate(Collections.singletonList("Savings Plan"));
    private static FindPredicate sixthPredicate =
                    new IncomeContainsKeywordsPredicate(Collections.singletonList("100"), ">");
    private static List<FindPredicate> allPredicates = new ArrayList<>();
    private static List<FindPredicate> firstAndSecondPredicates = new ArrayList<>();
    private static List<FindPredicate> secondAndThirdPredicates = new ArrayList<>();
    private static List<FindPredicate> thirdAndFourthPredicates = new ArrayList<>();
    private static List<FindPredicate> fourthAndFifthPredicates = new ArrayList<>();
    private static List<FindPredicate> fifthAndSixthPredicates = new ArrayList<>();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new CommandHistory());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new CommandHistory());

    @BeforeAll
    public static void setUp() {
        allPredicates.add(firstPredicate);
        allPredicates.add(secondPredicate);
        allPredicates.add(thirdPredicate);
        allPredicates.add(fourthPredicate);
        allPredicates.add(fifthPredicate);

        firstAndSecondPredicates.add(firstPredicate);
        firstAndSecondPredicates.add(secondPredicate);

        secondAndThirdPredicates.add(secondPredicate);
        secondAndThirdPredicates.add(thirdPredicate);

        thirdAndFourthPredicates.add(thirdPredicate);
        thirdAndFourthPredicates.add(fourthPredicate);

        fourthAndFifthPredicates.add(fourthPredicate);
        fourthAndFifthPredicates.add(fifthPredicate);

        fifthAndSixthPredicates.add(fifthPredicate);
        fifthAndSixthPredicates.add(sixthPredicate);
    }

    @Test
    public void equals() {
        FindCommand findAllCommand = new FindCommand(allPredicates);
        FindCommand findFirstAndSecondCommand = new FindCommand(firstAndSecondPredicates);
        FindCommand findSecondAndThirdCommand = new FindCommand(secondAndThirdPredicates);
        FindCommand findThirdAndFourthCommand = new FindCommand(thirdAndFourthPredicates);
        FindCommand findFourthAndFifthCommand = new FindCommand(fourthAndFifthPredicates);
        FindCommand findFifthAndSixthCommand = new FindCommand(fifthAndSixthPredicates);

        // same object -> returns true
        assertTrue(findAllCommand.equals(findAllCommand));
        assertTrue(findFirstAndSecondCommand.equals(findFirstAndSecondCommand));
        assertTrue(findSecondAndThirdCommand.equals(findSecondAndThirdCommand));
        assertTrue(findThirdAndFourthCommand.equals(findThirdAndFourthCommand));
        assertTrue(findFifthAndSixthCommand.equals(findFifthAndSixthCommand));

        // same values -> returns true
        FindCommand findAllCommandCopy = new FindCommand(allPredicates);
        assertTrue(findAllCommand.equals(findAllCommandCopy));

        FindCommand findFirstAndSecondCommandCopy = new FindCommand(firstAndSecondPredicates);
        assertTrue(findFirstAndSecondCommand.equals(findFirstAndSecondCommandCopy));

        FindCommand findSecondAndThirdCommandCopy = new FindCommand(secondAndThirdPredicates);
        assertTrue(findSecondAndThirdCommand.equals(findSecondAndThirdCommandCopy));

        FindCommand findFifthAndSixthCommandCopy = new FindCommand(fifthAndSixthPredicates);
        assertTrue(findFifthAndSixthCommandCopy.equals(findFifthAndSixthCommandCopy));

        // different types -> returns false
        assertFalse(findAllCommand.equals(1));
        assertFalse(findFirstAndSecondCommandCopy.equals(3));

        // null -> returns false
        assertFalse(findAllCommand.equals(null));
        assertFalse(findFourthAndFifthCommand.equals(null));

        // different keywords -> returns false
        assertFalse(findFirstAndSecondCommandCopy.equals(findAllCommand));
        assertFalse(findSecondAndThirdCommandCopy.equals(findFirstAndSecondCommandCopy));
        assertFalse(findFourthAndFifthCommand.equals(findThirdAndFourthCommand));
        assertFalse(findFifthAndSixthCommand.equals(findThirdAndFourthCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNamePredicate(" "));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_findName_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNamePredicate("Kurz Elle Kunz"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_findName_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNamePredicate("Yewjon"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_findPhone_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(preparePhonePredicate("9482427 87652533 98765432"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_findPhone_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(preparePhonePredicate("999"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNamePredicate("Kurz Elle Kunz"));
        predicate.add(prepareRiskPredicate("MEDIUM"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroRiskTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareRiskPredicate(" "));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneRiskTags_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareRiskPredicate("HIGH"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroNormalTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNormalPredicate(" "));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNormalTags_threePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNormalPredicate("friends"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroPlanTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(preparePlanPredicate(" "));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }


    @Test
    public void execute_multiplePrefixesAndMultipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNamePredicate("Kurz Elle Kunz"));
        predicate.add(prepareRiskPredicate("MEDIUM"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());

        //resets the FilteredPersonList
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new CommandHistory());

        String secondExpectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> secondPredicate = new ArrayList<>();
        secondPredicate.add(prepareNamePredicate("Meier"));
        secondPredicate.add(prepareNormalPredicate("friends"));
        FindCommand secondCommand = new FindCommand(secondPredicate);
        expectedModel.updateFilteredPersonList(secondPredicate);
        assertCommandSuccess(secondCommand, model, secondExpectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());

        //resets the FilteredPersonList
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new CommandHistory());

        String thirdExpectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        ArrayList<FindPredicate> thirdPredicate = new ArrayList<>();
        thirdPredicate.add(prepareNamePredicate("Meier"));
        thirdPredicate.add(prepareIncomePredicate("1500"));
        FindCommand thirdCommand = new FindCommand(thirdPredicate);
        expectedModel.updateFilteredPersonList(thirdPredicate);
        assertCommandSuccess(thirdCommand, model, thirdExpectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_subsequentFindCommand_displaysPersonsBasedOnCurrentList() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<FindPredicate> predicate = new ArrayList<>();
        predicate.add(prepareNamePredicate("Kurz Elle Kunz"));
        predicate.add(prepareRiskPredicate("MEDIUM"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());

        //Alice, Carl, Daniel have "LOW" RISK_APPETITE
        //However, filteredPersons consist of Carl, Elle, Fiona hence only Carl will be displayed
        String secondExpectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<FindPredicate> secondPredicate = new ArrayList<>();
        secondPredicate.add(prepareRiskPredicate("LOW"));
        FindCommand secondCommand = new FindCommand(secondPredicate);
        expectedModel.updateFilteredPersonList(secondPredicate);
        assertCommandSuccess(secondCommand, model, secondExpectedMessage, expectedModel);
        assertNotEquals(Arrays.asList(ALICE, CARL, DANIEL), model.getFilteredPersonList());
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RiskTagContainsKeywordsPredicate}.
     */
    private RiskTagContainsKeywordsPredicate prepareRiskPredicate(String userInput) {
        return new RiskTagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NormalTagContainsKeywordsPredicate}.
     */
    private NormalTagContainsKeywordsPredicate prepareNormalPredicate(String userInput) {
        return new NormalTagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PlanTagContainsKeywordsPredicate}.
     */
    private PlanTagContainsKeywordsPredicate preparePlanPredicate(String userInput) {
        return new PlanTagContainsKeywordsPredicate(Arrays.asList(userInput.split("(?<=\\s\\S{1,100})\\s")));
    }

    /**
     * Parses {@code userInput} into a {@code PlanTagContainsKeywordsPredicate}.
     */
    private IncomeContainsKeywordsPredicate prepareIncomePredicate(String userInput) {
        return new IncomeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")), ">");
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate }.
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ClientTagContainsKeywordsPredicate }.
     */
    private ClientTagContainsKeywordsPredicate prepareClientTagPredicate(String userInput) {
        return new ClientTagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
