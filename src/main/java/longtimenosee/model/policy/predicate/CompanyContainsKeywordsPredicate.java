package longtimenosee.model.policy.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.policy.Policy;

/**
 * Tests that a {@code Policy}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsKeywordsPredicate implements Predicate<Policy> {
    private final List<String> keywords;

    public CompanyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Policy policy) {
        for (String keyword : keywords) {
            if (StringUtil.containsWordIgnoreCase(policy.getCompany().value, keyword)) {
                return true;
            }
        }
        return false;
    }
}

