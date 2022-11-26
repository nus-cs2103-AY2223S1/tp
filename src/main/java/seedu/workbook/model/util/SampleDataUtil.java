package seedu.workbook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.DateTime;
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
                new Email("hr@janestreet.com"),
                new Stage("HR Interview"),
                new DateTime("11-Dec-2022 15:30"),
                getTagSet("OCaml"),
                getTagSet("remote")),
            new Internship(
                new Company("Hudson River Trading"),
                new Role("Algorithm Engineer"),
                new Email("teamlead@hrt.com"),
                new Stage("Interview with Team Lead"),
                new DateTime("11-Dec-2022 18:30"),
                getTagSet("Python"),
                getTagSet("quant")),
            new Internship(
                new Company("Shopee"),
                new Role("Android Engineer"),
                Email.EMPTY_EMAIL,
                new Stage("Online Assessment"),
                new DateTime("12-Dec-2022 18:30"),
                getTagSet("Kotlin"),
                getTagSet("backup")),
            new Internship(
                new Company("Visa"),
                new Role("Backend Engineer"),
                Email.EMPTY_EMAIL,
                new Stage("Technical Interview"),
                new DateTime("15-Oct-2022 16:00"),
                getTagSet("Java"),
                getTagSet("payments")),
            new Internship(
                new Company("Binance"),
                new Role("Blockchain Engineer"),
                new Email("hr@binance.com"),
                new Stage("Application Sent"),
                DateTime.EMPTY_DATETIME,
                getTagSet("Solidity"),
                getTagSet("crypto")),
            new Internship(
                new Company("Optiver"),
                new Role("Software Engineer"),
                Email.EMPTY_EMAIL,
                new Stage("Rejected"),
                new DateTime("20-Oct-2022 10:20"),
                getTagSet("Rust"),
                getTagSet("trading"))
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
