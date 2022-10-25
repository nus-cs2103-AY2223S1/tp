package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Person;
import longtimenosee.model.policy.AssignedPolicy;

/**
 * Tests that a {@code AssignedPolicy}'s {@code Policy} {@code Company} for a given {@code Person} matches the
 * keyword given.
 */
public class PolicyCompanyMatchesInputPredicate implements Predicate<Person> {
    private final String input;

    /**
     * Constructs a PolicyCompanyMatchesInputPredicate object, which consists of an input.
     *
     * @param input is the input by the user to be compared.
     */
    public PolicyCompanyMatchesInputPredicate(String input) {
        assert input.length() != 0;
        this.input = input;
    }

    @Override
    public boolean test(Person person) {
        for (AssignedPolicy assignedPolicy : person.getAssignedPolicies()) {
            if (assignedPolicy.getPolicy().getCompany().value.equals(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof PolicyCompanyMatchesInputPredicate) {
                return input.equals(((PolicyCompanyMatchesInputPredicate) other).input);
            } else {
                return false;
            }
        }
    }
}
