package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DoctorContainsKeywordsPredicateAppointment;
import seedu.address.model.appointment.MedicalTestContainsKeywordsPredicateAppointment;
import seedu.address.model.appointment.NameContainsKeywordsPredicateAppointment;
import seedu.address.model.appointment.SlotContainsNumberPredicateAppointment;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {
    private Predicate<Appointment> predicate = null;

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagCommand
     * and returns a FilterNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MEDICAL_TEST,
                        PREFIX_SLOT, PREFIX_DOCTOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MEDICAL_TEST, PREFIX_SLOT, PREFIX_DOCTOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            String predicateName = nameKeywords[0];
            for (int i = 1; i < nameKeywords.length; i++) {
                predicateName += " " + nameKeywords[i];
            }

            this.predicate = new NameContainsKeywordsPredicateAppointment(predicateName);
        }
        if (argMultimap.getValue(PREFIX_MEDICAL_TEST).isPresent()) {
            String trimmedArgs = ParserUtil.parseMedicalTest(argMultimap.getValue(PREFIX_MEDICAL_TEST).get())
                    .toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");

            String predicateMedicalTest = keywords[0];
            for (int i = 1; i < keywords.length; i++) {
                predicateMedicalTest += " " + keywords[i];
            }

            this.predicate = new MedicalTestContainsKeywordsPredicateAppointment(predicateMedicalTest);
        }
        if (argMultimap.getValue(PREFIX_SLOT).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_SLOT).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
            }

            this.predicate = new SlotContainsNumberPredicateAppointment(trimmedArgs);
        }
        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            String trimmedArgs = ParserUtil.parseDoctor(argMultimap.getValue(PREFIX_DOCTOR).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");

            String predicateDoctor = keywords[0];
            for (int i = 1; i < keywords.length; i++) {
                predicateDoctor += " " + keywords[i];
            }

            this.predicate = new DoctorContainsKeywordsPredicateAppointment(predicateDoctor);
        }

        return new FindAppointmentCommand(this.predicate);

    }

    /**
     * Returns true if any of the prefixes contain non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
