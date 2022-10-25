package coydir.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Department} matches any of the keywords given.
 */
public class DepartmentContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public DepartmentContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String lowerCaseDepartment = person.getDepartment().value.toLowerCase();
        return lowerCaseDepartment.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DepartmentContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((DepartmentContainsKeywordPredicate) other).keyword)); // state check
    }

}
