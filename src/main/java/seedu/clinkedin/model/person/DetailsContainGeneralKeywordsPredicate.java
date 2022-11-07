package seedu.clinkedin.model.person;

import java.util.List;

import seedu.clinkedin.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the general keywords given.
 */
public class DetailsContainGeneralKeywordsPredicate implements DetailsContainKeywordsPredicate {

    private final List<String> keywords;

    /**
     * Constructor for DetailsContainGeneralKeywordsPredicate.
     *
     * @param keywords List of keywords to search for.
     */
    public DetailsContainGeneralKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(person.getDetailsAsString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainGeneralKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainGeneralKeywordsPredicate) other).keywords)); // state check
    }

}
