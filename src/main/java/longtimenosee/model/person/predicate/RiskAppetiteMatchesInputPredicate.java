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

    public RiskAppetiteMatchesInputPredicate(String riskAppetite) {
        this.riskAppetite = riskAppetite;
    }

    @Override
    public boolean test(Person person) {
        RiskLevel personRiskLevel = person.getRiskAppetite().getRiskLevel();
        RiskLevel inputRiskLevel = RiskAppetite.parseRiskLevel(riskAppetite);
        return personRiskLevel.equals(inputRiskLevel);
    }
}
