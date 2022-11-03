package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADUATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;
import static seedu.address.model.person.Cap.CAP_SEPARATOR;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_GENDER_AMY = "Female";
    public static final String VALID_GENDER_BOB = "Male";
    public static final String VALID_GRADUATION_DATE_AMY = "05-2024";
    public static final String VALID_GRADUATION_DATE_BOB = "05-2025";
    public static final String VALID_UNIVERSITY_AMY = "NUS";
    public static final String VALID_UNIVERSITY_BOB = "NTU";
    public static final String VALID_MAJOR_AMY = "Computer Science";
    public static final String VALID_MAJOR_BOB = "Computer Engineering";
    public static final String VALID_JOB_ID_AMY = "167839";
    public static final String VALID_JOB_ID_BOB = "J9204342";
    public static final String VALID_JOB_TITLE_AMY = "Software Engineer Intern";
    public static final String VALID_JOB_TITLE_BOB = "Backend Engineer Intern (May'23 - July'23)";
    public static final String VALID_TAG_KIV = "KIV";
    public static final String VALID_TAG_REJECTED = "rejected";
    public static final double VALID_CAP_VALUE_AMY = 3.98;
    public static final double VALID_MAXIMUM_CAP_VALUE_AMY = 4.0;
    public static final double VALID_CAP_VALUE_BOB = 4.01;
    public static final double VALID_MAXIMUM_CAP_VALUE_BOB = 5.0;
    public static final String VALID_CAP_AMY = VALID_CAP_VALUE_AMY + CAP_SEPARATOR + VALID_MAXIMUM_CAP_VALUE_AMY;
    public static final String VALID_CAP_BOB = VALID_CAP_VALUE_BOB + CAP_SEPARATOR + VALID_MAXIMUM_CAP_VALUE_BOB;
    public static final String VALID_PATH_JERRY = "src/folder/jerry.jpg";
    public static final String VALID_PATH_JERRY_WITH_SPACE = "src/folder/jerry with space.jpg";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String GRADUATION_DATE_DESC_AMY = " " + PREFIX_GRADUATION_DATE + VALID_GRADUATION_DATE_AMY;
    public static final String GRADUATION_DATE_DESC_BOB = " " + PREFIX_GRADUATION_DATE + VALID_GRADUATION_DATE_BOB;
    public static final String UNIVERSITY_DESC_AMY = " " + PREFIX_UNIVERSITY + VALID_UNIVERSITY_AMY;
    public static final String UNIVERSITY_DESC_BOB = " " + PREFIX_UNIVERSITY + VALID_UNIVERSITY_BOB;
    public static final String MAJOR_DESC_AMY = " " + PREFIX_MAJOR + VALID_MAJOR_AMY;
    public static final String MAJOR_DESC_BOB = " " + PREFIX_MAJOR + VALID_MAJOR_BOB;
    public static final String JOB_ID_DESC_AMY = " " + PREFIX_JOB_ID + VALID_JOB_ID_AMY;
    public static final String JOB_ID_DESC_BOB = " " + PREFIX_JOB_ID + VALID_JOB_ID_BOB;
    public static final String JOB_TITLE_DESC_AMY = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_AMY;
    public static final String JOB_TITLE_DESC_BOB = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_BOB;
    public static final String TAG_DESC_REJECTED = " " + PREFIX_TAG + VALID_TAG_REJECTED;
    public static final String TAG_DESC_KIV = " " + PREFIX_TAG + VALID_TAG_KIV;
    public static final String CAP_DESC_AMY = " " + PREFIX_CAP + VALID_CAP_AMY;
    public static final String CAP_DESC_BOB = " " + PREFIX_CAP + VALID_CAP_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "4Male"; // '4' not allowed in gender
    public static final String INVALID_GRADUATION_DESC = " "
            + PREFIX_GRADUATION_DATE + "asdf-12"; //alphabets not allowed in graduation date
    public static final String INVALID_UNIVERSITY_DESC = " " + PREFIX_UNIVERSITY + "St@r"; // '@' not allowed for
    // universities
    public static final String INVALID_MAJOR_DESC = " " + PREFIX_MAJOR + "C0MPUT3R $C13NC3"; // '0, 3, $, 1'
    // not allowed for majors
    public static final String INVALID_JOB_ID_DESC = " " + PREFIX_JOB_ID + "J9021-1"; // '-' not allowed in job IDs
    public static final String INVALID_JOB_TITLE_DESC = " " + PREFIX_JOB_TITLE
            + "Intern | Software Engineer"; // '|' not allowed in job titles
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "offered*"; // '*' not allowed in tags
    // CAP value should not exceed its maximum value
    public static final String INVALID_CAP_DESC = " " + PREFIX_CAP + "5.01/5";
    public static final String INVALID_PATH_JERRY = "src/folder\0jerry.jpg";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withCap(VALID_CAP_VALUE_AMY, VALID_MAXIMUM_CAP_VALUE_AMY)
                .withUniversity(VALID_UNIVERSITY_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withGender(VALID_GENDER_AMY)
                .withGraduationDate(VALID_GRADUATION_DATE_AMY)
                .withId(VALID_JOB_ID_AMY)
                .withTitle(VALID_JOB_TITLE_AMY)
                .withTags(VALID_TAG_REJECTED).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withId(VALID_JOB_ID_BOB)
                .withTitle(VALID_JOB_TITLE_BOB)
                .withTags(VALID_TAG_KIV, VALID_TAG_REJECTED).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        StorageStub storageStub = new StorageStub();
        try {
            CommandResult result = command.execute(actualModel, storageStub);
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
        StorageStub storageStub = new StorageStub();
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, storageStub));
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

    /**
     * A default storage stub that have all of the methods failing.
     */
    private static class StorageStub implements Storage {

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookStorage(AddressBookStorage addressBookStorage) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void exportDisplayedListAddressBook(ReadOnlyAddressBook displayedListAddressBook, String filePathString)
                throws IOException {
            throw new AssertionError("This method should not be called.");
        }

    }

}
