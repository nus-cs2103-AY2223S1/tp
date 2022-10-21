package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    /**
     * Constructor for the tag predicate.
     * @param keywords
     */
    public TagContainsKeywordsPredicate(String keywords) {
        System.out.println(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        List<String> tagKeywords = Arrays.asList(keywords.split(" "));
        Set<Tag> tags = person.getTags();
        for (Tag t: tags) {
            if (tagKeywords.stream().anyMatch(t.getTagName()::equalsIgnoreCase)) {
                HiddenPredicateSingleton.addToHiddenPersonList(person);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
