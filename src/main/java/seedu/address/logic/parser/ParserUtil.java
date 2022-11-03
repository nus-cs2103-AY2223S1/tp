package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.appointment.Location;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;
import seedu.address.model.util.MaximumSortedList;

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
        requireNonNull(oneBasedIndex);
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code personAppointmentIndex} into an {@code Index} and returns the appointment index.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseAppointmentIndex(String personAppointmentIndex) throws ParseException {
        requireNonNull(personAppointmentIndex);
        String trimmedAppointmentIndex = personAppointmentIndex.trim();
        String[] splitStr = trimmedAppointmentIndex.split("\\.");

        if (splitStr.length != 2 || !StringUtil.isNonZeroUnsignedInteger(splitStr[1])) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(splitStr[1]));
    }

    /**
     * Parses {@code personAppointmentIndex} into an {@code Index} and returns the person index.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parsePersonIndex(String personAppointmentIndex) throws ParseException {
        requireNonNull(personAppointmentIndex);
        String trimmedAppointmentIndex = personAppointmentIndex.trim();
        String[] splitStr = trimmedAppointmentIndex.split("\\.");

        if (splitStr.length != 2 || !StringUtil.isNonZeroUnsignedInteger(splitStr[0])) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(splitStr[0]));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim().replaceAll(" +", " ");
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code List<Name>}.
     */
    public static List<Name> parseAllSpaceSeparatedNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final List<Name> nameList = new ArrayList<>();
        for (String name: names) {
            nameList.add(parseName(name));
        }
        return nameList;
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
     * Parses {@code Collection<String> phones} into a {@code List<Phone>}.
     */
    public static List<Phone> parseAllSpaceSeparatedPhone(Collection<String> phones) throws ParseException {
        requireNonNull(phones);
        final List<Phone> phoneList = new ArrayList<>();
        for (String phone: phones) {
            phoneList.add(parsePhone(phone));
        }
        return phoneList;
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String dateAndTime} and a {@code String Location} into an {@code Appointment}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Appointment parseAppointment(String dateAndTime, String location) throws ParseException {
        requireNonNull(dateAndTime);
        requireNonNull(location);
        DateTime appointmentDateTime = parseDateTime(dateAndTime);
        Location appointmentLocation = parseLocation(location);
        if (!Appointment.isValidAppointment(appointmentDateTime, appointmentLocation)) {
            throw new ParseException(Appointment.MESSAGE_CONSTRAINTS);
        }
        return new Appointment(appointmentDateTime, appointmentLocation);
    }

    /**
     * Parses {@code Collection<String> datesAndTimes} into a {@code Set<Appointment>}.
     */
    public static MaximumSortedList<Appointment> parseAppointmentsIntoSortedList(Collection<Appointment> appointments) {
        requireNonNull(appointments);
        final MaximumSortedList<Appointment> appointmentSet =
                new MaximumSortedList<>(MAXIMUM_NUM_OF_APPOINTMENTS);
        for (Appointment appointment : appointments) {
            appointmentSet.add(appointment);
        }
        return appointmentSet;
    }

    /**
     * Parses a {@code String dateAndTime} into an {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static DateTime parseDateTime(String dateAndTime) {
        requireNonNull(dateAndTime);
        String trimmedDateAndTime = dateAndTime.trim();
        LocalDateTime localDateTime = DateTimeParser.parseLocalDateTimeFromString(trimmedDateAndTime);
        return new DateTime(localDateTime);
    }
    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String income} into an {@code IncomeLevel}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static IncomeLevel parseIncomeLevel(String income) throws ParseException {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!IncomeLevel.isValidIncome(income)) {
            throw new ParseException(IncomeLevel.MESSAGE_CONSTRAINTS);
        }
        return new IncomeLevel(trimmedIncome);
    }

    /**
     * Parses a {@code String monthly} into an {@code Monthly}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Monthly parseMonthly(String monthly) throws ParseException {
        requireNonNull(monthly);
        String trimmedMonthly = monthly.trim();
        if (!Monthly.isValidMonthly(monthly)) {
            throw new ParseException(Monthly.MESSAGE_CONSTRAINTS);
        }
        return new Monthly(trimmedMonthly);
    }

    /**
     * Parses a {@code String riskTag} into an {@code RiskTag}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static RiskTag parseRiskTag(String riskTag) throws ParseException {
        requireNonNull(riskTag);
        String trimmedRiskTag = riskTag.trim();
        if (!RiskTag.isValidRiskTagName(trimmedRiskTag)) {
            throw new ParseException(RiskTag.MESSAGE_CONSTRAINTS);
        }
        return new RiskTag(trimmedRiskTag);
    }

    /**
     * Parses a {@code String clientTag} into an {@code ClientTag}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ClientTag parseClientTag(String clientTag) throws ParseException {
        requireNonNull(clientTag);
        String trimmedClientTag = clientTag.trim();
        if (!ClientTag.isValidClientTagName(trimmedClientTag)) {
            throw new ParseException(ClientTag.MESSAGE_CONSTRAINTS);
        }
        return new ClientTag(trimmedClientTag);
    }

    /**
     * Parses {@code Collection<String> riskTags} into a {@code List<RiskTag>}.
     */
    public static List<RiskTag> parseAllSpaceSeparatedRiskTag(Collection<String> riskTags) throws ParseException {
        requireNonNull(riskTags);
        final List<RiskTag> riskTagList = new ArrayList<>();
        for (String riskTag: riskTags) {
            riskTagList.add(parseRiskTag(riskTag.toUpperCase()));
        }
        return riskTagList;
    }

    /**
     * Parses a {@code String planTag} into an {@code PlanTag}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static PlanTag parsePlanTag(String planTag) throws ParseException {
        requireNonNull(planTag);
        String trimmedPlanTag = planTag.trim();
        if (!PlanTag.isValidPlanTagName(trimmedPlanTag)) {
            throw new ParseException(PlanTag.MESSAGE_CONSTRAINTS);
        }
        return new PlanTag(trimmedPlanTag);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code List<Name>}.
     */
    public static List<PlanTag> parseAllSpaceSeparatedPlanTags(Collection<String> planTags) throws ParseException {
        requireNonNull(planTags);
        final List<PlanTag> planTagList = new ArrayList<>();
        for (String planTag: planTags) {
            planTagList.add(parsePlanTag(planTag));
        }
        return planTagList;
    }
    /**
     * Parses {@code Collection<String> names} into a {@code List<Name>}.
     */
    public static List<ClientTag> parseAllSpaceSeparatedClientTags(Collection<String> clientTags)
            throws ParseException {
        requireNonNull(clientTags);
        final List<ClientTag> clientTagList = new ArrayList<>();
        for (String clientTag: clientTags) {
            clientTagList.add(parseClientTag(clientTag));
        }
        return clientTagList;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static NormalTag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!NormalTag.isValidTagName(trimmedTag)) {
            throw new ParseException(NormalTag.MESSAGE_CONSTRAINTS);
        }
        return new NormalTag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<NormalTag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<NormalTag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> normalTags} into a {@code List<NormalTag>}.
     */
    public static List<NormalTag> parseAllSpaceSeparatedNormalTags(Collection<String> normalTags)
            throws ParseException {
        requireNonNull(normalTags);
        final List<NormalTag> normalTagList = new ArrayList<>();
        for (String normalTag: normalTags) {
            normalTagList.add(parseTag(normalTag));
        }
        return normalTagList;
    }

    /**
     * Parses {@code Collection<String> monetaryValues} into a {@code List<String>}.
     */
    public static List<String> parseMonetaryValues(Collection<String> monetaryValues)
            throws ParseException {
        requireNonNull(monetaryValues);
        final List<String> incomeLevelList = new ArrayList<>(monetaryValues);
        for (int i = 0; i < incomeLevelList.size(); i++) {
            if (i == 0) {
                parseIncomeLevel("$" + incomeLevelList.get(i).substring(1));
            } else {
                parseIncomeLevel("$" + incomeLevelList.get(i));
            }
        }
        return incomeLevelList;
    }
}
