package seedu.workbook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;
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
                new Email("alexyeoh@example.com"),
                new Stage("HR Interview"),
                getTagSet("friends")),
            new Internship(
                new Company("Hudson River Trading"),
                new Role("Algorithm Engineer"),
                new Email("berniceyu@example.com"),
                new Stage("Team Lead Interview"),
                getTagSet("colleagues", "friends")),
            new Internship(
                new Company("Shopee"),
                new Role("iOS Engineer"),
                new Email("charlotte@example.com"),
                new Stage("Online Coding Assessment"),
                getTagSet("neighbours")),
            new Internship(
                new Company("Visa"),
                new Role("Backend Engineer"),
                new Email("lidavid@example.com"),
                new Stage("Technical Interview"),
                getTagSet("family")),
            new Internship(
                new Company("Binance"),
                new Role("Blockchain Engineer"),
                new Email("irfan@example.com"),
                new Stage("Application Sent"),
                getTagSet("classmates")),
            new Internship(
                new Company("Optiver"),
                new Role("God Engineer"),
                new Email("royb@example.com"),
                new Stage("Rejected"),
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
