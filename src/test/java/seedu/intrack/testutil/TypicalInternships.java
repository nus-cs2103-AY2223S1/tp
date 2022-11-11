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

    public static final Internship GOOG = new InternshipBuilder().withName("Google")
            .withPosition("Software Engineer").withStatus("Progress").withEmail("careers@google.com")
            .withWebsite("https://careers.google.com/").withTasks("Application submitted /at 19-10-2022 11:38")
            .withSalary("100000").withTags("Urgent").build();
    public static final Internship GOOG2 = new InternshipBuilder().withName("Google")
            .withPosition("Software Engineer").withStatus("Progress").withEmail("careers@google.com")
            .withWebsite("https://careers.google.com/").withTasks("Application submitted /at 19-10-2022 11:38")
            .withSalary("100000").withTags().build();
    public static final Internship GOOG3 = new InternshipBuilder().withName("Google")
            .withPosition("Software Engineer").withStatus("Progress").withEmail("careers@google.com")
            .withWebsite("https://careers.google.com/").withTasks("Application submitted /at 19-10-2022 11:38")
            .withSalary("100000").withTags("Urgent", "Remote").build();
    public static final Internship META = new InternshipBuilder().withName("Meta")
            .withPosition("Data Analyst").withStatus("Progress").withEmail("careers@meta.com")
            .withWebsite("https://www.metacareers.com/").withTasks("Application submitted /at 20-10-2022 12:00")
            .withSalary("125000").withTags("Urgent", "Remote").build();
    public static final Internship BABA = new InternshipBuilder().withName("Alibaba")
            .withPosition("Frontend Engineer").withStatus("Offered").withEmail("careers@alibaba.com")
            .withWebsite("https://careers.alibaba.com/").withTasks("Application submitted /at 19-10-2022 11:38")
            .withSalary("150000").build();
    public static final Internship PYPL = new InternshipBuilder().withName("Paypal")
            .withPosition("Backend Engineer").withStatus("Progress").withEmail("careers@paypal.com")
            .withWebsite("https://careers.pypl.com/home/")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withSalary("175000")
            .withTags("Remote").build();
    public static final Internship SSNLF = new InternshipBuilder().withName("Samsung Group")
            .withPosition("Full Stack Engineer").withStatus("Offered").withEmail("careers@samsung.com")
            .withWebsite("https://www.samsung.com/sg/about-us/careers/")
            .withTasks("Application submitted /at 25-10-2022 08:30", "Technical Interview /at 30-10-2022 09:00")
            .withSalary("200000")
            .withTags("Urgent").build();
    public static final Internship TCEHY = new InternshipBuilder().withName("Tencent Holdings Ltd")
            .withPosition("Cyber Security Analyst").withStatus("Offered").withEmail("careers@tencent.com")
            .withWebsite("https://careers.tencent.com/en-us/home.html")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withSalary("225000").build();
    public static final Internship NFLX = new InternshipBuilder().withName("Netflix")
            .withPosition("Algorithm Engineer").withStatus("Progress").withEmail("careers@netflix.com")
            .withWebsite("https://jobs.netflix.com/")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withSalary("250000").build();

    // Manually added
    public static final Internship ADBE = new InternshipBuilder().withName("Adobe Inc")
            .withPosition("Product Designer").withStatus("Progress").withEmail("careers@adobe.com")
            .withWebsite("https://www.adobe.com/careers.html").withSalary("300000").build();
    public static final Internship UBER = new InternshipBuilder().withName("Uber Technologies")
            .withPosition("Data Engineer").withStatus("Progress").withEmail("careers@uber.com")
            .withWebsite("https://www.uber.com/us/en/careers/").withSalary("275000").build();

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

    public static final String KEYWORD_MATCHING_ADBE = "Adobe"; // A keyword that matches ADBE

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

    /**
     * Returns an {@code InTrack} with all the typical internships.
     */
    public static InTrack getTypicalInTrackStub() {
        InTrack it = new InTrack();
        for (Internship internship : getTypicalInternshipsStub()) {
            it.addInternship(internship);
        }
        return it;
    }

    /**
     * Returns an {@code InTrack} with all the typical internships.
     */
    public static InTrack getTypicalInTrackStub2() {
        InTrack it = new InTrack();
        for (Internship internship : getTypicalInternshipsStub2()) {
            it.addInternship(internship);
        }
        return it;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(GOOG, META, BABA, PYPL, SSNLF, TCEHY, NFLX));
    }

    public static List<Internship> getTypicalInternshipsStub() {
        return new ArrayList<>(Arrays.asList(GOOG2, META, BABA, PYPL, SSNLF, TCEHY, NFLX));
    }

    public static List<Internship> getTypicalInternshipsStub2() {
        return new ArrayList<>(Arrays.asList(GOOG3, META, BABA, PYPL, SSNLF, TCEHY, NFLX));
    }

    public static List<Internship> getUnsortedInternships() {
        return new ArrayList<>(Arrays.asList(GOOG, SSNLF, META));
    }

    public static List<Internship> getSortedAscendingInternships() {
        return new ArrayList<>(Arrays.asList(GOOG, SSNLF, META));
    }

    public static List<Internship> getSortedDescendingInternships() {
        return new ArrayList<>(Arrays.asList(META, SSNLF, GOOG));
    }
}
