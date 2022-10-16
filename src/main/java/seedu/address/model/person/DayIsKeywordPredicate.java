package seedu.address.model.person;

import java.util.function.Predicate;

public class DayIsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public DayIsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public boolean test(Person person) {
        return keyword.equalsIgnoreCase(""); // person session day
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DayIsKeywordPredicate
                && keyword.equals(((DayIsKeywordPredicate) other).keyword));
    }
}
