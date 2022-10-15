package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.predicates.CombinedAppointmentPredicate;
import seedu.address.model.person.predicates.CombinedPersonPredicate;
import seedu.address.model.person.predicates.DateTimeWithinRangePredicate;
import seedu.address.model.person.predicates.PersonContainsTagsPredicate;

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
     * @return a {@code CombinedPersonPredicate}
     */
    public static CombinedPersonPredicate generateCombinedPersonPredicate(String name, String phone, String email,
                                                                    String address, String tagString) {
        List<String> tagList = tagString.isEmpty()
                ? Collections.emptyList()
                : Arrays.asList(tagString.split("\\s+"));
        return new CombinedPersonPredicate(name, phone, email, address, tagList);
    }

    /**
     * Provides a simple way to generate a {@code CombinedAppointmentPredicate} without needing to directly provide
     * {@code LocalDateTime}s but a string parsable accrding to the DateTimeFormatter in {@code Appointment} instead.
     *
     * @param reason reason string to test
     * @param startDateTime Tests for appointments after {@code startDateTime}.
     *                      Empty string denotes no {@code startDateTime} requirement.
     * @param endDateTime Tests for appointments before {@code endDateTime}.
     *                    Empty string denotes no {@code endDateTime} requirement.
     * @return A {@Code CombinedAppointmentPredicate} with the given reason and date range.
     */
    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicate(String reason, String startDateTime,
                                                                              String endDateTime) {
        LocalDateTime start = parseStartDateTime(startDateTime);
        LocalDateTime end = parseEndDateTime(endDateTime);
        return new CombinedAppointmentPredicate(reason, start, end);
    }

    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyName(String name) {
        return generateCombinedPersonPredicate(name, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyPhone(String phone) {
        return generateCombinedPersonPredicate(EMPTY_STRING, phone, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyEmail(String email) {
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, email, EMPTY_STRING, EMPTY_STRING);
    }

    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyAddress(String address) {
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, address, EMPTY_STRING);
    }

    public static CombinedPersonPredicate generateCombinedPersonPredicateWithOnlyTags(String tagString) {
        return generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, tagString);
    }

    /**
     * Provides a simple way to generate a {@code PersonContainsTagsPredicate} without needing to directly provide
     * a list of tags but an argument of strings instead.
     *
     * @param tags List of tags to test.
     * @return A PersonContainsTagsPredicate that tests if the tags inputted exist on a {@code Person}.
     */
    public static PersonContainsTagsPredicate generatePersonsContainsTagsPredicate(String... tags) {
        List<String> tagList = Arrays.asList(tags);
        return new PersonContainsTagsPredicate(tagList);
    }

    /**
     * Generates a DateTimeWithinRangePredicate without needing to directly provide {@code LocalDateTime}s
     * but a parsable string of LocalDateTime instead.
     *
     * @param startDateTime Tests for appointments after {@code startDateTime}.
     *                      Empty string denotes no {@code startDateTime} requirement.
     * @param endDateTime Tests for appointments before {@code endDateTime}.
     *                    Empty string denotes no {@code endDateTime} requirement.
     * @return {@Code DateTimeWithinRangePredicate} with the given reason and date range.
     */
    public static DateTimeWithinRangePredicate generateDateTimeWIthinRangePredicate(
            String startDateTime, String endDateTime) {
        LocalDateTime start = parseStartDateTime(startDateTime);
        LocalDateTime end = parseEndDateTime(endDateTime);
        return new DateTimeWithinRangePredicate(start, end);
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
