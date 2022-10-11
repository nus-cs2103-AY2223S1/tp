package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.keyword.KeywordList;
import seedu.address.model.tag.Tag;

/**
 * Tests if a Person's data matches with the Keyword on the specified
 * category.
 */
public class ContainsKeywordsPredicate implements Predicate<Person> {
    private final KeywordList keywords;
    private final FindableCategory category;

    /**
     * Constructs a predicate with the given keywords and category.
     *
     * @param keywords a list of keywords.
     * @param category the category to test.
     */
    public ContainsKeywordsPredicate(KeywordList keywords, FindableCategory category) {
        requireNonNull(category);
        requireNonNull(keywords);

        this.keywords = keywords;
        this.category = category;
    }

    @Override
    public boolean test(Person person) {
        switch (category) {
        case COMPANY_NAME:
            return testName(person.getName());

        case DATE:
            return testDate(person.getDate());

        case POSITION:
            return testPosition(person.getPosition());

        case APPLICATION_PROCESS:
            return testApplicationProcess(person.getApplicationProcess());

        case TAGS:
            return testTags(person.getTags());

        default:
            // Should not fall to default case
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && category.equals(((ContainsKeywordsPredicate) other).category)) // state check
                && keywords.equals(((ContainsKeywordsPredicate) other).keywords);
    }

    private boolean testName(Name name) {
        return keywords.isAnyKeywordFound(name.toString());
    }

    private boolean testDate(Date date) {
        return keywords.isAnyKeywordFound(date.toInputFormat());
    }

    private boolean testPosition(Position position) {
        return keywords.isAnyKeywordFound(position.toString());
    }

    private boolean testApplicationProcess(ApplicationProcess applicationState) {
        return keywords.isAnyKeywordFound(applicationState.toString());
    }

    private boolean testTags(Set<Tag> tags) {
        boolean result = false;

        for (Tag t : tags) {
            result = result || keywords.isAnyKeywordFound(t.getTagName());
        }

        return result;
    }
}
