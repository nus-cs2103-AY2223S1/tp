package longtimenosee.model.policy.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Policy;

/**
 * Tests that a {@code Policy}'s {@code Coverage} matches the input.
 */
public class CoverageMatchesInputPredicate implements Predicate<Policy> {
    private final List<String> keywords;

    /**
     * Constructs a CoverageMatchesInputPredicate object, which consists of a keywords input.
     *
     * @param keywords is the keywords input by the user to be compared.
     */
    public CoverageMatchesInputPredicate(List<String> keywords) {
        assert !keywords.isEmpty();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Policy policy) {
        for (String keyword : keywords) {
            for (Coverage coverage : policy.getCoverages()) {
                if (StringUtil.containsWordIgnoreCase(coverage.coverageType, keyword)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof CoverageMatchesInputPredicate) {
                return keywords.equals(((CoverageMatchesInputPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}

