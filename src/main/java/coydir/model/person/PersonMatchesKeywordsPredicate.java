package coydir.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} matches all of the keywords given, for name, position, and/or department.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private final String keywordName;
    private final String keywordPosition;
    private final String keywordDepartment;

    /**
     * Constructor for {@Code PersonMatchesKeywordsPredicate}.
     * Though in functionality there has to be at least one keyword (no need for all 3),
     * to construct an instance all 3 arguments must be provided.
     *
     * @param keywordName Keyword for name search.
     * @param keywordPosition Keyword for position search.
     * @param keywordDepartment Keyword for department search.
     */
    public PersonMatchesKeywordsPredicate(
            String keywordName, String keywordPosition, String keywordDepartment) {
        this.keywordName = keywordName.toLowerCase();
        this.keywordPosition = keywordPosition.toLowerCase();
        this.keywordDepartment = keywordDepartment.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String lowerCaseName = person.getName().fullName.toLowerCase();
        String lowerCasePosition = person.getPosition().value.toLowerCase();
        String lowerCaseDepartment = person.getDepartment().value.toLowerCase();
        return lowerCaseName.contains(keywordName)
                && lowerCasePosition.contains(keywordPosition)
                && lowerCaseDepartment.contains(keywordDepartment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesKeywordsPredicate // instanceof handles nulls
                && keywordName.equals(((PersonMatchesKeywordsPredicate) other).keywordName) // state check
                && keywordPosition.equals(((PersonMatchesKeywordsPredicate) other).keywordPosition)
                && keywordDepartment.equals(((PersonMatchesKeywordsPredicate) other).keywordDepartment));
    }

}
