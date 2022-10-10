package seedu.workbook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Address;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Phone;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code WorkBook} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(
                new Company("Jane Street"),
                new Role("Software Engineer"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Internship(
                new Company("Hudson River Trading"),
                new Role("Algorithm Engineer"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Internship(
                new Company("Shopee"),
                new Role("iOS Engineer"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Internship(
                new Company("Visa"),
                new Role("Backend Engineer"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Internship(
                new Company("Binance"),
                new Role("Blockchain Engineer"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Internship(
                new Company("Optiver"),
                new Role("God Engineer"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyWorkBook getSampleWorkBook() {
        WorkBook sampleWb = new WorkBook();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleWb.addInternship(sampleInternship);
        }
        return sampleWb;
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
