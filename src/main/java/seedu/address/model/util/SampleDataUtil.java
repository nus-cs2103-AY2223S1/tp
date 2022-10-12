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
                new Description("Software Engineering Intern"), ApplicationStatus.Applied,
                new AppliedDate("03/09/2022"), getTagSet("Backend")),
            new Internship(new Company("Tiktok"), new Link("careers@tiktok.com"),
                new Description("Software Engineering Intern"), ApplicationStatus.Applied,
                new AppliedDate("11/12/2022"), getTagSet("Frontend")),
            new Internship(new Company("Stripe"), new Link("https://stripe.com/en-sg/jobs/university"),
                new Description("Software Engineering Intern"), ApplicationStatus.Applied,
                new AppliedDate("03/10/2022"), getTagSet("Backend")),
            new Internship(new Company("Meta"), new Link("https://metacareers.com/careerprograms/students"),
                new Description("Software Engineering Intern"), ApplicationStatus.Applied,
                new AppliedDate("05/10/2022"), getTagSet("Frontend")),
            new Internship(new Company("Jane Street"), new Link("https://janestreet.com/join-jane-street"),
                new Description("Software Engineering Intern"), ApplicationStatus.Applied,
                new AppliedDate("11/11/2022"), getTagSet("Quant")),
            new Internship(new Company("Amazon"), new Link("https://amazon.jobs/en/business_categories"),
                new Description("Software Engineering Intern"), ApplicationStatus.Applied,
                new AppliedDate("22/07/2022"), getTagSet("Data"))
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
