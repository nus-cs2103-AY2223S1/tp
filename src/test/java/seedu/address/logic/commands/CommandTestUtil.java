package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLIED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.internship.CompanyContainsKeywordsPredicate;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_COMPANY_GOOGLE = "Google";
    public static final String VALID_COMPANY_TIKTOK = "Tiktok";
    public static final String VALID_LINK_GOOGLE = "https://careers.google.com/jobs/results/97935383573996230/";
    public static final String VALID_LINK_TIKTOK = "https://careers.tiktok.com/position/7132807469026117902/detail";
    public static final String VALID_DESCRIPTION_GOOGLE = "Software Engineering Intern, BS, Summer 2023";
    public static final String VALID_DESCRIPTION_TIKTOK = "Frontend Engineer Intern (Global e-Commerce) - 2023";
    public static final String VALID_APPLICATION_STATUS_GOOGLE = "applied";
    public static final String VALID_APPLICATION_STATUS_TIKTOK = "applied";
    public static final String VALID_APPLIED_DATE_GOOGLE = "12/10/2022";
    public static final String VALID_APPLIED_DATE_TIKTOK = "01/09/2022";
    public static final String VALID_TAG_FRONTEND = "Frontend";
    public static final String VALID_TAG_BACKEND = "Backend";
    public static final String VALID_TAG_AI = "AI";

    public static final String COMPANY_DESC_GOOGLE = " " + PREFIX_COMPANY + VALID_COMPANY_GOOGLE;
    public static final String COMPANY_DESC_TIKTOK = " " + PREFIX_COMPANY + VALID_COMPANY_TIKTOK;
    public static final String LINK_DESC_GOOGLE = " " + PREFIX_LINK + VALID_LINK_GOOGLE;
    public static final String LINK_DESC_TIKTOK = " " + PREFIX_LINK + VALID_LINK_TIKTOK;
    public static final String DESCRIPTION_DESC_GOOGLE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_GOOGLE;
    public static final String DESCRIPTION_DESC_TIKTOK = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_TIKTOK;
    public static final String APPLIED_DATE_DESC_GOOGLE = " " + PREFIX_APPLIED_DATE + VALID_APPLIED_DATE_GOOGLE;
    public static final String APPLIED_DATE_DESC_TIKTOK = " " + PREFIX_APPLIED_DATE + VALID_APPLIED_DATE_TIKTOK;
    public static final String TAG_DESC_FRONTEND = " " + PREFIX_TAG + VALID_TAG_FRONTEND;
    public static final String TAG_DESC_BACKEND = " " + PREFIX_TAG + VALID_TAG_BACKEND;
    public static final String TAG_DESC_AI = " " + PREFIX_TAG + VALID_TAG_AI;

    // To change
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "Meta&"; // '&' not allowed in names
    public static final String INVALID_LINK_DESC = " " + PREFIX_LINK + "911a"; // 'a' not allowed in phones
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_APPLIED_DATE_DESC = " " + PREFIX_APPLIED_DATE; // empty string not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "Frontend*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_GOOGLE;
    public static final EditCommand.EditInternshipDescriptor DESC_TIKTOK;

    static {
        DESC_GOOGLE = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_GOOGLE)
                .withLink(VALID_LINK_GOOGLE).withDescription(VALID_DESCRIPTION_GOOGLE)
                .withAppliedDate(VALID_APPLIED_DATE_GOOGLE).withTags(VALID_TAG_FRONTEND).build();
        DESC_TIKTOK = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_TIKTOK)
                .withLink(VALID_LINK_TIKTOK).withDescription(VALID_DESCRIPTION_TIKTOK)
                .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).withTags(VALID_TAG_BACKEND, VALID_TAG_AI).build();
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
     * - the address book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitName = internship.getCompany().value.split("\\s+");
        model.updateFilteredInternshipList(new CompanyContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
