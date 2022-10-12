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
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.CombinedAppointmentPredicate;
import seedu.address.logic.commands.CombinedPersonPredicate;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AddressContainsSequencePredicate;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTimeWithinRangePredicate;
import seedu.address.model.person.EmailContainsSequencePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsSequencePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsSequencePredicate;
import seedu.address.model.person.ReasonContainsSequencePredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                PREFIX_REASON, PREFIX_DATE_TIME_START, PREFIX_DATE_TIME_END);

        String name = argMultimap.getValue(PREFIX_NAME).orElse("");
        String phone = argMultimap.getValue(PREFIX_PHONE).orElse("");
        String email = argMultimap.getValue(PREFIX_EMAIL).orElse("");
        String address = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
        List<String> tagList = argMultimap.getAllValues(PREFIX_TAG);
        String reason = argMultimap.getValue(PREFIX_REASON).orElse("");
        String startDateTime =
                StringUtil.removeRedundantSpaces(argMultimap.getValue(PREFIX_DATE_TIME_START).orElse(""));
        String endDateTime =
                StringUtil.removeRedundantSpaces(argMultimap.getValue(PREFIX_DATE_TIME_END).orElse(""));

        checkIfInputsValid(name, phone, address, tagList, reason, startDateTime, endDateTime);

        LocalDateTime parsedStartDateTime = startDateTime.isEmpty()
                ? LocalDateTime.MIN
                : LocalDateTime.parse(startDateTime, Appointment.DATE_FORMATTER);
        LocalDateTime parsedEndDateTime = endDateTime.isEmpty()
                ? LocalDateTime.MAX
                : LocalDateTime.parse(endDateTime, Appointment.DATE_FORMATTER);

        checkIfStartDateIsBeforeEndDate(parsedStartDateTime, parsedEndDateTime);

        CombinedPersonPredicate combinedPersonPredicate = new CombinedPersonPredicate(name, phone, email, address, tagList);
        CombinedAppointmentPredicate combinedAppointmentPredicate =
                new CombinedAppointmentPredicate(reason, parsedStartDateTime, parsedEndDateTime);

        boolean isAnyAppointmentFieldSpecified =
                !reason.isEmpty() || !startDateTime.isEmpty() || !endDateTime.isEmpty();

        return new FindCommand(combinedPersonPredicate, combinedAppointmentPredicate, isAnyAppointmentFieldSpecified);
    }

    private void checkIfInputsValid(String name, String phone, String address, List<String> tagList, String reason,
                                    String startDateTime, String endDateTime) throws ParseException {

        boolean areAllFieldsEmpty = name.isEmpty() && phone.isEmpty() && address.isEmpty() && tagList.isEmpty()
                && reason.isEmpty() && startDateTime.isEmpty() && endDateTime.isEmpty();
        if (areAllFieldsEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!name.isEmpty() && !Name.isValidName(name)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        String containsOnlyNumbersRegex = "\\d+";
        if (!phone.isEmpty() && !phone.matches(containsOnlyNumbersRegex)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }

        if (!address.isEmpty() && !Address.isValidAddress(address)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }

        for (String tag : tagList) {
            if (!Tag.isValidTagName(tag)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }

        if (!reason.isEmpty() && !Appointment.isValidReason(reason)) {
            throw new ParseException(Appointment.REASON_MESSAGE_CONSTRAINTS);
        }

        boolean areBothDatesValid = (startDateTime.isEmpty() || Appointment.isValidDateTime(startDateTime))
                && (endDateTime.isEmpty() || Appointment.isValidDateTime(endDateTime));
        if (!areBothDatesValid) {
            throw new ParseException(Appointment.DATE_MESSAGE_CONSTRAINTS);
        }
    }

    private void checkIfStartDateIsBeforeEndDate(LocalDateTime parsedStartDateTime,
                                                 LocalDateTime parsedEndDateTime) throws ParseException {
        if (parsedStartDateTime.isAfter(parsedEndDateTime)) {
            throw new ParseException(Messages.START_DATE_AFTER_END_DATE);
        }
    }
}
