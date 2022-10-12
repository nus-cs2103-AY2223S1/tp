package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_APPLICATION_PROCESS;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.phu.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.phu.commons.core.index.Index;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.InternshipBook;
import seedu.phu.model.Model;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.NameContainsKeywordsPredicate;
import seedu.phu.testutil.EditInternshipDescriptorBuilder;

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
    public static final String VALID_INTERNSHIP_AMY = "Block 312, Amy Street 1";
    public static final String VALID_POSITION_AMY = "Backend Intern";
    public static final String VALID_INTERNSHIP_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_POSITION_BOB = "FullStack Developer Intern";
    public static final String VALID_APPLICATION_PROCESS_BOB = "Assessment";
    public static final String VALID_DATE_BOB = "05-12-2022";
    public static final String VALID_WEBSITE_BOB = "https://www.bobwebsite.com/careers";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String INTERNSHIP_DESC_AMY = " " + PREFIX_REMARK + VALID_INTERNSHIP_AMY;
    public static final String POSITION_DESC_AMY = " " + PREFIX_POSITION + VALID_POSITION_AMY;
    public static final String INTERNSHIP_DESC_BOB = " " + PREFIX_REMARK + VALID_INTERNSHIP_BOB;
    public static final String POSITION_DESC_BOB = " " + PREFIX_POSITION + VALID_POSITION_BOB;
    public static final String APPLICATION_PROCESS_DESC_BOB = " " + PREFIX_APPLICATION_PROCESS
            + VALID_APPLICATION_PROCESS_BOB;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE + VALID_DATE_BOB;
    public static final String WEBSITE_DESC_BOB = " " + PREFIX_WEBSITE + VALID_WEBSITE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_INTERNSHIP_DESC = " " + PREFIX_REMARK; // empty string not allowed for internship
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_AMY;
    public static final EditCommand.EditInternshipDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withInternship(VALID_INTERNSHIP_AMY)
                .withPosition(VALID_POSITION_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditInternshipDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withInternship(VALID_INTERNSHIP_BOB)
                .withPosition(VALID_POSITION_BOB).withApplicationProcess(VALID_APPLICATION_PROCESS_BOB)
                .withDate(VALID_DATE_BOB).withWebsite(VALID_WEBSITE_BOB)
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
     * - the internship book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternshipBook expectedInternshipBook = new InternshipBook(actualModel.getInternshipBook());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternshipBook, actualModel.getInternshipBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s internship book.
     */
    public static void showInternshipAtIndex(Model model, Index ... targetIndexes) {
        List<String> keywords = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

            Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
            final String[] splitName = internship.getName().fullName.split("\\s+");

            keywords.addAll(Arrays.asList(splitName[0]));
        }

        model.updateFilteredInternshipList(new NameContainsKeywordsPredicate(keywords));

        // assertEquals(1, model.getFilteredInternshipList().size());
    }
}
