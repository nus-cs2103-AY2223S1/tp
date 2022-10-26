package seedu.application.model.application;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that an {@code Application}'s {@code Interview} is within 1 week from now.
 * The current date and time reference is pre-configured in this stub and does not follow the system clock.
 */
public class UpcomingInterviewPredicateStub implements Predicate<Application> {

    /**
     * Returns true if interview date and time is within one week from pre-configured date-time.
     */
    private static boolean isWithinOneWeekFromNow(LocalDateTime interviewDateTime) {
        LocalDateTime current = LocalDateTime.of(2022, 10, 26, 18, 0);
        LocalDateTime oneWeekFromNow = current.plusWeeks(1);
        return interviewDateTime.compareTo(current) >= 0 && interviewDateTime.compareTo(oneWeekFromNow) <= 0;
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
                || other instanceof UpcomingInterviewPredicateStub; // instanceof handles nulls
    }

}
