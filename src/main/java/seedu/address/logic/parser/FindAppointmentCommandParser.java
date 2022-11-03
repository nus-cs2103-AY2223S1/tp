package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {
    private Predicate<Name> namePredicate = null;
    private Predicate<MedicalTest> testPredicate = null;
    private Predicate<Slot> slotPredicate = null;
    private Predicate<Doctor> doctorPredicate = null;

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
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

            checkNumberOfPrefixes(PREFIX_NAME, argMultimap);

            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString().trim();

            final String finalPredicateString = createPredicateString(trimmedArgs);

            Predicate<Name> namePredicate = (name -> name.fullName.toLowerCase()
                    .contains(finalPredicateString.toLowerCase()));

            this.namePredicate = namePredicate;
        }

        Optional<Predicate<Name>> finalNamePredicate = Optional.ofNullable(this.namePredicate);

        if (argMultimap.getValue(PREFIX_MEDICAL_TEST).isPresent()) {

            checkNumberOfPrefixes(PREFIX_MEDICAL_TEST, argMultimap);

            String trimmedArgs = ParserUtil.parseMedicalTest(argMultimap.getValue(PREFIX_MEDICAL_TEST).get())
                    .toString().trim();

            final String finalPredicateString = createPredicateString(trimmedArgs);

            Predicate<MedicalTest> testPredicate = (test -> test.toString().toLowerCase()
                    .contains(finalPredicateString.toLowerCase()));

            this.testPredicate = testPredicate;
        }

        Optional<Predicate<MedicalTest>> finalTestPredicate = Optional.ofNullable(this.testPredicate);

        if (argMultimap.getValue(PREFIX_SLOT).isPresent()) {

            checkNumberOfPrefixes(PREFIX_SLOT, argMultimap);

            String trimmedArgs = argMultimap.getValue(PREFIX_SLOT).get().trim();

            if (!trimmedArgs.matches("^[0-9 :-]+$")) {
                throw new ParseException("Only numbers, - and : and spaces are allowed as input for finding by slot");
            }

            Predicate<Slot> slotPredicate = (slot -> slot.toString().contains(trimmedArgs.toLowerCase()));

            this.slotPredicate = slotPredicate;
        }

        Optional<Predicate<Slot>> finalSlotPredicate = Optional.ofNullable(this.slotPredicate);

        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {

            checkNumberOfPrefixes(PREFIX_DOCTOR, argMultimap);

            String trimmedArgs = ParserUtil.parseDoctor(argMultimap.getValue(PREFIX_DOCTOR).get()).toString().trim();

            final String finalPredicateString = createPredicateString(trimmedArgs);

            Predicate<Doctor> doctorPredicate = (doctor -> doctor.toString().toLowerCase()
                    .contains(finalPredicateString.toLowerCase()));

            this.doctorPredicate = doctorPredicate;
        }

        Optional<Predicate<Doctor>> finalDoctorPredicate = Optional.ofNullable(this.doctorPredicate);

        return new FindAppointmentCommand(finalNamePredicate, finalTestPredicate, finalSlotPredicate,
                finalDoctorPredicate);
    }

    /**
     * Returns true if any of the prefixes contain non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the predicate string to be used in the predicate.
     *
     * @param trimmedArgs The trimmed arguments.
     * @return The predicate string.
     */
    public String createPredicateString(String trimmedArgs) {
        String[] keywords = trimmedArgs.split("\\s+");

        String predicateString = keywords[0];
        for (int i = 1; i < keywords.length; i++) {
            predicateString += " " + keywords[i];
        }

        return predicateString;
    }

    /**
     * Checks if the prefix for a field is present more than once in one command.
     *
     * @param prefix The prefix to be checked.
     * @param argMultimap The argument multimap.
     * @throws ParseException If the prefix is present more than once.
     */
    public void checkNumberOfPrefixes(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getAllValues(prefix).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindAppointmentCommand.MESSAGE_USAGE));
        }
    }
}
