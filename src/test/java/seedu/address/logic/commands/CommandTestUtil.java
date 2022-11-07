package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICEHOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMatchesPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_ANDERSON = "Anderson Johnson";
    public static final String VALID_NAME_BEN = "Ben Ten";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_COLIN = "Colin Lin";
    public static final String VALID_MODULE_CODE_AMY = "CS2013T";
    public static final String VALID_MODULE_CODE_BOB = "CS1231S";
    public static final String VALID_MODULE_CODE_CABE = "CS2100";
    public static final String VALID_NAME_CABE = "Cabe Tan";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CABE = "33333333";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_ANDERSON = "anderson@example.com";
    public static final String VALID_EMAIL_BEN = "ben@example.com";
    public static final String VALID_EMAIL_COLIN = "colin@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CABE = "cabe@example.com";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_GENDER_CABE = "M";
    public static final String VALID_GENDER_MALE = "M";
    public static final String VALID_GITHUB_AMY = "amyb";

    public static final String VALID_GITHUB_BOB = "bob123";
    public static final String VALID_GITHUB_CABE = "cabe123";
    public static final String VALID_LOCATION_AMY = "Comp 2";
    public static final String VALID_LOCATION_BOB = "Comp 3";
    public static final String VALID_LOCATION_CABE = "Comp 1";

    public static final HashSet<ModuleCode> VALID_MODULE_CODES_SET = new HashSet<>(List
            .of(new ModuleCode("CS1111"), new ModuleCode("CS2222")));
    public static final HashSet<ModuleCode> VALID_MODULE_CODES_SET_AMY = new HashSet<>(List
            .of(new ModuleCode(VALID_MODULE_CODE_AMY), new ModuleCode(VALID_MODULE_CODE_BOB)));
    public static final String VALID_OFFICE_HOUR = "MONDAY, 03:00 pm - 06:00 pm";
    public static final String VALID_OFFICE_HOUR_BOB = "TUESDAY, 03:00 pm - 06:00 pm";
    public static final String VALID_RATING = "4";
    public static final String VALID_RATING_CABE = "2";
    public static final String VALID_RATING_BOB = "3";
    public static final String VALID_RATING_ONE = "1";
    public static final String VALID_RATING_TWO = "2";
    public static final String VALID_SPECIALISATION = "Graphics";
    public static final String VALID_SPECIALISATION_BOB = "Networks";
    public static final String VALID_OFFICE_HOURS_BOB = "TUESDAY, 03:00 PM - 05:00 PM";

    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_USERNAME = "test";
    public static final String VALID_YEAR_AMY = "1";
    public static final String VALID_YEAR_BOB = "2";

    public static final String LOCATION_DESC_AMY = " " + PREFIX_LOCATION + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_LOCATION_BOB;
    public static final String LOCATION_DESC_CABE = " " + PREFIX_LOCATION + VALID_LOCATION_CABE;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CABE = " " + PREFIX_NAME + VALID_NAME_CABE;
    public static final String MODULE_CODE_DESC_AMY = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_AMY;
    public static final String MODULE_CODE_DESC_BOB = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_BOB;
    public static final String MODULE_CODE_DESC_CABE = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_CABE;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_CABE = " " + PREFIX_PHONE + VALID_PHONE_CABE;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CABE = " " + PREFIX_EMAIL + VALID_EMAIL_CABE;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String RATING_DESC_BOB = " " + PREFIX_RATING + VALID_RATING_BOB;
    public static final String RATING_DESC_CABE = " " + PREFIX_RATING + VALID_RATING_CABE;
    public static final String GITHUB_USERNAME_DESC_AMY = " " + PREFIX_GITHUBUSERNAME + VALID_GITHUB_AMY;
    public static final String GITHUB_USERNAME_DESC_BOB = " " + PREFIX_GITHUBUSERNAME + VALID_GITHUB_BOB;

    public static final String GENDER_DESC_CABE = " " + PREFIX_GENDER + VALID_GENDER_CABE;
    public static final String OFFICE_HOUR_MONDAY = " " + PREFIX_OFFICEHOUR + "MONDAY, 4:00 PM - 5:00 PM";

    public static final String OFFICE_HOUR_DESC_MONDAY = " " + PREFIX_OFFICEHOUR + "1-15:00-3";
    public static final String OFFICE_HOUR_DESC_TUESDAY = " " + PREFIX_OFFICEHOUR + "2-15:00-3";
    public static final String RATING_DESC_ONE = " " + PREFIX_RATING + VALID_RATING_ONE;
    public static final String RATING_DESC_TWO = " " + PREFIX_RATING + VALID_RATING_TWO;
    public static final String SPECIALISATION_DESC_GRAPHICS = " " + PREFIX_SPECIALISATION + VALID_SPECIALISATION;
    public static final String SPECIALISATION_DESC_BOB = " " + PREFIX_SPECIALISATION + VALID_SPECIALISATION_BOB;

    public static final String GITHUB_USERNAME_DESC_CABE = " " + PREFIX_GITHUBUSERNAME + VALID_GITHUB_CABE;

    public static final String OFFICE_HOURS_DESC_BOB = " " + PREFIX_OFFICEHOUR + VALID_OFFICE_HOURS_BOB;

    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String YEAR_DESC_AMY = " " + PREFIX_YEAR + VALID_YEAR_AMY;
    public static final String YEAR_DESC_BOB = " " + PREFIX_YEAR + VALID_YEAR_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER; // empty string not allowed for gender
    public static final String INVALID_GITHUB_DESC = " "
            + PREFIX_GITHUBUSERNAME + "---"; // consecutive hyphens not allowed for github

    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE_CODE + " "; // empty string not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_YEAR_DESC = " " + PREFIX_YEAR + "5*"; // 5 is an invalid year
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING + "6"; // rating is out of 5
    public static final String INVALID_OFFICE_HOUR_DESC = " " + PREFIX_OFFICEHOUR
            + "8-25:00-o"; //day and time out of range, letter used for hours
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION + " "; //empty string not allowed

    public static final String PREAMBLE_WHITESPACE = "a/ b/ c/ ";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    //Used as a student
    public static final EditCommand.EditPersonDescriptor STUDENT_AMY;
    //Used as a professor
    public static final EditCommand.EditPersonDescriptor PROFESSOR_BOB;
    //Used a teaching assistant
    public static final EditCommand.EditPersonDescriptor TEACHING_ASSISTANT_CABE;

    public static final String VALID_NAMES = "Kurz Elle Kunz";

    public static final List<String> NO_RESULTS_LIST = Arrays.asList("! @ # $ %^&* ()- 12 34 56 789 0".split("\\s+"));

    public static final List<String> EMPTY_LIST = new ArrayList<>();

    public static final List<String> VALID_NAMES_LIST = Arrays.asList(VALID_NAMES.split("\\s+"));

    static {
        STUDENT_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withModuleCodeSet(VALID_MODULE_CODES_SET_AMY).withLocation(VALID_LOCATION_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withGender(VALID_GENDER_AMY)
            .withTags(VALID_TAG_FRIEND).withUsername(VALID_GITHUB_AMY).withYear(VALID_YEAR_AMY).build();
        PROFESSOR_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withRating(VALID_RATING_BOB).withLocation(VALID_LOCATION_BOB)
            .withSpecialisation(VALID_SPECIALISATION_BOB)
            .withOfficeHour(VALID_OFFICE_HOUR_BOB)
            .withModuleCode(VALID_MODULE_CODE_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withGender(VALID_GENDER_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        TEACHING_ASSISTANT_CABE = new EditPersonDescriptorBuilder().withName(VALID_NAME_CABE)
                .withRating(VALID_RATING_CABE).withLocation(VALID_LOCATION_CABE)
            .withModuleCode(VALID_MODULE_CODE_CABE)
            .withPhone(VALID_PHONE_CABE).withEmail(VALID_EMAIL_CABE).withGender(VALID_GENDER_CABE)
            .withTags(VALID_TAG_FRIEND).build();
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
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setNamesList(List.of(splitName[0]));
        model.updateFilteredPersonList(predicate);

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
