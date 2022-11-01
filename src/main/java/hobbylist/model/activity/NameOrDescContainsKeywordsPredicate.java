package hobbylist.model.activity;

import java.util.List;
import java.util.function.Predicate;

import hobbylist.commons.util.StringUtil;

/**
 * Tests that an {@code Activity}'s {@code Name} matches any of the keywords given.
 */
public class NameOrDescContainsKeywordsPredicate implements Predicate<Activity> {
    private final List<String> keywords;

    public NameOrDescContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        // name or description matches the keywords
        if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                activity.getName().fullName, keyword)
                || StringUtil.containsWordIgnoreCase(activity.getDescription().value, keyword))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameOrDescContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameOrDescContainsKeywordsPredicate) other).keywords)); // state check
    }

}
