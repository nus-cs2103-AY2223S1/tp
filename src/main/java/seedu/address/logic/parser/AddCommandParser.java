package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD_NUMBER;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.UpcomingAppointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NEXT_OF_KIN,
                        PREFIX_PATIENT_TYPE, PREFIX_HOSPITAL_WING, PREFIX_FLOOR_NUMBER,
                        PREFIX_WARD_NUMBER, PREFIX_MEDICATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_NEXT_OF_KIN, PREFIX_PATIENT_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        NextOfKin nextOfKin = ParserUtil.parseNextOfKin(argMultimap.getValue(PREFIX_NEXT_OF_KIN).get());
        PatientType patientType = ParserUtil.parsePatientType(argMultimap.getValue(PREFIX_PATIENT_TYPE).get());
        Set<Medication> medicationList = ParserUtil.parseMedications(argMultimap.getAllValues(PREFIX_MEDICATION));

        if (patientType.isInpatient() && !arePrefixesPresent(argMultimap, PREFIX_HOSPITAL_WING, PREFIX_FLOOR_NUMBER,
                PREFIX_WARD_NUMBER)) {
            throw new ParseException(String.format(PatientType.DEPENDENCY_CONSTRAINTS, AddCommand.MESSAGE_USAGE));
        }
        HospitalWing hospitalWing = null;
        FloorNumber floorNumber = null;
        WardNumber wardNumber = null;

        if (patientType.isInpatient()) {
            hospitalWing = ParserUtil.parseHospitalWing(argMultimap.getValue(PREFIX_HOSPITAL_WING).get());
            floorNumber = ParserUtil.parseFloorNumber(argMultimap.getValue(PREFIX_FLOOR_NUMBER).get());
            wardNumber = ParserUtil.parseWardNumber(argMultimap.getValue(PREFIX_WARD_NUMBER).get());
        }

        UpcomingAppointment upcomingAppointment = null;

        Person person = new Person(name, phone, email, nextOfKin, patientType, hospitalWing,
                floorNumber, wardNumber, medicationList, new ArrayList<>(), upcomingAppointment);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
