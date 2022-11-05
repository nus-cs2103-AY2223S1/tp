package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new IncomeLevel("3000"),
                        new Monthly("200"), new RiskTag("HIGH"), new PlanTag("Savings Plan"),
                    new ClientTag("POTENTIAL"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new IncomeLevel("30000"),
                    new Monthly("100"), new RiskTag("LOW"), new PlanTag("Savings Plan"),
                    new ClientTag("CURRENT"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new IncomeLevel("1000000"),
                    new Monthly("400"), new RiskTag("MEDIUM"), new PlanTag("Savings Plan"),
                    new ClientTag("POTENTIAL"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new IncomeLevel("1"),
                    new Monthly("100"), new RiskTag("HIGH"), new PlanTag("Savings Plan"),
                    new ClientTag("CURRENT"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new IncomeLevel("20000"),
                    new Monthly("300"), new RiskTag("LOW"), new PlanTag("Savings Plan"),
                    new ClientTag("CURRENT"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new IncomeLevel("40000"), new Monthly("250"),
                new RiskTag("HIGH"), new PlanTag("Savings Plan"), new ClientTag("POTENTIAL"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<NormalTag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(NormalTag::new)
                .collect(Collectors.toSet());
    }
}
