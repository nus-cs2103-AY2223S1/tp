package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CombinedAppointmentPredicate;
import seedu.address.logic.commands.CombinedPersonPredicate;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AddressContainsSequencePredicate;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTimeWithinRangePredicate;
import seedu.address.model.person.EmailContainsSequencePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsSequencePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.person.PhoneContainsSequencePredicate;
import seedu.address.model.person.ReasonContainsSequencePredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String name = "John";
        String phone = "1234";
        String email = "abcd";
        String address = "clementi";
        String tag = "throat";
        String reason = "cough";
        String dateTimeStart = "2022-12-13T12:12";
        String dateTimeEnd = "2025-12-13T12:12";

        CombinedPersonPredicate expectedPersonPredicate =
                new CombinedPersonPredicate(name, phone, email, address, Collections.singletonList(tag));
        CombinedAppointmentPredicate expectedAppointmentPredicate = new CombinedAppointmentPredicate(reason,
                LocalDateTime.parse(dateTimeStart), LocalDateTime.parse(dateTimeEnd));
        boolean isAnyAppointmentFieldSpecified = true;

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(expectedPersonPredicate, expectedAppointmentPredicate, true);
        assertParseSuccess(parser, " n/John p/1234 e/abcd a/clementi t/throat r/cough "
                + "ds/2022-12-13 12:12 de/2025-12-13 12:12", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ John p/ 1234 e/abcd   a/clementi  t/  throat r/  cough  "
                + "ds/  2022-12-13 12:12 de/2025-12-13  12:12 ", expectedFindCommand);
    }
}
