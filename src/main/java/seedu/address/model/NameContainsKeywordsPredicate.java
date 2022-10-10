package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Person) {
            Person person = (Person) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        }

        assert (t instanceof TuitionClass);
        @SuppressWarnings("unchecked")
        TuitionClass tuitionClass = (TuitionClass) t;
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tuitionClass.getName().name, keyword));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
