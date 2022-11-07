package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Session} matches the day keyword given.
 */
public class DayIsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public DayIsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getSessionDays().stream().anyMatch(day -> day.equalsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DayIsKeywordPredicate
                && keyword.equals(((DayIsKeywordPredicate) other).keyword));
    }
}
