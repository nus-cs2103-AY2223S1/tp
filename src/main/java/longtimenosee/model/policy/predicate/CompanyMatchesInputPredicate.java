package longtimenosee.model.policy.predicate;

import java.util.function.Predicate;

import longtimenosee.model.policy.Policy;

/**
 * Tests that a {@code Policy}'s {@code Company} matches the input.
 */
public class CompanyMatchesInputPredicate implements Predicate<Policy> {
    private final String input;

    /**
     * Constructs a CompanyMatchesInputPredicate object, which consists of an input.
     *
     * @param input is the input by the user to be compared.
     */
    public CompanyMatchesInputPredicate(String input) {
        assert input.length() != 0;
        this.input = input;
    }

    @Override
    public boolean test(Policy policy) {
        return policy.getCompany().value.equals(input);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof CompanyMatchesInputPredicate) {
                return input.equals(((CompanyMatchesInputPredicate) other).input);
            } else {
                return false;
            }
        }
    }
}

