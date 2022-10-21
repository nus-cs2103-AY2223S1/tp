package seedu.classify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.classify.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.classify.commons.core.index.Index;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.model.Model;
import seedu.classify.model.StudentRecord;
import seedu.classify.model.student.NameContainsKeywordsPredicate;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_STUDENT_NAME_AMY = "Amy Bee";
    public static final String VALID_STUDENT_NAME_BOB = "Bob Choo";
    public static final String VALID_PARENT_NAME_AMY = "Jenny Bee";
    public static final String VALID_PARENT_NAME_BOB = "Eric Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_ID_AMY = "498C";
    public static final String VALID_ID_BOB = "883A";
    public static final String VALID_CLASS_AMY = "20A68";
    public static final String VALID_CLASS_BOB = "20A70";
    public static final String VALID_EMAIL_AMY = "amy@gmail.com";
    public static final String VALID_EMAIL_BOB = "bob@gmail.com";
    public static final String VALID_EXAM_1 = "CA1 50";
    public static final String VALID_EXAM_2 = "SA2 80";

    public static final String STUDENT_NAME_DESC_AMY = " " + PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_AMY;
    public static final String STUDENT_NAME_DESC_BOB = " " + PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_BOB;
    public static final String PARENT_NAME_DESC_AMY = " " + PREFIX_PARENT_NAME + VALID_PARENT_NAME_AMY;
    public static final String PARENT_NAME_DESC_BOB = " " + PREFIX_PARENT_NAME + VALID_PARENT_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String CLASS_DESC_AMY = " " + PREFIX_CLASS + VALID_CLASS_AMY;
    public static final String CLASS_DESC_BOB = " " + PREFIX_CLASS + VALID_CLASS_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_EXAM_1 = " " + PREFIX_EXAM + VALID_EXAM_1;
    public static final String TAG_DESC_EXAM_2 = " " + PREFIX_EXAM + VALID_EXAM_2;

    public static final String INVALID_STUDENT_NAME_DESC = " " + PREFIX_STUDENT_NAME + "A&"; // '&' not allowed in names
    public static final String INVALID_PARENT_NAME_DESC = " " + PREFIX_PARENT_NAME + "JC!"; // '!' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_ID_DESC = " " + PREFIX_ID + "461"; // missing last character
    public static final String INVALID_CLASS_DESC = " " + PREFIX_CLASS; // empty string not allowed in class names
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL
            + "test@@gmail.com"; // only a single @ is allowed for emails
    public static final String INVALID_EXAM_DESC = " " + PREFIX_EXAM + "CA2 1*0"; // '*' not allowed in exam score

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY)
                .withId(VALID_ID_AMY)
                .withClassName(VALID_CLASS_AMY)
                .withParentName(VALID_PARENT_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withExams(VALID_EXAM_2).build();
        DESC_BOB = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .withId(VALID_ID_BOB)
                .withParentName(VALID_PARENT_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withExams(VALID_EXAM_1, VALID_EXAM_2).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel.getFilteredStudentList(), actualModel.getFilteredStudentList());
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the student record, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));

        StudentRecord expectedStudentRecord = new StudentRecord(actualModel.getStudentRecord());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertEquals(expectedStudentRecord, actualModel.getStudentRecord());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getStudentName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
