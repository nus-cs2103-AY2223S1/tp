package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLinkCommand.EditLinkDescriptor;
import seedu.address.logic.commands.EditTeamCommand.EditTeamDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TruthTable;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditLinkDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTeamDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_NAME_GOOGLE = "google";
    public static final String VALID_NAME_FACEBOOK = "facebook";
    public static final String VALID_URL_GOOGLE = "https://google.com";
    public static final String VALID_URL_FACEBOOK = "https://facebook.com";

    public static final String VALID_NAME_FIRST_TEAM = "first";

    public static final String VALID_DESCRIPTION_FIRST_TEAM = "first team description";

    public static final String VALID_NAME_SECOND_TEAM = "second";
    public static final String VALID_DESCRIPTION_SECOND_TEAM = "second team description";

    public static final String VALID_TASK_NAME = "code";
    public static final String VALID_DEADLINE = "2029-12-25 23:59";


    public static final String NAME_DESC_AMY = FLAG_NAME_STR + " \"" + VALID_NAME_AMY + "\"";
    public static final String NAME_DESC_BOB = FLAG_NAME_STR + " \"" + VALID_NAME_BOB + "\"";
    public static final String PHONE_DESC_AMY = FLAG_PHONE_STR + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = FLAG_PHONE_STR + " " + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = FLAG_EMAIL_STR + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = FLAG_EMAIL_STR + " " + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = FLAG_TAG_STR + " " + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = FLAG_TAG_STR + " " + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = FLAG_NAME_STR + " James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = FLAG_PHONE_STR + " 911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = FLAG_EMAIL_STR + " bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = FLAG_TAG_STR + " hubby*"; // '*' not allowed in tags

    public static final String NON_EXISTENT_NAME = "InvalidName";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    public static final EditLinkDescriptor DESC_GOOGLE;

    public static final EditLinkDescriptor DESC_FACEBOOK;

    public static final EditTeamDescriptor DESC_FIRST_TEAM;
    public static final EditTeamDescriptor DESC_SECOND_TEAM;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();
        DESC_GOOGLE = new EditLinkDescriptorBuilder().withName(VALID_NAME_GOOGLE).withUrl(VALID_URL_GOOGLE).build();
        DESC_FACEBOOK =
                new EditLinkDescriptorBuilder().withName(VALID_NAME_FACEBOOK).withUrl(VALID_URL_FACEBOOK).build();
        DESC_FIRST_TEAM = new EditTeamDescriptorBuilder().withName("first").withDescription("first").build();
        DESC_SECOND_TEAM = new EditTeamDescriptorBuilder().withName("second").withDescription("second").build();
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
     * - the TruthTable, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TruthTable expectedTruthTable = new TruthTable(actualModel.getTruthTable());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTruthTable, actualModel.getTruthTable());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s TruthTable.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
