package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVEMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.XAVIER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_ZAC = "Zac Lim";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_ZAC = "33333333";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_ZAC = "zac@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_ZAC = "Block 231, Zac Street 2";
    public static final String VALID_GITHUB_AMY = "Amy-bee";
    public static final String VALID_GITHUB_BOB = "BobChoo";
    public static final String VALID_GITHUB_ZAC = "ZacL1m";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_MODULE_1 = "CS2103T";
    public static final String VALID_MODULE_2 = "CS2101";
    public static final String VALID_MODULE_3 = "CS2040S";
    public static final String VALID_MODULE_4 = "CS2030S";
    public static final String VALID_MODULE_5 = "CS2109S";
    public static final String VALID_MODULE_6 = "CS3230";
    public static final String VALID_MODULE_7 = "CS2100";
    public static final String VALID_MODULE_8 = "ST2334";
    public static final String VALID_MODULE_9 = "CS1101S";
    public static final String VALID_MODULE_10 = "CS1231S";
    public static final String VALID_MODULE_11 = "CS2106";
    public static final String VALID_MODULE_12 = "CS3213";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_ZAC = " " + PREFIX_NAME + VALID_NAME_ZAC;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_ZAC = " " + PREFIX_PHONE + VALID_PHONE_ZAC;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_ZAC = " " + PREFIX_EMAIL + VALID_EMAIL_ZAC;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_ZAC = " " + PREFIX_ADDRESS + VALID_ADDRESS_ZAC;
    public static final String GITHUB_DESC_AMY = " " + PREFIX_GITHUB + VALID_GITHUB_AMY;
    public static final String GITHUB_DESC_BOB = " " + PREFIX_GITHUB + VALID_GITHUB_BOB;
    public static final String GITHUB_DESC_ZAC = " " + PREFIX_GITHUB + VALID_GITHUB_ZAC;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String CURRENT_MODULE_DESC_1 = " " + PREFIX_CURRENTMOD + VALID_MODULE_1;
    public static final String CURRENT_MODULE_DESC_2 = " " + PREFIX_CURRENTMOD + VALID_MODULE_2;
    public static final String PREVIOUS_MODULE_DESC_1 = " " + PREFIX_PREVIOUSMOD + VALID_MODULE_3;
    public static final String PREVIOUS_MODULE_DESC_2 = " " + PREFIX_PREVIOUSMOD + VALID_MODULE_4;
    public static final String PLANNED_MODULE_DESC_1 = " " + PREFIX_PLANNEDMOD + VALID_MODULE_5;
    public static final String PLANNED_MODULE_DESC_2 = " " + PREFIX_PLANNEDMOD + VALID_MODULE_6;
    public static final String MODULE_TO_REMOVE_DESC_1 = " " + PREFIX_REMOVEMOD + VALID_MODULE_7;
    public static final String MODULE_TO_REMOVE_DESC_2 = " " + PREFIX_REMOVEMOD + VALID_MODULE_8;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GITHUB_DESC = " " + PREFIX_GITHUB + "jon_doe"; // underscore not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_CURRENT_MODULE_DESC = " " + PREFIX_CURRENTMOD + "CS2103T*"; // * not allowed
    public static final String INVALID_PLANNED_MODULE_DESC = " " + PREFIX_PLANNEDMOD + "CS2103T-"; // - not allowed
    public static final String INVALID_PREVIOUS_MODULE_DESC = " " + PREFIX_PREVIOUSMOD + "CS2103T^"; // ^ not allowed
    public static final String INVALID_MODULE_TO_REMOVE_DESC = " " + PREFIX_REMOVEMOD + "GEA+1000"; // + not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditCommand.EditPersonDescriptor DESC_ZAC;
    public static final EditCommand.EditPersonDescriptor DESC_XAVIER;
    public static final ModuleCommand.EditModuleDescriptor MODULE_DESC_AMY;
    public static final ModuleCommand.EditModuleDescriptor MODULE_DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withGithub(VALID_GITHUB_AMY).withTags(VALID_TAG_FRIEND)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withGithub(VALID_GITHUB_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_ZAC = new EditPersonDescriptorBuilder().withName(VALID_NAME_ZAC)
                .withPhone(VALID_PHONE_ZAC).withEmail(VALID_EMAIL_ZAC).withAddress(VALID_ADDRESS_ZAC)
                .withGithub(VALID_GITHUB_ZAC).build();
        DESC_XAVIER = new EditPersonDescriptorBuilder(XAVIER).build();
        MODULE_DESC_AMY = new EditModuleDescriptorBuilder().withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPreviousModules(VALID_MODULE_3, VALID_MODULE_4)
                .withPlannedModules(VALID_MODULE_5, VALID_MODULE_6)
                .withModToRemove(VALID_MODULE_7, VALID_MODULE_8)
                .build();
        MODULE_DESC_BOB = new EditModuleDescriptorBuilder().withCurrentModules(VALID_MODULE_7, VALID_MODULE_8)
                .withPreviousModules(VALID_MODULE_9, VALID_MODULE_10)
                .withPlannedModules(VALID_MODULE_11, VALID_MODULE_12)
                .withModToRemove(VALID_MODULE_1, VALID_MODULE_2)
                .build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
