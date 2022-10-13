package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTOFKIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTuitionClassDescriptorBuilder;
import seedu.address.testutil.EditTutorDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ENTITY_STUDENT = "student";
    public static final String VALID_ENTITY_TUTOR = "tutor";
    public static final String VALID_ENTITY_CLASS = "class";


    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CLASS1 = "Class1 Name";
    public static final String VALID_NAME_CLASS2 = "Class2 Name";

    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_SCHOOL_AMY = "Amy Primary School";
    public static final String VALID_SCHOOL_BOB = "Bob Primary School";
    public static final String VALID_LEVEL_AMY = "Primary1";
    public static final String VALID_LEVEL_BOB = "Primary2";
    public static final String VALID_NEXTOFKIN_AMY = "Amy Nok";
    public static final String VALID_NEXTOFKIN_BOB = "Bob Nok";

    public static final String VALID_QUALIFICATION_AMY = "BAmy";
    public static final String VALID_QUALIFICATION_BOB = "MBob";
    public static final String VALID_INSTITUTION_AMY = "Amy University";
    public static final String VALID_INSTITUTION_BOB = "Bob University";

    public static final String VALID_SUBJECT_CLASS1 = "Chemistry";
    public static final String VALID_SUBJECT_CLASS2 = "Physics";
    public static final String VALID_LEVEL_CLASS1 = "Primary3";
    public static final String VALID_LEVEL_CLASS2 = "Secondary3";
    public static final String VALID_DAY_CLASS1 = "Wednesday";
    public static final String VALID_DAY_CLASS2 = "Thursday";
    public static final String VALID_STARTTIME_CLASS1 = "12:00";
    public static final String VALID_STARTTIME_CLASS2 = "08:00";
    public static final String VALID_ENDTIME_CLASS1 = "14:00";
    public static final String VALID_ENDTIME_CLASS2 = "10:00";

    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String ENTITY_DESC_STUDENT = " " + VALID_ENTITY_STUDENT;
    public static final String ENTITY_DESC_TUTOR = " " + VALID_ENTITY_TUTOR;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String SCHOOL_DESC_AMY = " " + PREFIX_SCHOOL + VALID_SCHOOL_AMY;
    public static final String SCHOOL_DESC_BOB = " " + PREFIX_SCHOOL + VALID_SCHOOL_BOB;
    public static final String LEVEL_DESC_AMY = " " + PREFIX_LEVEL + VALID_LEVEL_AMY;
    public static final String LEVEL_DESC_BOB = " " + PREFIX_LEVEL + VALID_LEVEL_BOB;
    public static final String NEXTOFKIN_DESC_AMY = " " + PREFIX_NEXTOFKIN + VALID_NEXTOFKIN_AMY;
    public static final String NEXTOFKIN_DESC_BOB = " " + PREFIX_NEXTOFKIN + VALID_NEXTOFKIN_BOB;

    public static final String QUALIFICATION_DESC_AMY = " " + PREFIX_QUALIFICATION + VALID_QUALIFICATION_AMY;
    public static final String QUALIFICATION_DESC_BOB = " " + PREFIX_QUALIFICATION + VALID_QUALIFICATION_BOB;
    public static final String INSTITUTION_DESC_AMY = " " + PREFIX_INSTITUTION + VALID_INSTITUTION_AMY;
    public static final String INSTITUTION_DESC_BOB = " " + PREFIX_INSTITUTION + VALID_INSTITUTION_BOB;

    //class related here
    public static final String ENTITY_DESC_CLASS = " " + VALID_ENTITY_CLASS;
    public static final String NAME_DESC_CLASS1 = " " + PREFIX_NAME + VALID_NAME_CLASS1;
    public static final String NAME_DESC_CLASS2 = " " + PREFIX_NAME + VALID_NAME_CLASS2;
    public static final String SUBJECT_DESC_CLASS1 = " " + PREFIX_SUBJECT + VALID_SUBJECT_CLASS1;
    public static final String SUBJECT_DESC_CLASS2 = " " + PREFIX_SUBJECT + VALID_SUBJECT_CLASS2;
    public static final String LEVEL_DESC_CLASS1 = " " + PREFIX_LEVEL + VALID_LEVEL_CLASS1;
    public static final String LEVEL_DESC_CLASS2 = " " + PREFIX_LEVEL + VALID_LEVEL_CLASS2;
    public static final String DAY_DESC_CLASS1 = " " + PREFIX_DAY + VALID_DAY_CLASS1;
    public static final String DAY_DESC_CLASS2 = " " + PREFIX_DAY + VALID_DAY_CLASS2;
    public static final String TIME_DESC_CLASS1 = " " + PREFIX_TIME + VALID_STARTTIME_CLASS1 + "-"
            + VALID_ENDTIME_CLASS1;
    public static final String TIME_DESC_CLASS2 = " " + PREFIX_TIME + VALID_STARTTIME_CLASS2 + "-"
            + VALID_ENDTIME_CLASS2;

    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_ENTITY_DESC = " " + "parents"; // only allow 'student', 'tutor', 'class'

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names

    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_SCHOOL_DESC =
            " " + PREFIX_SCHOOL + "& Primary School"; // '&' not allowed in school name
    public static final String INVALID_LEVEL_DESC =
            " " + PREFIX_LEVEL + "Kindergarten1"; //outside p1-6, s1-4 not allowed
    public static final String INVALID_NEXTOFKIN_DESC =
            " " + PREFIX_NEXTOFKIN + "& Doe"; // '&' now allowed in nok names
    public static final String INVALID_QUALIFICATION_DESC =
            " " + PREFIX_QUALIFICATION + "M&c"; // '&' not allowed in qualifications name
    public static final String INVALID_INSTITUTION_DESC =
            " " + PREFIX_INSTITUTION + "& University"; // '&' not allowed in institutions name

    public static final String INVALID_SUBJECT_DESC =
            " " + PREFIX_SUBJECT + "Philosophy"; //outside set subjects not allowed
    public static final String INVALID_DAY_DESC =
            " " + PREFIX_DAY + "Newday"; // only allow 'monday' to 'sunday'
    public static final String INVALID_TIME1_DESC =
            " " + PREFIX_TIME + "12:00to13:00"; // only allow dash separation
    public static final String INVALID_TIME2_DESC =
            " " + PREFIX_TIME + "12:00-11:00"; // go back in time not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditCommand.EditStudentDescriptor DESC_AMY_STUDENT;
    public static final EditCommand.EditStudentDescriptor DESC_BOB_STUDENT;
    public static final EditCommand.EditTutorDescriptor DESC_AMY_TUTOR;
    public static final EditCommand.EditTutorDescriptor DESC_BOB_TUTOR;
    public static final EditCommand.EditTuitionClassDescriptor DESC_AMY_CLASS;
    public static final EditCommand.EditTuitionClassDescriptor DESC_BOB_CLASS;


    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_AMY_STUDENT = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withSchool(VALID_SCHOOL_AMY).withLevel(VALID_LEVEL_AMY)
                .withNextOfKin(VALID_NEXTOFKIN_AMY).build();
        DESC_BOB_STUDENT = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withSchool(VALID_SCHOOL_BOB).withLevel(VALID_LEVEL_BOB)
                .withNextOfKin(VALID_NEXTOFKIN_BOB).build();
        DESC_AMY_TUTOR = new EditTutorDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withQualification(VALID_QUALIFICATION_AMY)
                .withInstitution(VALID_INSTITUTION_AMY).build();
        DESC_BOB_TUTOR = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withQualification(VALID_QUALIFICATION_BOB)
                .withInstitution(VALID_INSTITUTION_BOB).build();
        DESC_AMY_CLASS = new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_CLASS1)
                .withSubject(VALID_SUBJECT_CLASS1).withLevel(VALID_LEVEL_CLASS1).withDay(VALID_DAY_CLASS1)
                .withTime(VALID_STARTTIME_CLASS1, VALID_ENDTIME_CLASS1).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB_CLASS = new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_CLASS2)
                .withSubject(VALID_SUBJECT_CLASS2).withLevel(VALID_LEVEL_CLASS2).withDay(VALID_DAY_CLASS2)
                .withTime(VALID_STARTTIME_CLASS2, VALID_ENDTIME_CLASS2).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel, boolean isListCommand) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, true);
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
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate<Person>(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate<Student>(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tutor at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTutorAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTutorList().size());

        Tutor tutor = model.getFilteredTutorList().get(targetIndex.getZeroBased());
        final String[] splitName = tutor.getName().fullName.split("\\s+");
        model.updateFilteredTutorList(new NameContainsKeywordsPredicate<Tutor>(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTutorList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tuition class at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTuitionClassAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTuitionClassList().size());

        TuitionClass tuitionClass = model.getFilteredTuitionClassList().get(targetIndex.getZeroBased());
        final String[] splitName = tuitionClass.getName().name.split("\\s+");
        model.updateFilteredTuitionClassList(
                new NameContainsKeywordsPredicate<TuitionClass>(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTuitionClassList().size());
    }

}
