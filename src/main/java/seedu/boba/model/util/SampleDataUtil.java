package seedu.boba.model.util;

import seedu.boba.model.BobaBot;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.tag.Tag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code BobaBot} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSamplePersons() {
        return new Customer[]{
                new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new BirthdayMonth("10"), new Reward("2300"),
                        getTagSet("bronze", "warning")),
                new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new BirthdayMonth("11"), new Reward("6900"),
                        getTagSet("gold")),
                new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new BirthdayMonth("10"), new Reward("12345"),
                        getTagSet("diamond")),
                new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new BirthdayMonth("6"), new Reward("0"),
                        getTagSet("member", "banned")),
                new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new BirthdayMonth("12"), new Reward("4000"),
                        getTagSet("silver")),
                new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new BirthdayMonth("9"), new Reward("8000"),
                        getTagSet("platinum"))
        };
    }

    public static ReadOnlyBobaBot getSampleBobaBot() {
        BobaBot sampleAb = new BobaBot();
        for (Customer sampleCustomer : getSamplePersons()) {
            sampleAb.addPerson(sampleCustomer);
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

}
