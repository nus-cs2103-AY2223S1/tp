package seedu.address.model.person;

import java.util.function.Predicate;

public class LocationContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public LocationContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equalsIgnoreCase(person.getLocation().location);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((LocationContainsKeywordsPredicate) other).keyword)); // state check
    }
}
