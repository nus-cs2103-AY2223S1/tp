package seedu.pennywise.model.entry;

import static java.util.Objects.requireNonNull;

import java.time.YearMonth;
import java.util.function.Predicate;

/**
 * Tests that a {@code Entry}'s {@code year-month} matches the given year-month.
 */
public class EntryInYearMonthPredicate implements Predicate<Entry> {
    private final YearMonth yearMonth;

    /**
     * Constructs a new predicate to test if an entry's year-month matches the given non-null {@code yearMonth}.
     */
    public EntryInYearMonthPredicate(YearMonth yearMonth) {
        requireNonNull(yearMonth);
        this.yearMonth = yearMonth;
    }

    @Override
    public boolean test(Entry entry) {
        return entry.getYearMonth().equals(yearMonth);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryInYearMonthPredicate // instanceof handles nulls
                && yearMonth.equals(((EntryInYearMonthPredicate) other).yearMonth)); // state check
    }

}
