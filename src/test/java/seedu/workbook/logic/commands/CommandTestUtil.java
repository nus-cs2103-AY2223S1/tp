package seedu.workbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.workbook.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.workbook.commons.core.index.Index;
import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_COMPANY_AMY = "Amy Bee";
    public static final String VALID_COMPANY_BOB = "Bob Choo";
    public static final String VALID_ROLE_AMY = "Software Engineer";
    public static final String VALID_ROLE_BOB = "Software Engineer";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_STAGE_AMY = "Technical Interview";
    public static final String VALID_STAGE_BOB = "HR Interview";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String COMPANY_DESC_AMY = " " + PREFIX_COMPANY + VALID_COMPANY_AMY;
    public static final String COMPANY_DESC_BOB = " " + PREFIX_COMPANY + VALID_COMPANY_BOB;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String STAGE_DESC_AMY = " " + PREFIX_STAGE + VALID_STAGE_AMY;
    public static final String STAGE_DESC_BOB = " " + PREFIX_STAGE + VALID_STAGE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    // CHECKSTYLE.OFF: LineLength
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "James&"; // '&' not allowed in company name
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "James&"; // '&' not allowed in roles
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_STAGE_DESC = " " + PREFIX_STAGE + "H&Interview"; // '&' not allowed in stage
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    // CHECKSTYLE.ON: LineLength

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_AMY;
    public static final EditCommand.EditInternshipDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditInternshipDescriptorBuilder()
            .withCompany(VALID_COMPANY_AMY)
            .withRole(VALID_ROLE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditInternshipDescriptorBuilder()
            .withCompany(VALID_COMPANY_BOB)
            .withRole(VALID_ROLE_BOB)
            .withEmail(VALID_EMAIL_BOB)
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
     * - the work book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        WorkBook expectedWorkBook = new WorkBook(actualModel.getWorkBook());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedWorkBook, actualModel.getWorkBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s work book.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitCompany = internship.getCompany().name.split("\\s+");
        model.updateFilteredInternshipList(new CompanyContainsKeywordsPredicate(Arrays.asList(splitCompany[0])));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

    /**
     * Deletes the first internship in {@code model}'s filtered list from {@code model}'s work book.
     */
    public static void deleteFirstInternship(Model model) {
        Internship firstInternship = model.getFilteredInternshipList().get(0);
        model.deleteInternship(firstInternship);
        model.commitWorkBook();
    }
}
