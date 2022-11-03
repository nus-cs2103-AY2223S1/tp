package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE_ZOOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ZOOM;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleDetailsContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonDetailsContainsKeywordsPredicate;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String CONTACT_COMMAND_IDENTIFIER = " c";
    public static final String MODULE_COMMAND_IDENTIFIER = " m";

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TELEGRAM_AMY = "@amybee";
    public static final String VALID_TELEGRAM_BOB = "@bobchoo";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_MODULE_CODE_CS2103T = "CS2103T";
    public static final String VALID_MODULE_CODE_CS2100 = "CS2100";
    public static final String VALID_MODULE_CODE_CS2040S = "CS2040S";
    public static final String VALID_LECTURE_DETAILS_CS2103T = "Every friday";
    public static final String VALID_LECTURE_DETAILS_CS2100 = "Every tuesday";
    public static final String VALID_TUTORIAL_DETAILS_CS2103T = "Every wednesday";
    public static final String VALID_TUTORIAL_DETAILS_CS2100 = "Every friday";
    public static final String VALID_LECTURE_ZOOM_LINK_CS2103T = "https://nus-sg.zoom.us/CS2103T";
    public static final String VALID_LECTURE_ZOOM_LINK_CS2100 = "https://nus-sg.zoom.us/CS2100";
    public static final String VALID_TUTORIAL_ZOOM_LINK_CS2103T = "https://nus-sg.zoom.us/CS2103T";
    public static final String VALID_TUTORIAL_ZOOM_LINK_CS2100 = "https://nus-sg.zoom.us/CS2100";
    public static final String VALID_ASSIGNMENT_DETAILS_HARD = "hard";
    public static final String VALID_ASSIGNMENT_DETAILS_NORMAL = "normal";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String MODULE_CODE_CS2103T = " " + PREFIX_MODULE + VALID_MODULE_CODE_CS2103T;
    public static final String MODULE_CODE_CS2100 = " " + PREFIX_MODULE + VALID_MODULE_CODE_CS2100;
    public static final String LECTURE_DETAILS_CS2103T = " " + PREFIX_LECTURE + VALID_LECTURE_DETAILS_CS2103T;
    public static final String LECTURE_DETAILS_CS2100 = " " + PREFIX_LECTURE + VALID_LECTURE_DETAILS_CS2100;
    public static final String TUTORIAL_DETAILS_CS2103T = " " + PREFIX_TUTORIAL + VALID_TUTORIAL_DETAILS_CS2103T;
    public static final String TUTORIAL_DETAILS_CS2100 = " " + PREFIX_TUTORIAL + VALID_TUTORIAL_DETAILS_CS2100;
    public static final String LECTURE_ZOOM_LINK_CS2103T = " " + PREFIX_LECTURE_ZOOM + VALID_LECTURE_ZOOM_LINK_CS2103T;
    public static final String LECTURE_ZOOM_LINK_CS2100 = " " + PREFIX_LECTURE_ZOOM + VALID_LECTURE_ZOOM_LINK_CS2100;
    public static final String TUTORIAL_ZOOM_LINK_CS2103T = " " + PREFIX_TUTORIAL_ZOOM
        + VALID_TUTORIAL_ZOOM_LINK_CS2103T;
    public static final String TUTORIAL_ZOOM_LINK_CS2100 = " " + PREFIX_TUTORIAL_ZOOM + VALID_TUTORIAL_ZOOM_LINK_CS2100;
    public static final String ASSIGNMENT_DETAILS_HARD = " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DETAILS_HARD;
    public static final String ASSIGNMENT_DETAILS_NORMAL = " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DETAILS_NORMAL;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // only alphanumerics allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // only alphanumerics allowed in tags

    //TODO to update invalid module details after parsing updated
    public static final String INVALID_MODULE_CODE = " " + PREFIX_MODULE + "CS2103T%"; // only alphanumerics allowed
    public static final String INVALID_LECTURE_DETAILS = " " + PREFIX_LECTURE + " "; //empty string not allowed
    public static final String INVALID_TUTORIAL_DETAILS = " " + PREFIX_TUTORIAL + " "; //empty string not allowed
    public static final String INVALID_LECTURE_ZOOM_LINK = " " + PREFIX_LECTURE_ZOOM + "zoom.com"; // TODO add reason
    public static final String INVALID_TUTORIAL_ZOOM_LINK = " " + PREFIX_TUTORIAL_ZOOM + "zoom.com"; // TODO add reason
    public static final String INVALID_ASSIGNMENT_DETAILS = " " + PREFIX_ASSIGNMENT + "hard&"; // only alphanumerics

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditModuleCommand.EditModuleDescriptor DESC_CS2103T;
    public static final EditModuleCommand.EditModuleDescriptor DESC_CS2100;


    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withModuleCode(VALID_MODULE_CODE_CS2100)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    static {
        DESC_CS2103T = new EditModuleDescriptorBuilder().withLectureDetails(VALID_LECTURE_DETAILS_CS2103T)
            .withLectureDetails(VALID_LECTURE_DETAILS_CS2103T).withLectureZoomLink(VALID_LECTURE_ZOOM_LINK_CS2103T)
            .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T).withTutorialZoomLink(VALID_TUTORIAL_ZOOM_LINK_CS2103T)
            .withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_NORMAL).build();
        DESC_CS2100 = new EditModuleDescriptorBuilder().withLectureDetails(VALID_LECTURE_DETAILS_CS2100)
            .withLectureDetails(VALID_LECTURE_DETAILS_CS2100).withLectureZoomLink(VALID_LECTURE_ZOOM_LINK_CS2100)
            .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2100).withTutorialZoomLink(VALID_TUTORIAL_ZOOM_LINK_CS2100)
            .withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_HARD, VALID_ASSIGNMENT_DETAILS_NORMAL).build();
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
        model.updateFilteredPersonList(new PersonDetailsContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the module at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showModuleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());

        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
        final String[] splitName = module.getModuleCode().moduleCode.split("\\s+");
        model.updateFilteredModuleList(new ModuleDetailsContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredModuleList().size());
    }

}
