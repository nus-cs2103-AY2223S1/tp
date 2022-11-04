package longtimenosee.model.person.predicate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.person.Person;
import longtimenosee.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a TagContainsKeywordsPredicate object, which consists of a keywords input.
     *
     * @param keywords is the keywords input by the user to be compared.
     */
    public TagContainsKeywordsPredicate(List<String> keywords) {
        assert !keywords.isEmpty();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean[] areMatch = new boolean[keywords.size()];
        Set<Tag> tags = person.getTags();
        for (int i = 0; i < keywords.size(); i++) {
            checkKeywordAgainstTags(tags, keywords.get(i), areMatch, i);
        }
        return areAllTrue(areMatch);
    }

    private static void checkKeywordAgainstTags(Set<Tag> tags, String keyword, boolean[] areMatch, int index) {
        for (Tag tag : tags) {
            if (StringUtil.containsWordIgnoreCase(tag.tagName, keyword)) {
                areMatch[index] = true;
                return;
            }
        }
    }
    private static boolean areAllTrue(boolean[] arr) {
        for (boolean bool : arr) {
            if (!bool) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof TagContainsKeywordsPredicate) {
                return keywords.equals(((TagContainsKeywordsPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}

