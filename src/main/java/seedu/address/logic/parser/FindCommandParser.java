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
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_APPOINTMENT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicates.CombinedAppointmentPredicate;
import seedu.address.model.person.predicates.CombinedPersonPredicate;
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
                PREFIX_REASON, PREFIX_DATE_TIME_START, PREFIX_DATE_TIME_END, PREFIX_TAG_APPOINTMENT);

        String name = argMultimap.getValue(PREFIX_NAME).orElse("");
        String phone = argMultimap.getValue(PREFIX_PHONE).orElse("");
        String email = argMultimap.getValue(PREFIX_EMAIL).orElse("");
        String address = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
        List<String> personTagList = argMultimap.getAllValues(PREFIX_TAG);
        String reason = argMultimap.getValue(PREFIX_REASON).orElse("");
        String startDateTime =
                StringUtil.removeRedundantSpaces(argMultimap.getValue(PREFIX_DATE_TIME_START).orElse(""));
        String endDateTime =
                StringUtil.removeRedundantSpaces(argMultimap.getValue(PREFIX_DATE_TIME_END).orElse(""));
        List<String> appointmentTagList = argMultimap.getAllValues(PREFIX_TAG_APPOINTMENT);

        checkIfAtLeastOneInputPresent(personTagList, appointmentTagList,
                name, phone, email, address, reason, startDateTime, endDateTime);
        checkIfInputsValid(name, phone, personTagList, startDateTime, endDateTime);

        LocalDateTime parsedStartDateTime = parseDateTime(startDateTime, LocalDateTime.MIN);
        LocalDateTime parsedEndDateTime = parseDateTime(endDateTime, LocalDateTime.MAX);

        checkIfStartDateIsBeforeEndDate(parsedStartDateTime, parsedEndDateTime);

        CombinedPersonPredicate combinedPersonPredicate =
                new CombinedPersonPredicate(name, phone, email, address, personTagList);
        CombinedAppointmentPredicate combinedAppointmentPredicate =
                new CombinedAppointmentPredicate(reason, parsedStartDateTime, parsedEndDateTime, appointmentTagList);

        boolean isUsingAppointmentPredicate =
                isAnyAppointmentFieldSpecified(reason, startDateTime, endDateTime, appointmentTagList);

        return new FindCommand(combinedPersonPredicate, combinedAppointmentPredicate, isUsingAppointmentPredicate);
    }

    private void checkIfAtLeastOneInputPresent(List<String> personTagList, List<String> appointmentTagList,
                                               String... otherFields) throws ParseException {
        List<String> allFields = new ArrayList<>(Arrays.asList(otherFields));
        allFields.addAll(personTagList);
        allFields.addAll(appointmentTagList);

        boolean areAllFieldsEmpty = allFields.stream().allMatch(String::isEmpty);

        if (areAllFieldsEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private void checkIfInputsValid(String name, String phone, List<String> tagList,
                                    String startDateTime, String endDateTime) throws ParseException {
        // Address, emails and reasons are excluded from validity checks since they don't need to be
        // proper emails/addresses/reasons (E.g finding google in john@google.com is fine).
        if (!name.isEmpty() && !Name.isValidName(name)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        String containsOnlyNumbersRegex = "\\d+";
        if (!phone.isEmpty() && !phone.matches(containsOnlyNumbersRegex)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }

        for (String tag : tagList) {
            if (!Tag.isValidTagName(tag)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }

        boolean areBothDatesValid = (startDateTime.isEmpty() || Appointment.isValidDateTime(startDateTime))
                && (endDateTime.isEmpty() || Appointment.isValidDateTime(endDateTime));
        if (!areBothDatesValid) {
            throw new ParseException(Appointment.DATE_MESSAGE_CONSTRAINTS);
        }
    }

    private LocalDateTime parseDateTime(String startDateTime, LocalDateTime defaultValue) {
        return startDateTime.isEmpty()
                ? defaultValue
                : LocalDateTime.parse(startDateTime, Appointment.DATE_FORMATTER);
    }

    private void checkIfStartDateIsBeforeEndDate(LocalDateTime parsedStartDateTime,
                                                 LocalDateTime parsedEndDateTime) throws ParseException {
        if (parsedStartDateTime.isAfter(parsedEndDateTime)) {
            throw new ParseException(Messages.START_DATE_AFTER_END_DATE);
        }
    }

    private boolean isAnyAppointmentFieldSpecified(String reason, String startDateTime, String endDateTime,
                                                   List<String> appointmentTagList) {
        boolean isAllAppointmentFieldsEmpty = !reason.isEmpty() || !startDateTime.isEmpty()
                || !endDateTime.isEmpty() || !appointmentTagList.isEmpty();
        return isAllAppointmentFieldsEmpty;
    }
}
