package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.model.Model;
import seedu.address.model.ProfNus;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeContainsKeywordPredicate;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.ScheduleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;

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
    public static final String VALID_MODULE_CODE_CYBERSEC = "CS2107";
    public static final String VALID_MODULE_CODE_SWE = "CS2103T";
    public static final String VALID_MODULE_CODE_PL = "CS2104";
    public static final String VALID_MODULE_DESCRIPTION_CYBERSEC = "Cryptography basics";
    public static final String VALID_MODULE_DESCRIPTION_SWE = "Module teaches about software engineering";
    public static final String VALID_MODULE_NAME_CYBERSEC = "Introduction to Cybersecurity";
    public static final String VALID_MODULE_NAME_SWE = "Introduction to Software Engineering";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_ID_AMY = "A0123456W";
    public static final String VALID_ID_BOB = "A0123654W";
    public static final String VALID_TELEGRAM_AMY = "@amylovescode";
    public static final String VALID_TELEGRAM_BOB = "@bobhatescode";
    public static final String VALID_INFO_AMY = "CS1101S";
    public static final String VALID_INFO_BOB = "CS2030S";
    public static final String VALID_TEACHING_INFO_AMY = "CS1231S";
    public static final String VALID_TEACHING_INFO_BOB = "CS1231S";
    public static final String VALID_STUDENT_ID = "A0123123X";
    public static final String VALID_STUDENT_TELEGRAM = "@amyb123";
    public static final String VALID_STUDENT_INFO = "CS1101S";
    public static final String VALID_TEACHING_INFO = "CS2107";
    public static final String VALID_TAG_IMPORTANT = "important";
    public static final String VALID_TAG_MODULE_COORDINATOR = "ModuleCoordinator";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_HANDLE + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_HANDLE + VALID_TELEGRAM_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String STUDENT_DESC_ID = " " + PREFIX_ID + VALID_STUDENT_ID;
    public static final String STUDENT_DESC_TELEGRAM = " " + PREFIX_HANDLE + VALID_STUDENT_TELEGRAM;
    public static final String STUDENT_DESC_STUDENT_INFO = " " + PREFIX_MODULE_CODE + VALID_STUDENT_INFO;
    public static final String STUDENT_DESC_TEACHING_INFO = " " + PREFIX_STUDENT_TA + VALID_TEACHING_INFO;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ID_DESC = " " + PREFIX_ID + "A023B749W"; // 'B' not allowed in middle of ID
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_HANDLE + "good_user"; // missing '@' symbol

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStuCommand.EditStudentDescriptor DESC_AMY_STUDENT;
    public static final EditStuCommand.EditStudentDescriptor DESC_BOB_STUDENT;
    public static final EditModuleCommand.EditModuleDescriptor DESC_SWE;
    public static final EditModuleCommand.EditModuleDescriptor DESC_CYBERSEC;

    static {
        DESC_AMY_STUDENT = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withStudentId(VALID_ID_AMY).withTelegramHandle(VALID_TELEGRAM_AMY)
                .withAddress(VALID_STUDENT_INFO).build();
        DESC_BOB_STUDENT = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withStudentId(VALID_ID_BOB)
                .withTelegramHandle(VALID_TELEGRAM_BOB).withAddress(VALID_STUDENT_INFO).build();
        DESC_SWE = new EditModuleDescriptorBuilder().withName(VALID_MODULE_NAME_SWE)
                .withCode(VALID_MODULE_CODE_SWE).withDescription(VALID_MODULE_DESCRIPTION_SWE)
                .withTags(VALID_TAG_MODULE_COORDINATOR, VALID_TAG_IMPORTANT).build();
        DESC_CYBERSEC = new EditModuleDescriptorBuilder().withName(VALID_MODULE_NAME_CYBERSEC)
                .withCode(VALID_MODULE_CODE_CYBERSEC).withDescription(VALID_MODULE_DESCRIPTION_CYBERSEC)
                .withTags(VALID_TAG_MODULE_COORDINATOR, VALID_TAG_IMPORTANT).build();
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
     * - the profNus, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ProfNus expectedProfNus = new ProfNus(actualModel.getProfNus());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedProfNus, actualModel.getProfNus());
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
     * Updates {@code model}'s filtered list to show only the module at the given {@code targetIndex} in the
     * {@code model}'s address book.
     * @param model
     * @param targetIndex
     */
    public static void showModuleWithModuleCode(Model model, Index targetIndex) {
        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
        final ModuleCode moduleCode = module.getCode();
        model.updateFilteredModuleList(new ModuleCodeContainsKeywordPredicate(moduleCode));

        assertEquals(1, model.getFilteredModuleList().size());
    }

    /**
     * Updates {@code model}'s filtered schedule list to show only the schedule at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showScheduleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredScheduleList().size());

        Schedule schedule = model.getFilteredScheduleList().get(targetIndex.getZeroBased());
        final String weekday = schedule.getWeekday().name();
        model.updateFilteredScheduleList(new ScheduleContainsKeywordsPredicate(Arrays.asList(weekday)));
        assertEquals(1, model.getFilteredPersonList().size());
    }

}
