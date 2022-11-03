package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.application.model.Model.HIDE_ARCHIVE_IN_LIST;
import static seedu.application.model.Model.SHOW_ARCHIVE_ONLY;
import static seedu.application.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.CompanyContainsKeywordsPredicate;
import seedu.application.model.application.PositionContainsKeywordsPredicate;
import seedu.application.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    //Application fields
    public static final String VALID_COMPANY_GOOGLE = "Google";
    public static final String VALID_COMPANY_FACEBOOK = "Facebook";
    public static final String VALID_CONTACT_GOOGLE = "11111111";
    public static final String VALID_CONTACT_FACEBOOK = "22222222";
    public static final String VALID_DATE_GOOGLE = "2022-01-01";
    public static final String VALID_DATE_FACEBOOK = "2022-11-03";
    public static final String VALID_EMAIL_GOOGLE = "google@example.com";
    public static final String VALID_EMAIL_FACEBOOK = "facebook@example.com";
    public static final String VALID_POSITION_GOOGLE = "Software Engineer";
    public static final String VALID_POSITION_FACEBOOK = "Backend Engineer";
    public static final String VALID_STATUS_GOOGLE = "interview";
    public static final String VALID_STATUS_FACEBOOK = "pending";
    public static final String VALID_TAG_PREFERRED = "preferred";
    public static final String VALID_TAG_TECH_COMPANY = "techCompany";

    public static final String COMPANY_DESC_GOOGLE = " " + PREFIX_COMPANY + VALID_COMPANY_GOOGLE;
    public static final String COMPANY_DESC_FACEBOOK = " " + PREFIX_COMPANY + VALID_COMPANY_FACEBOOK;
    public static final String CONTACT_DESC_GOOGLE = " " + PREFIX_CONTACT + VALID_CONTACT_GOOGLE;
    public static final String CONTACT_DESC_FACEBOOK = " " + PREFIX_CONTACT + VALID_CONTACT_FACEBOOK;
    public static final String DATE_DESC_GOOGLE = " " + PREFIX_DATE + VALID_DATE_GOOGLE;
    public static final String DATE_DESC_FACEBOOK = " " + PREFIX_DATE + VALID_DATE_FACEBOOK;
    public static final String EMAIL_DESC_GOOGLE = " " + PREFIX_EMAIL + VALID_EMAIL_GOOGLE;
    public static final String EMAIL_DESC_FACEBOOK = " " + PREFIX_EMAIL + VALID_EMAIL_FACEBOOK;
    public static final String POSITION_DESC_GOOGLE = " " + PREFIX_POSITION + VALID_POSITION_GOOGLE;
    public static final String POSITION_DESC_FACEBOOK = " " + PREFIX_POSITION + VALID_POSITION_FACEBOOK;
    public static final String STATUS_DESC_GOOGLE = " " + PREFIX_STATUS + VALID_STATUS_GOOGLE;
    public static final String STATUS_DESC_FACEBOOK = " " + PREFIX_STATUS + VALID_STATUS_FACEBOOK;
    public static final String TAG_DESC_PREFERRED = " " + PREFIX_TAG + VALID_TAG_PREFERRED;
    public static final String TAG_DESC_TECH_COMPANY = " " + PREFIX_TAG + VALID_TAG_TECH_COMPANY;

    //Interview fields
    public static final String VALID_ROUND_GOOGLE = "Technical interview";
    public static final String VALID_INTERVIEW_DATE_GOOGLE = "2022-12-12";
    public static final String VALID_INTERVIEW_TIME_GOOGLE = "1100";
    public static final String VALID_LOCATION_GOOGLE = "11, Kallang Way 6, 118436";
    public static final String VALID_ROUND_FACEBOOK = "Online assessment";
    public static final String VALID_INTERVIEW_DATE_FACEBOOK = "2022-11-03";
    public static final String VALID_INTERVIEW_TIME_FACEBOOK = "1700";
    public static final String VALID_LOCATION_FACEBOOK = "Skype";
    public static final String ROUND_DESC_GOOGLE = " " + PREFIX_ROUND + VALID_ROUND_GOOGLE;
    public static final String INTERVIEW_DATE_DESC_GOOGLE = " " + PREFIX_INTERVIEW_DATE + VALID_INTERVIEW_DATE_GOOGLE;
    public static final String INTERVIEW_TIME_DESC_GOOGLE = " " + PREFIX_INTERVIEW_TIME + VALID_INTERVIEW_TIME_GOOGLE;
    public static final String LOCATION_DESC_GOOGLE = " " + PREFIX_LOCATION + VALID_LOCATION_GOOGLE;

    //Invalid Application fields
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "Google&"; // '&' not allowed in companies
    public static final String INVALID_CONTACT_DESC = " " + PREFIX_CONTACT + "911a"; // 'a' not allowed in contacts
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "01/09/2022"; // invalid date format
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "facebook!yahoo"; // missing '@' symbol
    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION; // empty string not allowed for positions
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "applied"; // not one of the allowed statuses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "senior referral"; // space not allowed in tags

    //Invalid Interview fields
    public static final String INVALID_ROUND_DESC = " " + PREFIX_ROUND + "     "; // empty string not allowed for rounds
    public static final String INVALID_INTERVIEW_DATE_DESC = " " + PREFIX_INTERVIEW_DATE
            + "01/09/2022"; // invalid date format
    public static final String INVALID_INTERVIEW_TIME_DESC = " " + PREFIX_INTERVIEW_TIME + "5678"; //invalid time format
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty string not allowed for location

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicationDescriptor DESC_GOOGLE;
    public static final EditCommand.EditApplicationDescriptor DESC_FACEBOOK;

    static {
        DESC_GOOGLE = new EditApplicationDescriptorBuilder().withCompany(VALID_COMPANY_GOOGLE)
                .withContact(VALID_CONTACT_GOOGLE).withEmail(VALID_EMAIL_GOOGLE).withPosition(VALID_POSITION_GOOGLE)
                .withDate(VALID_DATE_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
                .withTags(VALID_TAG_TECH_COMPANY).build();
        DESC_FACEBOOK = new EditApplicationDescriptorBuilder().withCompany(VALID_COMPANY_FACEBOOK)
                .withContact(VALID_CONTACT_FACEBOOK).withEmail(VALID_EMAIL_FACEBOOK)
                .withPosition(VALID_POSITION_FACEBOOK).withDate(VALID_DATE_FACEBOOK)
                .withStatus(VALID_STATUS_FACEBOOK).withTags(VALID_TAG_PREFERRED, VALID_TAG_TECH_COMPANY).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}, except for previously undone states
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            expectedModel.commitApplicationBook();
            actualModel.commitApplicationBook();
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the application book, filtered application list and selected application in {@code actualModel} are unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ApplicationBook expectedApplicationBook = new ApplicationBook(actualModel.getApplicationBook());
        List<Application> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedApplicationBook, actualModel.getApplicationBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicationList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s application book.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        Application application = model.getFilteredApplicationList().get(targetIndex.getZeroBased());
        final String[] splitCompany = application.getCompany().company.split("\\s+");
        final String[] splitPosition = application.getPosition().value.split("\\s+");
        model.updateFilteredApplicationList(new CompanyContainsKeywordsPredicate(Arrays.asList(splitCompany[0])));
        model.updateFilteredApplicationList(new PositionContainsKeywordsPredicate(Arrays.asList(splitPosition[0])));

        assertEquals(1, model.getFilteredApplicationList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code archiveStatus} in the
     * {@code model}'s application book.
     */
    public static void showApplicationByArchiveStatus(Model model, boolean isListRequiredArchived) {
        if (isListRequiredArchived) {
            model.updateFilteredApplicationList(SHOW_ARCHIVE_ONLY);
            for (Application app : model.getFilteredApplicationList()) {
                //filtered list should not have unarchived applications
                assertTrue(app.isArchived());
            }
        } else {
            model.updateFilteredApplicationList(HIDE_ARCHIVE_IN_LIST);
            for (Application app : model.getFilteredApplicationList()) {
                //filtered list should not have archived applications
                assertFalse(app.isArchived());
            }
        }
        //ensure filtered application has at least one application for testing
        assert model.getFilteredApplicationList().size() != 0;
    }
}
