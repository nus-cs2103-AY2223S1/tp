package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     * @param name name string to search
     * @param phone phone string to search
     * @param email email string to search
     * @param address address string to search
     * @param tagString tags to search, separated by spaces.
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
     * {@code LocalDateTime}s but a parsable string of LocalDateTime instead.
     *
     * @param reason reason string to search
     * @param startDateTime limit search results to appointments after {@code startDateTime}.
     *                      Empty string denotes no {@code startDateTime} requirement.
     * @param endDateTime limit search results to appointments before {@code endDateTime}.
     *                    Empty string denotes no {@code endDateTime} requirement.
     * @return A {@Code CombinedAppointmentPredicate} with the given reason and date range.
     */
    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicate(String reason, String startDateTime,
                                                                              String endDateTime) {
        LocalDateTime start = parseStartDateTime(startDateTime);
        LocalDateTime end = parseEndDateTime(endDateTime);
        return new CombinedAppointmentPredicate(reason, start, end);
    }

    public static PersonContainsTagsPredicate generatePersonsContainsTagsPredicate(String... tags) {
        List<String> tagList = Arrays.asList(tags);
        return new PersonContainsTagsPredicate(tagList);
    }

    /**
     * Generates a DateTimeWithinRangePredicate without needing to directly provide {@code LocalDateTime}s
     * but a parsable string of LocalDateTime instead.
     *
     * @param startDateTime limit search results to appointments after {@code startDateTime}.
     *                      Empty string denotes no {@code startDateTime} requirement.
     * @param endDateTime limit search results to appointments before {@code endDateTime}.
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
                : LocalDateTime.parse(startDateTime);
    }

    private static LocalDateTime parseEndDateTime(String endDateTime) {
        return endDateTime.isEmpty()
                ? LocalDateTime.MAX
                : LocalDateTime.parse(endDateTime);
    }
}
