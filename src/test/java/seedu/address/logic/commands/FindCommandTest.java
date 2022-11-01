package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.getTypicalTeachersPet;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.student.predicate.ClassContainsDatePredicate;
import seedu.address.model.student.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.student.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicate.NokPhoneContainsNumberPredicate;
import seedu.address.model.student.predicate.PhoneContainsNumberPredicate;
import seedu.address.model.student.predicate.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        AddressContainsKeywordsPredicate addressFirstPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("121 Baker Street #100-@-99"));
        AddressContainsKeywordsPredicate addressSecondPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("(00 - 12379623) Prinsep :1 Lane"));
        EmailContainsKeywordsPredicate emailFirstPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("fong_teng@yahoo.com"));
        EmailContainsKeywordsPredicate emailSecondPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("wongtf@gmail.com"));
        ClassContainsDatePredicate classOnePredicate =
                new ClassContainsDatePredicate("2022-10-10");
        ClassContainsDatePredicate classTwoPredicate =
                new ClassContainsDatePredicate("2022-10-11");
        PhoneContainsNumberPredicate phoneOnePredicate =
                new PhoneContainsNumberPredicate("94351253");
        PhoneContainsNumberPredicate phoneTwoPredicate =
                new PhoneContainsNumberPredicate("98765432");

        TagContainsKeywordsPredicate tagOnePredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("python"));
        TagContainsKeywordsPredicate tagTwoPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("java"));

        NokPhoneContainsNumberPredicate nokPhoneOnePredicate =
                new NokPhoneContainsNumberPredicate("94351253");
        NokPhoneContainsNumberPredicate nokPhoneTwoPredicate =
                new NokPhoneContainsNumberPredicate("98765432");

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findAddressFirstCommand = new FindCommand(addressFirstPredicate);
        FindCommand findAddressSecondCommand = new FindCommand(addressSecondPredicate);
        FindCommand findClassOneCommand = new FindCommand(classOnePredicate);
        FindCommand findClassTwoCommand = new FindCommand(classTwoPredicate);
        FindCommand findEmailFirstCommand = new FindCommand(emailFirstPredicate);
        FindCommand findEmailSecondCommand = new FindCommand(emailSecondPredicate);
        FindCommand findPhoneOneCommand = new FindCommand(phoneOnePredicate);
        FindCommand findPhoneTwoCommand = new FindCommand(phoneTwoPredicate);
        FindCommand findTagOneCommand = new FindCommand(tagOnePredicate);
        FindCommand findTagTwoCommand = new FindCommand(tagTwoPredicate);
        FindCommand findNokPhoneOneCommand = new FindCommand(nokPhoneOnePredicate);
        FindCommand findNokPhoneTwoCommand = new FindCommand(nokPhoneTwoPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findAddressFirstCommand.equals(findAddressFirstCommand));
        assertTrue(findClassOneCommand.equals(findClassOneCommand));
        assertTrue(findEmailFirstCommand.equals(findEmailFirstCommand));
        assertTrue(findPhoneOneCommand.equals(findPhoneOneCommand));
        assertTrue(findTagOneCommand.equals(findTagOneCommand));
        assertTrue(findNokPhoneOneCommand.equals(findNokPhoneOneCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        FindCommand findAddressFirstCommandCopy = new FindCommand(addressFirstPredicate);
        assertTrue(findAddressFirstCommand.equals(findAddressFirstCommandCopy));
        FindCommand findClassOneCommandCopy = new FindCommand(classOnePredicate);
        assertTrue(findClassOneCommand.equals(findClassOneCommandCopy));
        FindCommand findEmailFirstCommandCopy = new FindCommand(emailFirstPredicate);
        assertTrue(findEmailFirstCommand.equals(findEmailFirstCommandCopy));
        FindCommand findPhoneOneCommandCopy = new FindCommand(phoneOnePredicate);
        assertTrue(findPhoneOneCommand.equals(findPhoneOneCommandCopy));
        FindCommand findTagOneCommandCopy = new FindCommand(tagOnePredicate);
        assertTrue(findTagOneCommand.equals(findTagOneCommandCopy));
        FindCommand findNokPhoneOneCommandCopy = new FindCommand(nokPhoneOnePredicate);
        assertTrue(findNokPhoneOneCommand.equals(findNokPhoneOneCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findAddressFirstCommand.equals(2));
        assertFalse(findSecondCommand.equals(1));
        assertFalse(findTagOneCommand.equals(1));
        assertFalse(findTagTwoCommand.equals(1));
        assertFalse(findEmailFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findAddressFirstCommand.equals(null));
        assertFalse(findClassOneCommand.equals(null));
        assertFalse(findEmailFirstCommand.equals(null));
        assertFalse(findPhoneOneCommand.equals(null));
        assertFalse(findTagOneCommand.equals(null));
        assertFalse(findNokPhoneOneCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // different address -> returns false
        assertFalse(findAddressFirstCommand.equals(findAddressSecondCommand));

        // different date -> returns false
        assertFalse(findClassOneCommand.equals(findClassTwoCommand));

        // different address -> returns false
        assertFalse(findEmailFirstCommand.equals(findEmailSecondCommand));

        // different phone -> returns false
        assertFalse(findPhoneOneCommand.equals(findPhoneTwoCommand));

        // different tag -> returns false
        assertFalse(findTagOneCommand.equals(findTagTwoCommand));

        // different phone -> returns false
        assertFalse(findNokPhoneOneCommand.equals(findNokPhoneTwoCommand));
    }

    @Test
    public void execute_zeroNameKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_zeroAddressKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_zeroClassKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        ClassContainsDatePredicate predicate = new ClassContainsDatePredicate("2022-01-01");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_zeroPhoneKeyword_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate("81234567");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_zeroTagKeyword_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("hello");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    public void execute_zeroNokPhoneKeyword_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NokPhoneContainsNumberPredicate predicate = new NokPhoneContainsNumberPredicate("81234566");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_zeroEmailKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
    /**
     * Parses {@code userInput} into an {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into an {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
