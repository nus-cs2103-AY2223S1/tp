package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class InternshipCommandTestUtil {

    public static final String VALID_NAME_ABC = "ABC Pte Ltd";
    public static final String VALID_NAME_BOBBY = "Bobby Constructions Pte Ltd";
    public static final String VALID_ROLE_ABC = "Software Engineer";
    public static final String VALID_ROLE_BOBBY = "Admin Assistant";
    public static final String VALID_STATUS_ABC = InternshipStatus.State.PENDING.toString();
    public static final String VALID_STATUS_BOBBY = InternshipStatus.State.ACCEPTED.toString();
    public static final String VALID_CONTACT_PERSON_ABC = null;
    public static final String VALID_CONTACT_PERSON_BOBBY = null;
    public static final String VALID_INTERVIEW_ABC = "2022-11-11 11:11";
    public static final String VALID_INTERVIEW_BOBBY = "2022-10-10 12:34";

    public static final String NAME_DESC_ABC = " " + PREFIX_COMPANY_NAME + VALID_NAME_ABC;
    public static final String NAME_DESC_BOBBY = " " + PREFIX_COMPANY_NAME + VALID_NAME_BOBBY;
    public static final String ROLE_DESC_ABC = " " + PREFIX_INTERNSHIP_ROLE + VALID_ROLE_ABC;
    public static final String ROLE_DESC_BOBBY = " " + PREFIX_INTERNSHIP_ROLE + VALID_ROLE_BOBBY;
    public static final String STATUS_DESC_ABC = " " + PREFIX_INTERNSHIP_STATUS + VALID_STATUS_ABC;
    public static final String STATUS_DESC_BOBBY = " " + PREFIX_INTERNSHIP_STATUS + VALID_STATUS_BOBBY;
    public static final String INTERVIEW_DESC_ABC = " " + PREFIX_INTERVIEW_DATE + VALID_INTERVIEW_ABC;
    public static final String INTERVIEW_DESC_BOBBY = " " + PREFIX_INTERVIEW_DATE + VALID_INTERVIEW_BOBBY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_ROLE_DESC = " " + PREFIX_INTERNSHIP_ROLE + "911&"; // '&' not allowed in phones
    public static final String INVALID_STATUS_DESC = " " + PREFIX_INTERNSHIP_STATUS + "bob!yahoo"; // not a status
    public static final String INVALID_INTERVIEW_DESC = " " + PREFIX_INTERVIEW_DATE + "2022-11-10"; // missing time

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    public static final EditInternshipCommand.EditInternshipDescriptor DESC_ABC;
    public static final EditInternshipCommand.EditInternshipDescriptor DESC_BOBBY;

    static {
        DESC_ABC = new EditInternshipDescriptorBuilder().withCompanyName(VALID_NAME_ABC)
                .withInternshipRole(VALID_ROLE_ABC).withInternshipStatus(VALID_STATUS_ABC)
                .withInterviewDate(VALID_INTERVIEW_ABC).build();
        DESC_BOBBY = new EditInternshipDescriptorBuilder().withCompanyName(VALID_NAME_BOBBY)
                .withInternshipRole(VALID_ROLE_BOBBY).withInternshipStatus(VALID_STATUS_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY).build();
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
        final String[] splitName = internship.getCompanyName().fullName.split("\\s+");
        model.updateFilteredInternshipList(new InternshipContainsKeywordsPredicate(
                Arrays.asList(splitName[0]),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
