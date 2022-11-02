package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.ui.Ui;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_MOBILE_AMY = "11111111";
    public static final String VALID_MOBILE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DEADLINE = "2022-03-05";
    public static final String VALID_REPOSITORY = "Amy/Tp";
    public static final String VALID_PROJECT_ID = "1";
    public static final String VALID_EMPTY_PROJECT_ID = "2147483647";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MOBILE_DESC_AMY = " " + PREFIX_MOBILE + VALID_MOBILE_AMY;
    public static final String MOBILE_DESC_BOB = " " + PREFIX_MOBILE + VALID_MOBILE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String DEADLINE_DESC_DEADLINE = " " + PREFIX_DEADLINE + VALID_DEADLINE;
    public static final String REPOSITORY_DESC_REPOSITORY = " " + PREFIX_REPOSITORY + VALID_REPOSITORY;
    public static final String PROJECT_DESC_PROJECT = " " + PREFIX_PROJECT_ID + VALID_PROJECT_ID;
    public static final String PROJECT_DESC_EMPTY_PROJECT = " " + PREFIX_PROJECT_ID + VALID_EMPTY_PROJECT_ID;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_MOBILE_DESC = " " + PREFIX_MOBILE + "911a"; // 'a' not allowed in mobiles
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "20 October 2022"; // yyyy-mm-dd format
    public static final String INVALID_REPOSITORY_DESC = " " + PREFIX_REPOSITORY + "Repo"; // username/repository format
    public static final String INVALID_PROJECT_ID_DESC = " " + PREFIX_PROJECT_ID + "-3";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel, Ui stubUi) {
        try {
            CommandResult result = command.execute(actualModel, stubUi);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model, Ui)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel, Ui stubUi) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel, stubUi);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the project book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage, Ui stubUi) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, stubUi));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }
}
