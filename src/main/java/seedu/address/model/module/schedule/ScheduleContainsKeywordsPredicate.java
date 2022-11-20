package seedu.address.model.module.schedule;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Schedule}'s {@code Weekday} matches any of the keywords given.
 */
public class ScheduleContainsKeywordsPredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        boolean containsWeekday = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(schedule.getWeekday().name(), keyword));
        boolean containsModuleCode = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(schedule.getModule(), keyword));
        return containsModuleCode || containsWeekday;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ScheduleContainsKeywordsPredicate)
                && keywords.equals(((ScheduleContainsKeywordsPredicate) other).keywords);
    }



}
