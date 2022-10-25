package longtimenosee.model.person.predicate;

import java.util.List;
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
        boolean[] isMatch = new boolean[keywords.size()];
        for (int i = 0; i < keywords.size(); i++) {
            for (Tag tag : person.getTags()) {
                if (StringUtil.containsWordIgnoreCase(tag.tagName, keywords.get(i))) {
                    isMatch[i] = true;
                    break;
                }
            }
        }
        return isAllTrue(isMatch);
    }

    private boolean isAllTrue(boolean[] arr) {
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

