package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.UpcomingAppointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String nextOfKin} into an {@code NextOfKin}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nextOfKin} is invalid.
     */
    public static NextOfKin parseNextOfKin(String nextOfKin) throws ParseException {
        requireNonNull(nextOfKin);
        String trimmedNextOfKin = nextOfKin.trim();
        if (!NextOfKin.isValidNextOfKin(trimmedNextOfKin)) {
            throw new ParseException(NextOfKin.MESSAGE_CONSTRAINTS);
        }
        return new NextOfKin(trimmedNextOfKin);
    }

    /**
     * Parses a {@code String patientType} into an {@code PatientType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code patientType} is invalid.
     */
    public static PatientType parsePatientType(String patientType) throws ParseException {
        requireNonNull(patientType);
        String trimmedPatientType = patientType.trim();
        if (!PatientType.isValidPatientType(trimmedPatientType)) {
            throw new ParseException(PatientType.MESSAGE_CONSTRAINTS);
        }
        PatientTypes pt = PatientTypes.parsePatientType(trimmedPatientType);
        if (pt == null) {
            throw new ParseException(PatientType.MESSAGE_CONSTRAINTS);
        }
        return new PatientType(pt);
    }

    /**
     * Parses a {@code String hospitalWing} into an {@code hospitalWing}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code hospital wing} is invalid.
     */
    public static HospitalWing parseHospitalWing(String hospitalWing) throws ParseException {
        requireNonNull(hospitalWing);
        String trimmedHospitalWing = hospitalWing.trim();
        if (!HospitalWing.isValidHospitalWing(trimmedHospitalWing)) {
            throw new ParseException(HospitalWing.MESSAGE_CONSTRAINTS);
        }
        return new HospitalWing(trimmedHospitalWing);
    }

    /**
     * Parses a {@code String floorNumber} into an {@code FloorNumber}.
     * String will be converted to an Integer.
     *
     * @throws ParseException if the given {@code floorNumber} is invalid.
     */
    public static FloorNumber parseFloorNumber(String floorNumber) throws ParseException {
        requireNonNull(floorNumber);
        String trimmedFloorNumber = floorNumber.trim();
        Integer parsedFloorNumber;
        try {
            parsedFloorNumber = Integer.valueOf(trimmedFloorNumber);
        } catch (NumberFormatException nfe) {
            throw new ParseException(FloorNumber.MESSAGE_CONSTRAINTS);
        }
        if (!FloorNumber.isValidFloorNumber(parsedFloorNumber)) {
            throw new ParseException(FloorNumber.MESSAGE_CONSTRAINTS);
        }
        return new FloorNumber(parsedFloorNumber);
    }

    /**
     * Parses a {@code String wardNumber} into an {@code WardNumber}.
     * String will be converted to an Integer.
     *
     * @throws ParseException if the given {@code wardNumber} is invalid.
     */
    public static WardNumber parseWardNumber(String wardNumber) throws ParseException {
        requireNonNull(wardNumber);
        String trimmedWardNumber = wardNumber.trim();
        if (!WardNumber.isValidWardNumber(trimmedWardNumber)) {
            throw new ParseException(WardNumber.MESSAGE_CONSTRAINTS);
        }
        return new WardNumber(trimmedWardNumber);
    }

    /**
     * Parses a {@code String medication} into a {@code Medication}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code medication} is invalid.
     */
    public static Medication parseMedication(String medication) throws ParseException {
        requireNonNull(medication);
        String trimmedMedication = medication.trim();
        if (!Medication.isValidMedicationName(trimmedMedication)) {
            throw new ParseException(Medication.MESSAGE_CONSTRAINTS);
        }
        return new Medication(trimmedMedication);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Medication> parseMedications(Collection<String> medications) throws ParseException {
        requireNonNull(medications);
        final Set<Medication> medicationSet = new HashSet<>();
        for (String medicationName : medications) {
            medicationSet.add(parseMedication(medicationName));
        }
        return medicationSet;
    }

    /**
     * Parses {@code String upcomingAppointment} into a {@code UpcomingAppointment}.
     */
    public static UpcomingAppointment parseUpcomingAppointment(String upcomingAppointment) throws ParseException {
        if (upcomingAppointment == null) {
            return new UpcomingAppointment((LocalDate) null);
        } else if (!UpcomingAppointment.isValidDate(upcomingAppointment)) {
            throw new ParseException(UpcomingAppointment.MESSAGE_CONSTRAINTS);
        }
        return new UpcomingAppointment(upcomingAppointment);
    }
}

