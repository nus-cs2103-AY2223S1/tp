package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_EMAIL;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_TAG;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.NameContainsKeywordsPredicate;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_STUDENT_ID_AMY = "A1111111M";
    public static final String VALID_STUDENT_ID_BOB = "A2222222O";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_STATUS_AMY = "IP";
    public static final String VALID_STATUS_BOB = "YTS";
    public static final String VALID_PROJECT_NAME_AMY = "random CS proj 1";
    public static final String VALID_PROJECT_NAME_BOB = "random CS proj 2";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String PROJECT_NAME_DESC_AMY = " " + PREFIX_PROJECT_NAME + VALID_PROJECT_NAME_AMY;
    public static final String PROJECT_NAME_DESC_BOB = " " + PREFIX_PROJECT_NAME + VALID_PROJECT_NAME_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID + "a"; // 'a' invalidates student ID
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    // empty str not allowed for projects
    public static final String INVALID_PROJECT_NAME_DESC = " " + PREFIX_PROJECT_NAME;
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final StudentId STUDENT_ID = new StudentId("A0123456G");

    public static final StudentId INVALID_STUDENT_ID = new StudentId("BCDEDIT");

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).withProjectName(VALID_PROJECT_NAME_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withProjectName(VALID_PROJECT_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the FYP manager, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FypManager expectedFypManager = new FypManager(actualModel.getFypManager());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFypManager, actualModel.getFypManager());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s FYP manager.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
