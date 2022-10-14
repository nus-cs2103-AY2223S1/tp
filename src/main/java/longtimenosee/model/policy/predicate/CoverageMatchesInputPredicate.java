package longtimenosee.model.policy.predicate;

import java.util.function.Predicate;

import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Policy;

/**
 * Tests that a {@code Policy}'s {@code Coverage} matches the input.
 */
public class CoverageMatchesInputPredicate implements Predicate<Policy> {
    private final String input;

    public CoverageMatchesInputPredicate(String input) {
        this.input = input;
    }

    @Override
    public boolean test(Policy policy) {
        for (Coverage coverage : policy.getCoverages()) {
            if (coverage.coverageType.equals(input)) {
                return true;
            }
        }
        return false;
    }
}

