package longtimenosee.testutil;

import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMMISSION_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMMISSION_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMPANY_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMPANY_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COVERAGE_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COVERAGE_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TITLE_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TITLE_PRULIFE;
import static longtimenosee.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import longtimenosee.model.AddressBook;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPolicies {

    public static final Policy PRUSHIELD = new PolicyBuilder().withTitle("PruShield")
            .withCompany("PRU").withCommission("10% 7.5% 2%")
            .withCoverages("LIFE", "HEALTH").build();
    public static final Policy FLEXI = new PolicyBuilder().withTitle("Secure Flexi Term")
            .withCompany("AIA").withCommission("7% 7% 1%")
            .withCoverages("HEALTH").build();
    public static final Policy TERMLIFE = new PolicyBuilder().withTitle("TermLife Solitaire")
            .withCompany("NTU").withCommission("10% 7.5% 2%")
            .withCoverages("LIFE", "HEALTH").build();
    public static final Policy INVEST_READY = new PolicyBuilder().withTitle("Manulife Invest Ready Wealth")
            .withCompany("MNF").withCommission("8% 3.5% 1%")
            .withCoverages("INVESTMENT", "LIFE").build();
    public static final Policy WEALTH = new PolicyBuilder().withTitle("AXA Wealth Accelerate")
            .withCompany("AXA").withCommission("4% 3.5% 3%")
            .withCoverages("INVESTMENT", "LIFE").build();


    // Manually added - Policy's details found in {@code CommandTestUtil}
    public static final Policy PRULIFE = new PolicyBuilder().withTitle(VALID_TITLE_PRULIFE)
            .withCompany(VALID_COMPANY_PRULIFE).withCommission(VALID_COMMISSION_PRULIFE)
            .withCoverages(VALID_COVERAGE_PRULIFE).build();
    public static final Policy MANUEXTRA = new PolicyBuilder().withTitle(VALID_TITLE_MANUEXTRA)
            .withCompany(VALID_COMPANY_MANUEXTRA).withCommission(VALID_COMMISSION_MANUEXTRA)
            .withCoverages(VALID_COVERAGE_MANUEXTRA).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPolicies() {} // prevents instantiation

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

    public static List<Policy> getTypicalPolicies() {
        return new ArrayList<>(Arrays.asList(PRUSHIELD, FLEXI, TERMLIFE, INVEST_READY, WEALTH));
    }
}
