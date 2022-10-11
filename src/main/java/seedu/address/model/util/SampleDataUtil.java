package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Email;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new Company("Google"), new Link("87438807"), new Email("careers@google.com"),
                ApplicationStatus.Applied, new AppliedDate("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Internship(new Company("Tiktok"), new Link("99272758"), new Email("careers@tiktok.com"),
                ApplicationStatus.Applied, new AppliedDate("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Internship(new Company("Stripe"), new Link("93210283"), new Email("careers@stripe.com"),
                ApplicationStatus.Applied, new AppliedDate("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Internship(new Company("Meta"), new Link("91031282"), new Email("careers@meta.com"),
                ApplicationStatus.Applied, new AppliedDate("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Internship(new Company("Jane Street"), new Link("92492021"), new Email("careers@janestreet.com"),
                ApplicationStatus.Applied, new AppliedDate("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Internship(new Company("Amazon"), new Link("92624417"), new Email("careers@amazon.com"),
                ApplicationStatus.Applied, new AppliedDate("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleAb.addInternship(sampleInternship);
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
