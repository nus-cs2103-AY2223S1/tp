package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_INDEX_NUMBERS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.watson.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.watson.commons.core.index.Index;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Database;
import seedu.watson.model.Model;
import seedu.watson.model.student.FindCommandPredicate;
import seedu.watson.model.student.Student;
import seedu.watson.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_INDEX_NUMBER_AMY = "21";
    public static final String VALID_INDEX_NUMBER_BOB = "17";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_SUBJECT_ENGLISH = "english";
    public static final String INVALID_TAG = "#####";

    // New fields
    public static final String VALID_STUDENTCLASS = "1A";
    public static final String VALID_SUBJECTHANDLER = "english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                      + "CA2:[30.0, 56.0, 0.4, 2.0]";

    // StudentClass examples
    public static final String STUDENTCLASS_DUMMY = " " + PREFIX_STUDENTCLASS + VALID_STUDENTCLASS;

    // Remark examples
    public static final String VALID_REMARK_ICE_CREAM = "likes ice cream";
    public static final String VALID_REMARK_COFFEE = "likes coffee";
    public static final String REMARK_ICE_CREAM = " " + PREFIX_REMARK + VALID_REMARK_ICE_CREAM;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String INDEX_NUMBER_DESC_AMY = " " + PREFIX_INDEX_NUMBERS + VALID_INDEX_NUMBER_AMY;
    public static final String INDEX_NUMBER_DESC_BOB = " " + PREFIX_INDEX_NUMBERS + VALID_INDEX_NUMBER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " "
        + PREFIX_TAG + INVALID_TAG; // tag cannot contain special characters

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_ASC_ARGS = " ASC";
    public static final String INVALID_ASC_ARGS = " AEC";
    public static final String SUBJECT_ARG = " " + PREFIX_SUBJECT + VALID_SUBJECT_ENGLISH;

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                                                    .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                                                    .withAddress(VALID_ADDRESS_AMY)
                                                    .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                                                    .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                                                    .withAddress(VALID_ADDRESS_BOB)
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
     * - the watson book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Database expectedDatabase = new Database(actualModel.getDatabase());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDatabase, actualModel.getDatabase());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s watson book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Student student = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");

        List<String> predicateKeywordList = new ArrayList<>();
        Collections.addAll(predicateKeywordList, splitName[0], "", "");
        List<String> unmodifiablePredicateKeywordList = Collections.unmodifiableList(predicateKeywordList);
        model.updateFilteredPersonList(new FindCommandPredicate(unmodifiablePredicateKeywordList));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
