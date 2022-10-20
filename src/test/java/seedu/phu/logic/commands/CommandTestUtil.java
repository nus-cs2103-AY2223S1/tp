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

    public static final String VALID_NAME_APPLE = "Apple";
    public static final String VALID_NAME_BLACKROCK = "Blackrock";
    public static final String VALID_PHONE_APPLE = "11111111";
    public static final String VALID_PHONE_BLACKROCK = "22222222";
    public static final String VALID_EMAIL_APPLE = "apple@example.com";
    public static final String VALID_EMAIL_BLACKROCK = "blackrock@example.com";
    public static final String VALID_REMARK_APPLE = "Block 312, Apple Street 1";
    public static final String VALID_REMARK_BLACKROCK = "Block 123, Blackrockby Street 3";
    public static final String VALID_POSITION_APPLE = "Backend Intern";
    public static final String VALID_POSITION_BLACKROCK = "FullStack Developer Intern";
    public static final String VALID_APPLICATION_PROCESS_APPLE = "interview";
    public static final String VALID_APPLICATION_PROCESS_BLACKROCK = "Assessment";
    public static final String VALID_DATE_APPLE = "11-03-2023";
    public static final String VALID_DATE_BLACKROCK = "05-12-2022";
    public static final String VALID_WEBSITE_APPLE = "https://www.apple.website.com/careers";
    public static final String VALID_WEBSITE_BLACKROCK = "https://www.blackrockwebsite.com/careers";
    public static final String VALID_TAG_TRANSPORT = "transport";
    public static final String VALID_TAG_STOCK = "stock";

    public static final String NAME_DESC_APPLE = " " + PREFIX_NAME + VALID_NAME_APPLE;
    public static final String NAME_DESC_BLACKROCK = " " + PREFIX_NAME + VALID_NAME_BLACKROCK;
    public static final String PHONE_DESC_APPLE = " " + PREFIX_PHONE + VALID_PHONE_APPLE;
    public static final String PHONE_DESC_BLACKROCK = " " + PREFIX_PHONE + VALID_PHONE_BLACKROCK;
    public static final String EMAIL_DESC_APPLE = " " + PREFIX_EMAIL + VALID_EMAIL_APPLE;
    public static final String EMAIL_DESC_BLACKROCK = " " + PREFIX_EMAIL + VALID_EMAIL_BLACKROCK;
    public static final String REMARK_DESC_APPLE = " " + PREFIX_REMARK + VALID_REMARK_APPLE;
    public static final String REMARK_DESC_BLACKROCK = " " + PREFIX_REMARK + VALID_REMARK_BLACKROCK;
    public static final String POSITION_DESC_APPLE = " " + PREFIX_POSITION + VALID_POSITION_APPLE;
    public static final String POSITION_DESC_BLACKROCK = " " + PREFIX_POSITION + VALID_POSITION_BLACKROCK;
    public static final String APPLICATION_PROCESS_DESC_BLACKROCK = " " + PREFIX_APPLICATION_PROCESS
            + VALID_APPLICATION_PROCESS_BLACKROCK;
    public static final String APPLICATION_PROCESS_DESC_APPLE = " " + PREFIX_APPLICATION_PROCESS
            + VALID_APPLICATION_PROCESS_APPLE;
    public static final String DATE_DESC_APPLE = " " + PREFIX_DATE + VALID_DATE_APPLE;
    public static final String DATE_DESC_BLACKROCK = " " + PREFIX_DATE + VALID_DATE_BLACKROCK;
    public static final String WEBSITE_DESC_APPLE = " " + PREFIX_WEBSITE + VALID_WEBSITE_APPLE;
    public static final String WEBSITE_DESC_BLACKROCK = " " + PREFIX_WEBSITE + VALID_WEBSITE_BLACKROCK;
    public static final String TAG_DESC_STOCK = " " + PREFIX_TAG + VALID_TAG_STOCK;
    public static final String TAG_DESC_TRANSPORT = " " + PREFIX_TAG + VALID_TAG_TRANSPORT;

    public static final String INVALID_APPLICATION_PROCESS_DESC = " " + PREFIX_APPLICATION_PROCESS + "Test";
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION + "softw@re engineer"; // '@' not allowed
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "1-09-2022"; // date should be 2 digits
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "blackrock!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_WEBSITE_DESC = " " + PREFIX_WEBSITE
            + "www.invalid.com/jobs"; // must start with https / http

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_APPLE;
    public static final EditCommand.EditInternshipDescriptor DESC_BLACKROCK;

    static {
        DESC_APPLE = new EditInternshipDescriptorBuilder().withName(VALID_NAME_APPLE)
                .withPhone(VALID_PHONE_APPLE).withEmail(VALID_EMAIL_APPLE).withRemark(VALID_REMARK_APPLE)
                .withPosition(VALID_POSITION_APPLE).withTags(VALID_TAG_STOCK).build();
        DESC_BLACKROCK = new EditInternshipDescriptorBuilder().withName(VALID_NAME_BLACKROCK)
                .withPhone(VALID_PHONE_BLACKROCK).withEmail(VALID_EMAIL_BLACKROCK).withRemark(VALID_REMARK_BLACKROCK)
                .withPosition(VALID_POSITION_BLACKROCK).withApplicationProcess(VALID_APPLICATION_PROCESS_BLACKROCK)
                .withDate(VALID_DATE_BLACKROCK).withWebsite(VALID_WEBSITE_BLACKROCK)
                .withTags(VALID_TAG_TRANSPORT, VALID_TAG_STOCK).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            CommandResult expectedCommandResult, Model expectedModel) {
        actualCommandHistory.addCommand(command.toString());
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        if (command instanceof AddCommand || command instanceof ClearCommand || command instanceof DeleteCommand
                || command instanceof EditCommand) {
            expectedCommandHistory.setLastCommandAsModify();
        }

        if (command instanceof UndoCommand) {
            expectedCommandHistory.getPreviousModifyCommand();
        }

        if (command instanceof RedoCommand) {
            expectedCommandHistory.getNextModifyCommand();
        }

        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the internship book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternshipBook expectedInternshipBook = new InternshipBook(actualModel.getInternshipBook());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, actualCommandHistory));
        assertEquals(expectedInternshipBook, actualModel.getInternshipBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
        assertEquals(expectedCommandHistory, actualCommandHistory);
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

    /**
     * Update {@code model}'s so that it only shows the first internship.
     */
    public static void findFirstInternship(Model model) {
        Internship firstInternship = model.getFilteredInternshipList().get(0);
        model.updateFilteredInternshipList(x -> x.isSameInternship(firstInternship));
    }

    /**
     * Deletes the first internship in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstInternship(Model model) {
        Internship firstInternship = model.getFilteredInternshipList().get(0);
        model.deleteInternship(firstInternship);
        model.commitInternshipBookChange();
    }

    /**
     * Deletes the first internship in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstInternship(Model model, CommandHistory commandHistory) {
        deleteFirstInternship(model);
        commandHistory.addCommand("delete 1");
        commandHistory.setLastCommandAsModify();
    }

    /**
     * Undo previous commands in {@code model}.
     */
    public static void undoPreviousCommand(Model model) {
        model.undoInternshipBook();
    }

    /**
     * Undo previous commands in {@code model}.
     */
    public static void undoPreviousCommand(Model model, CommandHistory commandHistory) {
        undoPreviousCommand(model);
        commandHistory.addCommand("undo");
        commandHistory.getPreviousModifyCommand();
    }

}
