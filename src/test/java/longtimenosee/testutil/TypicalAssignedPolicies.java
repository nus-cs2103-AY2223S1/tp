package longtimenosee.testutil;

import static longtimenosee.testutil.TypicalPersons.getTypicalPersons;
import static longtimenosee.testutil.TypicalPolicies.getTypicalPolicies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import longtimenosee.model.AddressBook;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.Policy;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAssignedPolicies {

    public static final AssignedPolicy PRUSHIELD = new AssignedPolicyBuilder().withPolicy(
            new PolicyBuilder().withTitle("PruShield")
                    .withCompany("PRU").withCommission("10% 7.5% 2%")
                    .withCoverages("LIFE", "HEALTH").build()).withPremium("200")
                    .withStartDate("2010-10-10")
                    .withEndDate("2021-12-12").build();

    public static final AssignedPolicy FLEXI = new AssignedPolicyBuilder().withPolicy(
            new PolicyBuilder().withTitle("Secure Flexi Term")
                    .withCompany("AIA").withCommission("7% 7% 1%")
                    .withCoverages("HEALTH").build()).withPremium("300")
                    .withStartDate("2011-01-01")
                    .withEndDate("2019-01-01").build();

    public static final AssignedPolicy TERMLIFE = new AssignedPolicyBuilder().withPolicy(
            new PolicyBuilder().withTitle("TermLife Solitaire")
                    .withCompany("NTU").withCommission("10% 7.5% 2%")
                    .withCoverages("LIFE", "HEALTH").build()).withPremium("400")
                    .withStartDate("2001-05-05")
                    .withEndDate("2018-05-05").build();

    private TypicalAssignedPolicies() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Policy policy : getTypicalPolicies()) {
            ab.addPolicy(policy);
        }
        return ab;
    }

    public static List<AssignedPolicy> getTypicalAssignedPolicies() {
        return new ArrayList<>(Arrays.asList(PRUSHIELD, FLEXI, TERMLIFE));
    }
}
