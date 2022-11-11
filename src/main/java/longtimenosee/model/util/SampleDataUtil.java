package longtimenosee.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import longtimenosee.model.AddressBook;
import longtimenosee.model.ReadOnlyAddressBook;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Birthday;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Income;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.Phone;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.Commission;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.PolicyDate;
import longtimenosee.model.policy.Premium;
import longtimenosee.model.policy.Title;
import longtimenosee.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                new Birthday("2015-05-05"),
                new Income("100.0"),
                new RiskAppetite("H")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"),
                new Birthday("2015-05-05"),
                new Income("100.0"),
                new RiskAppetite("H")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"),
                new Birthday("2015-05-05"),
                new Income("100.0"),
                new RiskAppetite("H")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"),
                new Birthday("2015-05-05"),
                new Income("100.0"),
                new RiskAppetite("H")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                new Birthday("2015-05-05"),
                new Income("100.0"),
                new RiskAppetite("H")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                new Birthday("2015-05-05"),
                new Income("100.0"),
                new RiskAppetite("H")),
        };
    }

    public static Policy[] getSamplePolicies() {
        return new Policy[] {
            new Policy(new Title("PruShield"), new Company("PRU"), new Commission("10% 7.5% 2%"),
                    getCoverageSet("LIFE", "HEALTH")),
            new Policy(new Title("Secure Flexi Term"), new Company("AIA"), new Commission("7% 7% 1%"),
                    getCoverageSet("HEALTH")),
            new Policy(new Title("TermLife Solitaire"), new Company("NTU"), new Commission("10% 7.5% 2%"),
                    getCoverageSet("LIFE", "HEALTH")),
            new Policy(new Title("Manulife Invest Ready Wealth"), new Company("MNF"), new Commission("8% 3.5% 1%"),
                    getCoverageSet("INVESTMENT", "LIFE")),
            new Policy(new Title("AXA Wealth Accelerate"), new Company("AXA"), new Commission("4% 3.5% 3%"),
                    getCoverageSet("INVESTMENT", "LIFE")),
        };
    }

    public static AssignedPolicy[] getSampleAssignedPolicies() {
        return new AssignedPolicy[] {
            new AssignedPolicy(
                new Policy(new Title("PruShield"), new Company("PRU"), new Commission("10% 7.5% 2%"),
                        getCoverageSet("LIFE", "HEALTH")), new Premium("200"),
                        new PolicyDate("2010-10-10"), new PolicyDate("2021-12-12")),
            new AssignedPolicy(
                new Policy(new Title("Secure Flexi Term"), new Company("AIA"), new Commission("7% 7% 1%"),
                        getCoverageSet("HEALTH")), new Premium("300"),
                        new PolicyDate("2011-01-01"), new PolicyDate("2019-01-01")),
            new AssignedPolicy(
                new Policy(new Title("TermLife Solitaire"), new Company("NTU"), new Commission("10% 7.5% 2%"),
                        getCoverageSet("LIFE", "HEALTH")), new Premium("400"),
                        new PolicyDate("2001-05-05"), new PolicyDate("2018-05-05")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Policy samplePolicy : getSamplePolicies()) {
            sampleAb.addPolicy(samplePolicy);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a coverage set containing the list of strings given.
     */
    public static Set<Coverage> getCoverageSet(String... strings) {
        return Arrays.stream(strings)
                .map(Coverage::new)
                .collect(Collectors.toSet());
    }

}
