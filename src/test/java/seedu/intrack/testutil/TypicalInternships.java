package seedu.intrack.testutil;

import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALICE = new InternshipBuilder().withName("Alice Pauline")
            .withPosition("Software Engineer").withStatus("Progress").withEmail("alice@example.com")
            .withWebsite("https://careers.google.com/").withTasks("Application submitted /at 19-10-2022 11:38")
            .withSalary("100000").withTags("friends").build();
    public static final Internship BENSON = new InternshipBuilder().withName("Benson Meier")
            .withPosition("Data Analyst").withStatus("Progress").withEmail("johnd@example.com")
            .withWebsite("https://careers.google.com/").withTasks("Application submitted /at 20-10-2022 12:00")
            .withSalary("125000").withTags("owesMoney", "friends").build();
    public static final Internship CARL = new InternshipBuilder().withName("Carl Kurz")
            .withPosition("Frontend Engineer").withStatus("Offered").withEmail("heinz@example.com")
            .withWebsite("https://careers.google.com/").withTasks("Application submitted /at 19-10-2022 11:38")
            .withSalary("150000").build();
    public static final Internship DANIEL = new InternshipBuilder().withName("Daniel Meier")
            .withPosition("Backend Engineer").withStatus("Progress").withEmail("cornelia@example.com")
            .withWebsite("https://careers.google.com/")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withSalary("175000")
            .withTags("friends").build();
    public static final Internship ELLE = new InternshipBuilder().withName("Elle Meyer")
            .withPosition("Full Stack Engineer").withStatus("Offered").withEmail("werner@example.com")
            .withWebsite("https://careers.google.com/")
            .withTasks("Application submitted /at 25-10-2022 08:30", "Technical Interview /at 30-10-2022 09:00")
            .withSalary("200000")
            .build();
    public static final Internship FIONA = new InternshipBuilder().withName("Fiona Kunz")
            .withPosition("Cyber Security Analyst").withStatus("Offered").withEmail("lydia@example.com")
            .withWebsite("https://careers.google.com/")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withSalary("225000").build();
    public static final Internship GEORGE = new InternshipBuilder().withName("George Best")
            .withPosition("Algorithm Engineer").withStatus("Progress").withEmail("anna@example.com")
            .withWebsite("https://careers.google.com/")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withSalary("250000").build();

    // Manually added
    public static final Internship HOON = new InternshipBuilder().withName("Hoon Meier")
            .withPosition("Product Designer").withStatus("Progress").withEmail("stefan@example.com")
            .withWebsite("https://careers.google.com/").withSalary("300000").build();
    public static final Internship IDA = new InternshipBuilder().withName("Ida Mueller")
            .withPosition("Data Engineer").withStatus("Progress").withEmail("hans@example.com")
            .withWebsite("https://careers.google.com/").withSalary("275000").build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship AAPL = new InternshipBuilder().withName(VALID_NAME_AAPL)
            .withPosition(VALID_POSITION_AAPL).withStatus(VALID_STATUS_AAPL)
            .withEmail(VALID_EMAIL_AAPL).withWebsite(VALID_WEBSITE_AAPL)
            .withSalary(VALID_SALARY_AAPL).withTags(VALID_TAG_REMOTE).build();
    public static final Internship MSFT = new InternshipBuilder().withName(VALID_NAME_MSFT)
            .withPosition(VALID_POSITION_MSFT).withStatus(VALID_STATUS_MSFT).withSalary(VALID_SALARY_MSFT)
            .withEmail(VALID_EMAIL_MSFT).withWebsite(VALID_WEBSITE_MSFT)
            .withSalary(VALID_SALARY_MSFT)
            .withTags(VALID_TAG_REMOTE, VALID_TAG_URGENT).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code InTrack} with all the typical internships.
     */
    public static InTrack getTypicalInTrack() {
        InTrack it = new InTrack();
        for (Internship internship : getTypicalInternships()) {
            it.addInternship(internship);
        }
        return it;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
