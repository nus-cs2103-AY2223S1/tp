package seedu.phu.testutil;

import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.phu.model.InternshipBook;
import seedu.phu.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALICE = new InternshipBuilder().withName("Alice Pauline")
            .withRemark("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").withPosition("Backend Intern").withApplicationProcess("APPLY")
            .withDate("11-12-2022").withWebsite("https://careers.google.com/jobs").build();
    public static final Internship BENSON = new InternshipBuilder().withName("Benson Meier")
            .withRemark("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withPosition("software engineer").withApplicationProcess("ASSESSMENT")
            .withDate("24-09-2022").withWebsite("https://www.grab.com/sg/about").build();
    public static final Internship CARL = new InternshipBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withRemark("wall street").withPosition("backend engineer")
            .withApplicationProcess("APPLY").withDate("24-09-2022").withWebsite("NA").build();
    public static final Internship DANIEL = new InternshipBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withRemark("10th street").withTags("friends")
            .withPosition("frontend engineer").withApplicationProcess("INTERVIEW")
            .withDate("14-09-2022").withWebsite("NA").build();
    public static final Internship ELLE = new InternshipBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withRemark("michegan ave").withPosition("data engineer")
            .withApplicationProcess("APPLY").withDate("24-09-2022").withWebsite("NA").build();
    public static final Internship FIONA = new InternshipBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withRemark("little tokyo").withPosition("AI engineer")
            .withApplicationProcess("APPLY").withApplicationProcess("APPLY").withDate("24-09-2022")
            .withWebsite("NA").build();
    public static final Internship GEORGE = new InternshipBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withRemark("4th street").withPosition("Data analyst")
            .withApplicationProcess("APPLY").withApplicationProcess("APPLY").withDate("24-09-2022")
            .withWebsite("NA").build();

    // Manually added
    public static final Internship HOON = new InternshipBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withRemark("little india").build();
    public static final Internship IDA = new InternshipBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withRemark("chicago ave").build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship AMY = new InternshipBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRemark(VALID_REMARK_AMY).withTags(VALID_TAG_FRIEND)
            .withPosition(VALID_POSITION_AMY).withApplicationProcess(VALID_APPLICATION_PROCESS_AMY)
            .withDate(VALID_DATE_AMY).withWebsite(VALID_WEBSITE_AMY).build();
    public static final Internship BOB = new InternshipBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRemark(VALID_REMARK_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withPosition(VALID_POSITION_BOB).withApplicationProcess(VALID_APPLICATION_PROCESS_BOB)
            .withDate(VALID_DATE_BOB).withWebsite(VALID_WEBSITE_BOB).build();

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
