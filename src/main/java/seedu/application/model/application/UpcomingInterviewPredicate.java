package seedu.application.model.application;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that an {@code Application}'s {@code Interview} is within 1 week from now.
 */
public class UpcomingInterviewPredicate implements Predicate<Application> {

    /**
     * Returns true if interview date and time is within one week from current date-time,
     * using system clock in the default time-zone.
     */
    public static boolean isWithinOneWeekFromNow(LocalDateTime interviewDateTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekFromNow = now.plusWeeks(1);
        return interviewDateTime.compareTo(now) >= 0 && interviewDateTime.compareTo(oneWeekFromNow) <= 0;
    }

    @Override
    public boolean test(Application application) {
        Optional<LocalDateTime> interviewDateTime = application.getInterviewDateTime();
        return !application.isArchived() && interviewDateTime.isPresent()
                && isWithinOneWeekFromNow(interviewDateTime.get());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof UpcomingInterviewPredicate; // instanceof handles nulls
    }

}
