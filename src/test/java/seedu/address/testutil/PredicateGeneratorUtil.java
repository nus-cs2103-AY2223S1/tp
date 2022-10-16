package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.predicates.CombinedAppointmentPredicate;
import seedu.address.model.person.predicates.CombinedPersonPredicate;

/**
 * A helper class consisting of methods to generate predicates more easily for testing without needing to create
 * classes to pass into the predicate constructor as arguments.
 */
public class PredicateGeneratorUtil {
    /**
     * Provides a simple way to generate a {@code CombinedPersonPredicate} without needing to make a list from scratch.
     *
     * @param name name string to test
     * @param phone phone string to test
     * @param email email string to test
     * @param address address string to test
     * @param tagString tags to test, separated by spaces.
     * @return a {@code CombinedPersonPredicate} with the given inputs.
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicate(String name, String phone, String email,
                                                                    String address, String tagString) {
        List<String> tagList = tagString.isEmpty()
                ? Collections.emptyList()
                : Arrays.asList(tagString.split("\\s+"));
        return new CombinedPersonPredicate(name, phone, email, address, tagList);
    }

    /**
     * Creates a {@code CombinedPersonPredicate} with all empty fields.
     *
     * @return Creates a {@code CombinedPersonPredicate} with all empty fields.
     */
    public static CombinedPersonPredicate generateEmptyCombinedPersonPredicate() {
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedPersonPredicate} with only name present and other fields empty.
     *
     * @param name name string to test
     * @return a {@code CombinedPersonPredicate} with only name present and other fields empty.
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyName(String name) {
        return generateCombinedPersonPredicate(name, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedPersonPredicate} with only phone present and other fields empty.
     *
     * @param phone phone string to test
     * @return a {@code CombinedPersonPredicate} with only phone present and other fields empty.
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyPhone(String phone) {
        return generateCombinedPersonPredicate(EMPTY_STRING, phone, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedPersonPredicate} with only email present and other fields empty.
     *
     * @param email email string to test
     * @return a {@code CombinedPersonPredicate} with only email present and other fields empty.
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyEmail(String email) {
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, email, EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedPersonPredicate} with only address present and other fields empty.
     *
     * @param address address string to test
     * @return a {@code CombinedPersonPredicate} with only address present and other fields empty.
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyAddress(String address) {
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, address, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedPersonPredicate} with only tags present and other fields empty.
     *
     * @param tags tags to test. Tags can be passed in separately or as a single string separated by spaces.
     * @return a {@code CombinedPersonPredicate} with only tags present and other fields empty.
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyTags(String... tags) {
        String tagString = String.join(" ", tags);
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, tagString);
    }

    /**
     * Provides a simple way to generate a {@code CombinedAppointmentPredicate} without needing to directly provide
     * {@code LocalDateTime}s but a string parsable according to the DateTimeFormatter in {@code Appointment} instead.
     *
     * @param reason reason string to test
     * @param startDateTime Tests for appointments after {@code startDateTime}.
     *                      Empty string denotes no {@code startDateTime} requirement.
     * @param endDateTime Tests for appointments before {@code endDateTime}.
     *                    Empty string denotes no {@code endDateTime} requirement.
     * @return a {@Code CombinedAppointmentPredicate} with the given reason and date range.
     */
    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicate(
            String reason, String startDateTime, String endDateTime) {

        LocalDateTime start = parseStartDateTime(startDateTime);
        LocalDateTime end = parseEndDateTime(endDateTime);
        return new CombinedAppointmentPredicate(reason, start, end);
    }

    /**
     * Creates a {@code CombinedAppointmentPredicate} with all empty fields.
     *
     * @return a {@code CombinedAppointmentPredicate} with all empty fields.
     */
    public static CombinedAppointmentPredicate generateEmptyCombinedAppointmentPredicate() {
        return generateCombinedAppointmentPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedAppointmentPredicate} with only reason present and other fields empty.
     *
     * @param reason reason to test
     * @return a {@code CombinedAppointmentPredicate} with only reason present and other fields empty.
     */
    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicateWithOnlyReason(String reason) {
        return generateCombinedAppointmentPredicate(reason, EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Creates a {@code CombinedAppointmentPredicate} with only dateTime fields present and other fields empty.
     * Use strings parsable according to the DateTimeFormatter in {@code Appointment} as input.
     *
     * @param startDateTime Tests for appointments after {@code startDateTime}.
     *                      Empty string denotes no {@code startDateTime} requirement.
     * @param endDateTime Tests for appointments before {@code endDateTime}.
     *                    Empty string denotes no {@code endDateTime} requirement.
     * @return a {@code CombinedAppointmentPredicate} with only dateTime fields present and other fields empty.
     */
    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicateWithOnlyDateTime(
            String startDateTime, String endDateTime) {
        return generateCombinedAppointmentPredicate(EMPTY_STRING, startDateTime, endDateTime);
    }

    /**
     * Creates a {@code CombinedAppointmentPredicate} with only dateTime fields present and other fields empty.
     * Uses {@code LocalDateTime} as inputs.
     *
     * @param startDateTime Tests for appointments after {@code startDateTime}.
     * @param endDateTime Tests for appointments before {@code endDateTime}.
     * @return a {@code CombinedAppointmentPredicate} with only dateTime fields present and other fields empty.
     */

    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicateWithOnlyDateTime(
            LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new CombinedAppointmentPredicate(EMPTY_STRING, startDateTime, endDateTime);
    }

    private static LocalDateTime parseStartDateTime(String startDateTime) {
        return startDateTime.isEmpty()
                ? LocalDateTime.MIN
                : LocalDateTime.parse(startDateTime, Appointment.DATE_FORMATTER);
    }

    private static LocalDateTime parseEndDateTime(String endDateTime) {
        return endDateTime.isEmpty()
                ? LocalDateTime.MAX
                : LocalDateTime.parse(endDateTime, Appointment.DATE_FORMATTER);
    }
}
