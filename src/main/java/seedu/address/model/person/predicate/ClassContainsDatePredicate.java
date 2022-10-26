package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Class} matches the date given.
 */
public class ClassContainsDatePredicate implements Predicate<Person> {
    private final String keyword;

    public ClassContainsDatePredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWord(person.getAClass().classDateTime, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassContainsDatePredicate // instanceof handles nulls
                && keyword.equals(((ClassContainsDatePredicate) other).keyword)); // state check
    }

}
