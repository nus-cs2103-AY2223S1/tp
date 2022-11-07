package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_MATRIC_NUM;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Model;
import jarvis.model.NameContainsKeywordsPredicate;
import jarvis.model.Student;
import jarvis.model.StudentBook;
import jarvis.model.Task;
import jarvis.model.TaskBook;
import jarvis.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_MATRIC_NUM_AMY = "A0344534D";
    public static final String VALID_MATRIC_NUM_BOB = "A3533843G";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MATRIC_NUM_DESC_AMY = " " + PREFIX_MATRIC_NUM + VALID_MATRIC_NUM_AMY;
    public static final String MATRIC_NUM_DESC_BOB = " " + PREFIX_MATRIC_NUM + VALID_MATRIC_NUM_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_MATRIC_NUM_START_ALPHABET = " " + PREFIX_MATRIC_NUM + "B1234567A";
    public static final String INVALID_MATRIC_NUM_SIX_DIGITS = " " + PREFIX_MATRIC_NUM + "A123456U";
    public static final String INVALID_MATRIC_NUM_UNCAPITALISED = " " + PREFIX_MATRIC_NUM + "a1234567U";

    public static final String VALID_TASK_DESC_MISSION1 = "Mark mission 1";
    public static final String VALID_TASK_DESC_MISSION2 = "Mark mission 2";
    public static final LocalDate VALID_TASK_DEADLINE = LocalDate.of(2022, 10, 20);

    public static final String VALID_CONSULT_DESC = "Consult on recursion";
    public static final String VALID_MASTERY_CHECK_DESC = "Mastery Check 1";
    public static final String VALID_STUDIO_DESC = "Studio 1";
    public static final String VALID_START_DATE = "2022-12-12";
    public static final String VALID_START_TIME = "10:00";
    public static final String VALID_END_TIME = "12:00";

    public static final String LESSON_DESC_CONSULT = " " + PREFIX_LESSON + VALID_CONSULT_DESC;
    public static final String LESSON_DESC_MASTERY_CHECK = " " + PREFIX_LESSON + VALID_MASTERY_CHECK_DESC;
    public static final String LESSON_DESC_STUDIO = " " + PREFIX_LESSON + VALID_STUDIO_DESC;
    public static final String LESSON_START_DATE = " " + PREFIX_START_DATE + VALID_START_DATE;
    public static final String LESSON_START_TIME = " " + PREFIX_START_TIME + VALID_START_TIME;
    public static final String LESSON_END_DATE = " " + PREFIX_END_DATE + VALID_START_DATE;
    public static final String LESSON_END_TIME = " " + PREFIX_END_TIME + VALID_END_TIME;
    public static final String LESSON_STUDENT_INDEX = " " + PREFIX_STUDENT_INDEX + "1";

    public static final String INVALID_LESSON_DESC = " " + PREFIX_LESSON + " ";
    public static final String INVALID_LESSON_START_DATE = " " + PREFIX_START_DATE + "08-08-2022";
    public static final String INVALID_LESSON_START_TIME = " " + PREFIX_START_TIME + "12pm";
    public static final String INVALID_LESSON_END_DATE = " " + PREFIX_END_DATE + "10";
    public static final String INVALID_LESSON_END_TIME = " " + PREFIX_END_TIME + "25:00";
    public static final String INVALID_LESSON_STUDENT_INDEX = " " + PREFIX_STUDENT_INDEX + "-1";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withMatricNum(VALID_MATRIC_NUM_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withMatricNum(VALID_MATRIC_NUM_BOB).build();
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
     * - the student book and filtered student list in {@code actualModel} remain unchanged
     * - the task book and filtered task list in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudentBook expectedStudentBook = new StudentBook(actualModel.getStudentBook());
        TaskBook expectedTaskBook = new TaskBook(actualModel.getTaskBook());
        List<Student> expectedFilteredStudentList = new ArrayList<>(actualModel.getFilteredStudentList());
        List<Task> expectedFilteredTaskList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudentBook, actualModel.getStudentBook());
        assertEquals(expectedTaskBook, actualModel.getTaskBook());
        assertEquals(expectedFilteredStudentList, actualModel.getFilteredStudentList());
        assertEquals(expectedFilteredTaskList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered student list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s student book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }
}
