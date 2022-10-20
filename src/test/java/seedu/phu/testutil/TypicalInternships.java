package seedu.phu.testutil;

import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_STOCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_BLACKROCK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.phu.model.InternshipBook;
import seedu.phu.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship AMAZON = new InternshipBuilder().withName("Amazon")
            .withRemark("1 George St, Singapore 049145").withEmail("amazon@example.com")
            .withPhone("94351253")
            .withTags("stocks").withPosition("Backend Intern").withApplicationProcess("APPLY")
            .withDate("11-12-2022").withWebsite("https://www.amazon.jobs").build();
    public static final Internship BYTEDANCE = new InternshipBuilder().withName("Bytedance")
            .withRemark("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "stocks").withPosition("software engineer").withApplicationProcess("ASSESSMENT")
            .withDate("24-09-2022").withWebsite("https://jobs.bytedance.com/en/").build();
    public static final Internship CITADEL = new InternshipBuilder().withName("Citadel").withPhone("95352563")
            .withEmail("heinz@example.com").withRemark("wall street").withPosition("backend engineer")
            .withApplicationProcess("APPLY").withDate("24-09-2022").withWebsite("NA").build();
    public static final Internship DSTA = new InternshipBuilder().withName("DSTA").withPhone("87652533")
            .withEmail("cornelia@example.com").withRemark("10th street").withTags("stocks")
            .withPosition("frontend engineer").withApplicationProcess("INTERVIEW")
            .withDate("14-09-2022").withWebsite("NA").build();
    public static final Internship EBAY = new InternshipBuilder().withName("Ebay").withPhone("9482224")
            .withEmail("werner@example.com").withRemark("michegan ave").withPosition("data engineer")
            .withApplicationProcess("APPLY").withDate("24-09-2022").withWebsite("NA").build();
    public static final Internship FASTLY = new InternshipBuilder().withName("Fastly").withPhone("9482427")
            .withEmail("lydia@example.com").withRemark("little tokyo").withPosition("AI engineer")
            .withApplicationProcess("APPLY").withApplicationProcess("APPLY").withDate("24-09-2022")
            .withWebsite("NA").build();
    public static final Internship GOOGLE = new InternshipBuilder().withName("Google").withPhone("9482442")
            .withEmail("anna@example.com").withRemark("4th street").withPosition("Data analyst")
            .withApplicationProcess("APPLY").withApplicationProcess("APPLY").withDate("24-09-2022")
            .withWebsite("NA").build();

    // Manually added
    public static final Internship HOON = new InternshipBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withRemark("little india").build();
    public static final Internship IDA = new InternshipBuilder().withName("Ida Muebayr").withPhone("8482131")
            .withEmail("hans@example.com").withRemark("chicago ave").build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship APPLE = new InternshipBuilder().withName(VALID_NAME_APPLE)
            .withPhone(VALID_PHONE_APPLE).withEmail(VALID_EMAIL_APPLE)
            .withRemark(VALID_REMARK_APPLE).withTags(VALID_TAG_STOCK)
            .withPosition(VALID_POSITION_APPLE).withApplicationProcess(VALID_APPLICATION_PROCESS_APPLE)
            .withDate(VALID_DATE_APPLE).withWebsite(VALID_WEBSITE_APPLE).build();
    public static final Internship BLACKROCK = new InternshipBuilder().withName(VALID_NAME_BLACKROCK)
            .withPhone(VALID_PHONE_BLACKROCK).withEmail(VALID_EMAIL_BLACKROCK)
            .withRemark(VALID_REMARK_BLACKROCK).withTags(VALID_TAG_TRANSPORT, VALID_TAG_STOCK)
            .withPosition(VALID_POSITION_BLACKROCK).withApplicationProcess(VALID_APPLICATION_PROCESS_BLACKROCK)
            .withDate(VALID_DATE_BLACKROCK).withWebsite(VALID_WEBSITE_BLACKROCK).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code InternshipBook} with all the typical internships.
     */
    public static InternshipBook getTypicalInternshipBook() {
        InternshipBook ab = new InternshipBook();
        for (Internship internship : getTypicalInternships()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(AMAZON, BYTEDANCE, CITADEL, DSTA, EBAY, FASTLY, GOOGLE));
    }
}
