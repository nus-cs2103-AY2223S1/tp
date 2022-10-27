package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StudentContainsKeywordsPredicate;
import seedu.address.model.TuitionClassContainsKeywordsPredicate;
import seedu.address.model.TutorContainsKeywordsPredicate;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model studentModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
    private Model tutorModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
    private Model tuitionClassModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
    private Model expectedStudentModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
    private Model expectedTutorModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
    private Model expectedTuitionClassModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());

    @Test
    public void equalsForStudentListType() {
        HashMap<Prefix, String> firstKeywords = new HashMap<>();
        HashMap<Prefix, String> secondKeywords = new HashMap<>();
        firstKeywords.put(PREFIX_NAME, "first");
        secondKeywords.put(PREFIX_NAME, "second");

        FindCommand findFirstCommand = new FindCommand(firstKeywords);
        FindCommand findSecondCommand = new FindCommand(secondKeywords);

        // unexecuted FindCommand -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // FindCommand test for Student List
        studentModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        findFirstCommand.execute(studentModel);

        // second command studentPredicate not initialized -> return false;
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // Initialize the second command
        findSecondCommand.execute(studentModel);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstKeywords);
        findFirstCommandCopy.execute(studentModel);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void equalsForTutorListType() {
        HashMap<Prefix, String> firstKeywords = new HashMap<>();
        HashMap<Prefix, String> secondKeywords = new HashMap<>();
        firstKeywords.put(PREFIX_NAME, "first");
        secondKeywords.put(PREFIX_NAME, "second");

        FindCommand findFirstCommand = new FindCommand(firstKeywords);
        FindCommand findSecondCommand = new FindCommand(secondKeywords);

        // unexecuted FindCommand -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // FindCommand test for Student List
        tutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        findFirstCommand.execute(tutorModel);

        // second command tutorPredicate not initialized -> return false;
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // Initialize the second command
        findSecondCommand.execute(tutorModel);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstKeywords);
        findFirstCommandCopy.execute(tutorModel);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void equalsForTuitionClassListType() {
        HashMap<Prefix, String> firstKeywords = new HashMap<>();
        HashMap<Prefix, String> secondKeywords = new HashMap<>();
        firstKeywords.put(PREFIX_NAME, "first");
        secondKeywords.put(PREFIX_NAME, "second");

        FindCommand findFirstCommand = new FindCommand(firstKeywords);
        FindCommand findSecondCommand = new FindCommand(secondKeywords);

        // unexecuted FindCommand -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // FindCommand test for Student List
        tuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        findFirstCommand.execute(tuitionClassModel);

        // second command tuitionClassPredicate not initialized -> return false;
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // Initialize the second command
        findSecondCommand.execute(tuitionClassModel);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstKeywords);
        findFirstCommandCopy.execute(tuitionClassModel);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_validStudentNameKeyword_success() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "Alice");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        StudentContainsKeywordsPredicate<Student> predicate = new StudentContainsKeywordsPredicate<>(keywords);
        FindCommand command = new FindCommand(keywords);
        expectedStudentModel.updateFilteredStudentList(predicate);
        studentModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedStudentModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        assertCommandSuccess(command, studentModel, expectedMessage, expectedStudentModel);
    }

    @Test
    public void execute_validTutorNameKeyword_success() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 1);
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "Alice");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");
        TutorContainsKeywordsPredicate<Tutor> predicate = new TutorContainsKeywordsPredicate<>(keywords);
        FindCommand command = new FindCommand(keywords);
        expectedTutorModel.updateFilteredTutorList(predicate);
        tutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedTutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        assertCommandSuccess(command, tutorModel, expectedMessage, expectedTutorModel);
    }

    @Test
    public void execute_validTuitionClassNameKeyword_success() {
        String expectedMessage = String.format(MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW, 1);
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "P2Math");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "");
        TuitionClassContainsKeywordsPredicate<TuitionClass> predicate =
                new TuitionClassContainsKeywordsPredicate<>(keywords);
        FindCommand command = new FindCommand(keywords);
        expectedTuitionClassModel.updateFilteredTuitionClassList(predicate);
        tuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedTuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        assertCommandSuccess(command, tuitionClassModel, expectedMessage, expectedTuitionClassModel);
    }
}
