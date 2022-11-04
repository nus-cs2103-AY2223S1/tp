package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailureForPrefix;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentsHealthContact;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindAppointmentCommandParserTest {
    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();
    private Model model = new ModelManager(getTypicalAppointmentsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAppointmentsHealthContact(), new UserPrefs());

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindAppointmentCommand() throws ParseException {
        // no leading and trailing whitespaces
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FindAppointmentCommand firstCommand = parser.parse(" n/pauline");
        expectedModel.updateFilteredAppointmentList(firstCommand.getPredicate());
        assertCommandSuccess(firstCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1), model.getFilteredAppointmentList());

        // multiple whitespaces between keywords
        FindAppointmentCommand secondCommand = parser.parse(" n/    pauline  ");
        expectedModel.updateFilteredAppointmentList(secondCommand.getPredicate());
        assertCommandSuccess(secondCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1), model.getFilteredAppointmentList());

        //multiple fields
        FindAppointmentCommand thirdCommand = parser.parse(" n/meier t/kidney s/01- d/milly ");
        expectedModel.updateFilteredAppointmentList(thirdCommand.getPredicate());
        assertCommandSuccess(thirdCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_2), model.getFilteredAppointmentList());
    }

    @Test
    public void checkNumberOfPrefixes() {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" t/test t/function", PREFIX_NAME, PREFIX_MEDICAL_TEST,
                        PREFIX_SLOT, PREFIX_DOCTOR);
        assertParseFailureForPrefix(parser, PREFIX_MEDICAL_TEST, argMultimap,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void createPredicateString() throws ParseException {
        String testString = parser.createPredicateString("   alice      pauline  ".trim());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FindAppointmentCommand firstCommand = parser.parse(" n/" + testString);
        expectedModel.updateFilteredAppointmentList(firstCommand.getPredicate());
        assertCommandSuccess(firstCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1), model.getFilteredAppointmentList());
    }
}
