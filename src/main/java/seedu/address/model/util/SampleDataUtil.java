package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.JeeqTracker;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.client.Address;
import seedu.address.model.client.Company;
import seedu.address.model.client.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code JeeqTracker} with sample data.
 */
public class SampleDataUtil {

    public static Company[] getSampleCompanies() {
        return new Company[] {
            new Company(new Name("Alex Yeoh"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Company(new Name("Bernice Yu"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Company(new Name("Charlotte Oliveiro"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Company(new Name("David Li"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Company(new Name("Irfan Ibrahim"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Company(new Name("Roy Balakrishnan"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyJeeqTracker getSampleJeeqTracker() {
        JeeqTracker sampleAb = new JeeqTracker();
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
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
