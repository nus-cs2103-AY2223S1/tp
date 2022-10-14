package longtimenosee.model.policy.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.policy.Policy;

/**
 * Tests that a {@code Policy}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Policy> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Policy policy) {
        for (String keyword : keywords) {
            if (StringUtil.containsWordIgnoreCase(policy.getTitle().fullTitle, keyword)) {
                return true;
            }
        }
        return false;
    }
}
