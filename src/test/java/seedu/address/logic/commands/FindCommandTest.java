package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT2;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.TUTOR1;
import static seedu.address.testutil.TypicalTutors.TUTOR2;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NameContainsKeywordsPredicate;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model studentModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
    private Model tutorModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
    private Model tuitionClassModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedStudentModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
    private Model expectedTutorModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
    private Model expectedTuitionClassModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        model.updateCurrentListType(Model.ListType.PERSON_LIST);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_zeroKeywords_noTutorFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_zeroKeywords_noTuitionClassFound() {
        String expectedMessage = String.format(MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTuitionClassList(predicate);
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTuitionClassList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        model.updateCurrentListType(Model.ListType.PERSON_LIST);
        expectedModel.updateCurrentListType(Model.ListType.PERSON_LIST);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Alice Benson");
        FindCommand command = new FindCommand(predicate);
        expectedStudentModel.updateFilteredStudentList(predicate);
        expectedStudentModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        studentModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        assertCommandSuccess(command, studentModel, expectedMessage, expectedStudentModel);
        assertEquals(Arrays.asList(STUDENT1, STUDENT2), studentModel.getFilteredStudentList());
    }


    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Alice Benson");
        FindCommand command = new FindCommand(predicate);
        expectedTutorModel.updateFilteredTutorList(predicate);
        expectedTutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        tutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        assertCommandSuccess(command, tutorModel, expectedMessage, expectedTutorModel);
        assertEquals(Arrays.asList(TUTOR1, TUTOR2), tutorModel.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTuitionClassesFound() {
        String expectedMessage = String.format(MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("P2MATH P5ENG");
        FindCommand command = new FindCommand(predicate);
        expectedTuitionClassModel.updateFilteredTuitionClassList(predicate);
        expectedTuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        tuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        assertCommandSuccess(command, tuitionClassModel, expectedMessage, expectedTuitionClassModel);
        assertEquals(Arrays.asList(TUITIONCLASS1, TUITIONCLASS2), tuitionClassModel.getFilteredTuitionClassList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
