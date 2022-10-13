package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     * Provides a simple way to generate a {@code CombinedAppointmentPredicate} without needing to directly provide a
     * {@code LocalDateTime} but a string of LocalDateTime instead.
     *
     * @param reason reason string to search
     * @param startDateTime limit search results to appointments after {@code startDateTime}.
     * @param endDateTime limit search results to appointments before {@code endDateTime}.
     * @return
     */
    public static CombinedAppointmentPredicate generateCombinedAppointmentPredicate(String reason, String startDateTime,
                                                                              String endDateTime) {
        LocalDateTime start = startDateTime.isEmpty() ? LocalDateTime.MIN : LocalDateTime.parse(startDateTime);
        LocalDateTime end = endDateTime.isEmpty() ? LocalDateTime.MAX : LocalDateTime.parse(endDateTime);
        return new CombinedAppointmentPredicate(reason, start, end);
    }
}
