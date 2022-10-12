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
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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


        Optional<String> startDateTime = argMultimap.getValue(PREFIX_DATE_TIME_START).orElse("");
        Optional<String> endDateTime = argMultimap.getValue(PREFIX_DATE_TIME_END).orElse("");

        Optional<String name = argMultimap.getValue(PREFIX_NAME).orElse("");
        Optional<String phone = argMultimap.getValue(PREFIX_PHONE).orElse("");
        Optional<String email = argMultimap.getValue(PREFIX_EMAIL).orElse("");
        Optional<String address = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
        List<String> tagList = argMultimap.getAllValues(PREFIX_TAG);
        String reason = argMultimap.getValue(PREFIX_EMAIL).orElse("");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        Predicate<Person> personPredicate = generatePersonPredicate(name, phone, email, address, tagList);
        Predicate<Appointment> appointmentPredicate = generateAppointmentPredicate(reason, startDateTime, endDateTime);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(personPredicate, appointmentPredicate);
    }

    private Predicate<Person> generatePersonPredicate(Optional<String> name, Optional<String> phone,
                                                      Optional<String> email, Optional<String> address,
                                                      List<String> tagList) {
        List<Predicate<Person>> personPredicates = new ArrayList<>();

        name.ifPresent(s -> personPredicates.add(new NameContainsSequencePredicate(s)));
        phone.ifPresent(s -> personPredicates.add(new PhoneContainsSequencePredicate(s)));
        email.ifPresent(s -> personPredicates.add(new EmailContainsSequencePredicate(s)));
        address.ifPresent(s -> personPredicates.add(new AddressContainsSequencePredicate(s)));
        if (!tagList.isEmpty()) {
            personPredicates.add(new PersonContainsTagsPredicate(tagList));
        }

        return personPredicates.stream().reduce(PREDICATE_SHOW_ALL_PERSONS, Predicate::and);
    }

    private Predicate<Appointment> generateAppointmentPredicate(Optional<String> reason,
                                                                Optional<String> startDateTime,
                                                                Optional<String> endDateTime) {
        List<Predicate<Appointment>> appointmentPredicates = new ArrayList<>();
        reason.ifPresent(s -> appointmentPredicates.add(new ReasonContainsSequencePredicate(s)));
        startDateTime.ifPresent(s -> appointmentPredicates.add(new DateTimeWithinRangePredicate(startDateTime, endDateTime)));
    }
}
