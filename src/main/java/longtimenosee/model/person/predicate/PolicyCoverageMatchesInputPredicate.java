package longtimenosee.model.person.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.Coverage;

/**
 * Tests that a {@code AssignedPolicy}'s {@code Policy} {@code Coverage} for a given {@code Person} matches any of the
 * keywords given.
 */
public class PolicyCoverageMatchesInputPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a PolicyCoverageMatchesInputPredicate object, which consists of a keywords input.
     *
     * @param keywords is the keywords input by the user to be compared.
     */
    public PolicyCoverageMatchesInputPredicate(List<String> keywords) {
        assert !keywords.isEmpty();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean[] areMatch = new boolean[keywords.size()];
        List<Coverage> coverages = getCoverages(person);
        for (int i = 0; i < keywords.size(); i++) {
            checkKeywordAgainstCoverages(coverages, keywords.get(i), areMatch, i);
        }
        return areAllTrue(areMatch);
    }

    private static void checkKeywordAgainstCoverages(List<Coverage> coverages, String keyword, boolean[] areMatch,
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

    private static List<Coverage> getCoverages(Person person) {
        List<Coverage> coverages = new ArrayList<Coverage>();
        for (AssignedPolicy assignedPolicy : person.getAssignedPolicies()) {
            coverages.addAll(assignedPolicy.getPolicy().getCoverages());
        }
        return coverages;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof PolicyCoverageMatchesInputPredicate) {
                return keywords.equals(((PolicyCoverageMatchesInputPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}
