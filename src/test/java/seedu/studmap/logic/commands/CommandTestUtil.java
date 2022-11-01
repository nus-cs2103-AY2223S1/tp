package seedu.studmap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_GIT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.studmap.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.studmap.commons.core.index.Index;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.student.NameContainsKeywordsPredicate;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHARLIE = "Charlie Cow";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CHARLIE = "33333333";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CHARLIE = "charlie@example.com";
    public static final String VALID_MODULE_AMY = "CS2103T";
    public static final String VALID_MODULE_BOB = "CS2106";
    public static final String VALID_MODULE_CHARLIE = "CS3230";
    public static final String VALID_ID_AMY = "E1234569";
    public static final String VALID_ID_BOB = "E1234560";
    public static final String VALID_ID_CHARLIE = "E3141592";
    public static final String VALID_GIT_AMY = "user10";
    public static final String VALID_GIT_BOB = "user11";
    public static final String VALID_GIT_CHARLIE = "user12";
    public static final String VALID_HANDLE_AMY = "@user10";
    public static final String VALID_HANDLE_BOB = "@user11";
    public static final String VALID_HANDLE_CHARLIE = "@user12";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_STUDENT = "student";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CHARLIE = " " + PREFIX_NAME + VALID_NAME_CHARLIE;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_AMY;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_BOB;
    public static final String MODULE_DESC_CHARLIE = " " + PREFIX_MODULE + VALID_MODULE_CHARLIE;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String ID_DESC_CHARLIE = " " + PREFIX_ID + VALID_ID_CHARLIE;
    public static final String GIT_DESC_AMY = " " + PREFIX_GIT + VALID_GIT_AMY;
    public static final String GIT_DESC_BOB = " " + PREFIX_GIT + VALID_GIT_BOB;
    public static final String HANDLE_DESC_AMY = " " + PREFIX_HANDLE + VALID_HANDLE_AMY;
    public static final String HANDLE_DESC_BOB = " " + PREFIX_HANDLE + VALID_HANDLE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME = "James&"; // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + INVALID_NAME;
    public static final String INVALID_PHONE = "911a"; // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + INVALID_PHONE;
    public static final String INVALID_EMAIL = "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + INVALID_EMAIL;
    public static final String INVALID_TAG = "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + INVALID_TAG;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCommandStudentEditor DESC_AMY;
    public static final EditCommand.EditCommandStudentEditor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY)
                .withId(VALID_ID_AMY).withGitName(VALID_GIT_AMY)
                .withTeleHandle(VALID_HANDLE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB)
                .withId(VALID_ID_BOB).withGitName(VALID_GIT_BOB)
                .withTeleHandle(VALID_HANDLE_BOB)
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
     * - the student map, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudMap expectedStudMap = new StudMap(actualModel.getStudMap());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudMap, actualModel.getStudMap());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s student map.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
