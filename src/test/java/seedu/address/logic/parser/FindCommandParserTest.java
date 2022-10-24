package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.START_DATE_AFTER_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REASON_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EAR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_THROAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REASON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_THROAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME_START;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicate;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyDateTime;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedAppointmentPredicateWithOnlyReason;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicate;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyTags;
import static seedu.address.testutil.PredicateGeneratorUtil.generateEmptyCombinedAppointmentPredicate;
import static seedu.address.testutil.PredicateGeneratorUtil.generateEmptyCombinedPersonPredicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicates.CombinedAppointmentPredicate;
import seedu.address.model.person.predicates.CombinedPersonPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    private String validDateTimeStart = "2022-12-13 12:12";
    private String validDateTimeEnd = "2025-12-13 12:12";
    private String invalidDate = "2999-99-99 99-99";

    private String dateTimeStartDesc = " " + PREFIX_DATE_TIME_START + validDateTimeStart;
    private String dateTimeEndDesc = " " + PREFIX_DATE_TIME_END + validDateTimeEnd;

    @Test
    public void parse_validArgsAllFieldsProvided_returnsFindCommand() {
        CombinedPersonPredicate expectedPersonPredicate = generateCombinedPersonPredicate(
                VALID_NAME_AMY, VALID_PHONE_AMY, VALID_EMAIL_AMY, VALID_ADDRESS_AMY, VALID_TAG_THROAT);
        CombinedAppointmentPredicate expectedAppointmentPredicate =
                generateCombinedAppointmentPredicate(VALID_REASON_AMY, validDateTimeStart, validDateTimeEnd);
        boolean isAnyAppointmentFieldSpecified = true;

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, isAnyAppointmentFieldSpecified);
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + TAG_DESC_THROAT + REASON_DESC_AMY + dateTimeStartDesc + dateTimeEndDesc, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + PHONE_DESC_AMY + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + EMAIL_DESC_AMY + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + ADDRESS_DESC_AMY + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + TAG_DESC_THROAT + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + REASON_DESC_AMY + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + dateTimeStartDesc + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + dateTimeEndDesc + PREAMBLE_WHITESPACE, expectedFindCommand);
    }

    @Test
    public void parse_noAppointmentFieldsProvided_returnsFindCommandWithFalseBoolean() {
        CombinedPersonPredicate expectedPersonPredicate = generateCombinedPersonPredicate(
                VALID_NAME_AMY, VALID_PHONE_AMY, VALID_EMAIL_AMY, VALID_ADDRESS_AMY, VALID_TAG_THROAT);
        CombinedAppointmentPredicate expectedAppointmentPredicate = generateEmptyCombinedAppointmentPredicate();
        boolean isAnyAppointmentFieldSpecified = false;

        FindCommand expectedFindCommand =
                new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, isAnyAppointmentFieldSpecified);
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + TAG_DESC_THROAT, expectedFindCommand);
    }

    @Test
    public void parse_somePersonFieldsProvided_success() {
        CombinedAppointmentPredicate expectedAppointmentPredicate = generateEmptyCombinedAppointmentPredicate();
        boolean hasApptFields = false;

        CombinedPersonPredicate expectedPersonPredicate;
        FindCommand expectedFindCommand;

        // Only name and phone provided
        expectedPersonPredicate = generateCombinedPersonPredicate(
                VALID_NAME_AMY, VALID_PHONE_AMY, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY, expectedFindCommand);

        // Only email and address provided
        expectedPersonPredicate = generateCombinedPersonPredicate(
                EMPTY_STRING, EMPTY_STRING, VALID_EMAIL_AMY, VALID_ADDRESS_AMY, EMPTY_STRING);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, EMAIL_DESC_AMY + ADDRESS_DESC_AMY, expectedFindCommand);

        // Only tags provided
        expectedPersonPredicate = generateCombinedPersonPredicateWithOnlyTags(VALID_TAG_THROAT);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, TAG_DESC_THROAT, expectedFindCommand);

        // Multiple tags provided
        expectedPersonPredicate = generateCombinedPersonPredicateWithOnlyTags(VALID_TAG_THROAT, VALID_TAG_EAR);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, TAG_DESC_THROAT + TAG_DESC_EAR, expectedFindCommand);
    }

    @Test
    public void parse_someAppointmentFieldsProvided_success() {
        CombinedPersonPredicate expectedPersonPredicate = generateEmptyCombinedPersonPredicate();
        boolean hasApptFields = true;

        CombinedAppointmentPredicate expectedAppointmentPredicate;
        FindCommand expectedFindCommand;

        // Only reason provided
        expectedAppointmentPredicate = generateCombinedAppointmentPredicateWithOnlyReason(VALID_REASON_AMY);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, REASON_DESC_AMY, expectedFindCommand);

        // Only start date provided
        expectedAppointmentPredicate =
                generateCombinedAppointmentPredicateWithOnlyDateTime(validDateTimeStart, EMPTY_STRING);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, dateTimeStartDesc, expectedFindCommand);

        // Only end date provided
        expectedAppointmentPredicate =
                generateCombinedAppointmentPredicateWithOnlyDateTime(EMPTY_STRING, validDateTimeEnd);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, dateTimeEndDesc, expectedFindCommand);

        // Only start and end date provided
        expectedAppointmentPredicate =
                generateCombinedAppointmentPredicateWithOnlyDateTime(validDateTimeStart, validDateTimeEnd);
        expectedFindCommand = new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, hasApptFields);
        assertParseSuccess(parser, dateTimeStartDesc + dateTimeEndDesc, expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Address, emails and reasons are considered always valid since they don't need to be
        // proper emails/addresses/reasons (E.g finding "google" in john@google.com is fine).

        // Invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // Invalid Phone
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // Invalid Tag
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // Invalid startDate
        assertParseFailure(parser, " " + PREFIX_DATE_TIME_START + invalidDate,
                Appointment.DATE_MESSAGE_CONSTRAINTS);

        // Invalid endDate
        assertParseFailure(parser, " " + PREFIX_DATE_TIME_END + invalidDate,
                Appointment.DATE_MESSAGE_CONSTRAINTS);

        // startDate after endDate -> Invalid
        assertParseFailure(parser,
                " " + PREFIX_DATE_TIME_START + validDateTimeEnd
                        + " " + PREFIX_DATE_TIME_END + validDateTimeStart,
                START_DATE_AFTER_END_DATE);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
