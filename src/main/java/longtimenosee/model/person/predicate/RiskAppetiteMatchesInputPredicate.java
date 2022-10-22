package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Person;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.person.RiskAppetite.RiskLevel;

/**
 * Tests that a {@code Person}'s {@code RiskAppetite} is the same as the input provided.
 */
public class RiskAppetiteMatchesInputPredicate implements Predicate<Person> {
    private final String riskAppetite;

    /**
     * Constructs a RiskAppetiteMatchesInputPredicate object, which consists of a risk appetite input.
     *
     * @param riskAppetite is the input by the user to be compared.
     */
    public RiskAppetiteMatchesInputPredicate(String riskAppetite) {
        assert riskAppetite.length() == 1;
        this.riskAppetite = riskAppetite;
    }

    @Override
    public boolean test(Person person) {
        RiskLevel personRiskLevel = person.getRiskAppetite().getRiskLevel();
        RiskLevel inputRiskLevel = RiskAppetite.parseRiskLevel(riskAppetite);
        return personRiskLevel.equals(inputRiskLevel);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof RiskAppetiteMatchesInputPredicate) {
                return riskAppetite.equals(((RiskAppetiteMatchesInputPredicate) other).riskAppetite);
            } else {
                return false;
            }
        }
    }
}
