package longtimenosee.model.policy.predicate;

import java.util.function.Predicate;

import longtimenosee.model.policy.Policy;

/**
 * Tests that a {@code Policy}'s {@code Company} matches the input.
 */
public class CompanyMatchesInputPredicate implements Predicate<Policy> {
    private final String input;

    public CompanyMatchesInputPredicate(String input) {
        this.input = input;
    }

    @Override
    public boolean test(Policy policy) {
        return policy.getCompany().value.equals(input);
    }
}

