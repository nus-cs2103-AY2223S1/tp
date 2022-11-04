package seedu.rc4hdb.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.predicates.NameContainsKeywordsPredicate;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;

/**
 * Contains helper methods for testing model commands.
 */
public class ModelCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ROOM_AMY = "01-02";
    public static final String VALID_ROOM_BOB = "09-10";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_HOUSE_AMY = "A";
    public static final String VALID_HOUSE_BOB = "U";
    public static final String VALID_MATRIC_NUMBER_AMY = "A0123456U";
    public static final String VALID_MATRIC_NUMBER_BOB = "A0000000U";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ROOM_DESC_AMY = " " + PREFIX_ROOM + VALID_ROOM_AMY;
    public static final String ROOM_DESC_BOB = " " + PREFIX_ROOM + VALID_ROOM_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String HOUSE_DESC_AMY = " " + PREFIX_HOUSE + VALID_HOUSE_AMY;
    public static final String HOUSE_DESC_BOB = " " + PREFIX_HOUSE + VALID_HOUSE_BOB;
    public static final String MATRIC_NUMBER_DESC_AMY = " " + PREFIX_MATRIC_NUMBER + VALID_MATRIC_NUMBER_AMY;
    public static final String MATRIC_NUMBER_DESC_BOB = " " + PREFIX_MATRIC_NUMBER + VALID_MATRIC_NUMBER_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ROOM_DESC =
            " " + PREFIX_ROOM + "-1-00"; // negative integers not allowed in rooms
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "A"; //'A' not allowed in gender
    public static final String INVALID_HOUSE_DESC = " " + PREFIX_HOUSE + "2"; //'2' not allowed in house
    public static final String INVALID_MATRIC_NUMBER_DESC =
            " " + PREFIX_MATRIC_NUMBER + "b0000000c"; //'a' not allowed in matric numbers
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_ANY_SPECIFIER_DESC = " " + "/any";
    public static final String VALID_ALL_SPECIFIER_DESC = " " + "/all";

    public static final ResidentDescriptor DESC_AMY;
    public static final ResidentDescriptor DESC_BOB;

    static {
        DESC_AMY = new ResidentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withRoom(VALID_ROOM_AMY)
                .withGender(VALID_GENDER_AMY).withHouse(VALID_HOUSE_AMY).withMatricNumber(VALID_MATRIC_NUMBER_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new ResidentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withRoom(VALID_ROOM_BOB)
                .withGender(VALID_GENDER_BOB).withHouse(VALID_HOUSE_BOB).withMatricNumber(VALID_MATRIC_NUMBER_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(ModelCommand command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(ModelCommand, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(ModelCommand command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the resident book, filtered resident list and selected resident in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(ModelCommand command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ResidentBook expectedResidentBook = new ResidentBook(actualModel.getResidentBook());
        List<Resident> expectedFilteredList = new ArrayList<>(actualModel.getFilteredResidentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedResidentBook, actualModel.getResidentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredResidentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the resident at the given {@code targetIndex} in the
     * {@code model}'s resident book.
     */
    public static void showResidentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredResidentList().size());

        Resident resident = model.getFilteredResidentList().get(targetIndex.getZeroBased());
        final String[] splitName = resident.getName().value.split("\\s+");
        model.updateFilteredResidentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredResidentList().size());
    }

}
