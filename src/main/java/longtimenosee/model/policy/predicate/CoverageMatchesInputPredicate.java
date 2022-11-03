package longtimenosee.model.policy.predicate;

import java.util.List;
import java.util.Set;
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
        boolean[] areMatch = new boolean[keywords.size()];
        Set<Coverage> coverages = policy.getCoverages();
        for (int i = 0; i < keywords.size(); i++) {
            checkKeywordAgainstCoverages(coverages, keywords.get(i), areMatch, i);
        }
        return areAllTrue(areMatch);
    }

    private static void checkKeywordAgainstCoverages(Set<Coverage> coverages, String keyword, boolean[] areMatch,
                                                     int index) {
        for (Coverage coverage : coverages) {
            if (StringUtil.containsWordIgnoreCase(coverage.coverageType, keyword)) {
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
            if (other instanceof CoverageMatchesInputPredicate) {
                return keywords.equals(((CoverageMatchesInputPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}

