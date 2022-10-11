package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new Company("Google"), new Link("https://careers.google.com/students"),
                new Description("careers@google.com"), ApplicationStatus.Applied,
                new AppliedDate("2022-10-30"), getTagSet("friends")),
            new Internship(new Company("Tiktok"), new Link("careers@tiktok.com"),
                new Description("careers@tiktok.com"), ApplicationStatus.Applied,
                new AppliedDate("2022-11-30"), getTagSet("colleagues", "friends")),
            new Internship(new Company("Stripe"), new Link("https://stripe.com/en-sg/jobs/university"),
                new Description("careers@stripe.com"), ApplicationStatus.Applied,
                new AppliedDate("2022-12-30"), getTagSet("neighbours")),
            new Internship(new Company("Meta"), new Link("https://metacareers.com/careerprograms/students"),
                new Description("careers@meta.com"), ApplicationStatus.Applied,
                new AppliedDate("2022-11-05"), getTagSet("family")),
            new Internship(new Company("Jane Street"), new Link("https://janestreet.com/join-jane-street"),
                new Description("careers@janestreet.com"), ApplicationStatus.Applied,
                new AppliedDate("2022-11-10"), getTagSet("classmates")),
            new Internship(new Company("Amazon"), new Link("https://amazon.jobs/en/business_categories"),
                new Description("careers@amazon.com"), ApplicationStatus.Applied,
                new AppliedDate("2022-11-15"), getTagSet("colleagues"))
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
