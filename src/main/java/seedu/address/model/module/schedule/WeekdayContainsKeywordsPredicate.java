package seedu.address.model.module.schedule;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Schedule}'s {@code Weekday} matches any of the keywords given.
 */
public class WeekdayContainsKeywordsPredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public WeekdayContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(schedule.getWeekday().name(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof WeekdayContainsKeywordsPredicate)
                && keywords.equals(((WeekdayContainsKeywordsPredicate) other).keywords);
    }
}
