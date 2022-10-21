package seedu.intrack.testutil;

import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TASK_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TASK_MSFT;

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
            .withPosition("Software Engineer")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withStatus("Progress")
            .withTasks("Application submitted /at 19-10-2022 11:38")
            .withTags("friends").build();
    public static final Internship BENSON = new InternshipBuilder().withName("Benson Meier")
            .withPosition("Data Analyst")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withStatus("Rejected")
            .withTasks("Application submitted /at 20-10-2022 12:00")
            .withTags("owesMoney", "friends").build();
    public static final Internship CARL = new InternshipBuilder().withName("Carl Kurz").withPhone("95352563")
            .withPosition("Frontend Engineer").withEmail("heinz@example.com").withAddress("wall street")
            .withStatus("Offered")
            .withTasks("Application submitted /at 19-10-2022 11:38")
            .build();
    public static final Internship DANIEL = new InternshipBuilder().withName("Daniel Meier").withPhone("87652533")
            .withPosition("Backend Engineer").withEmail("cornelia@example.com").withStatus("Rejected")
            .withAddress("10th street")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .withTags("friends")
            .build();
    public static final Internship ELLE = new InternshipBuilder().withName("Elle Meyer").withPhone("9482224")
            .withPosition("Full Stack Engineer").withEmail("werner@example.com").withAddress("michegan ave")
            .withStatus("Offered")
            .withTasks("Application submitted /at 25-10-2022 08:30", "Technical Interview /at 30-10-2022 09:00")
            .build();
    public static final Internship FIONA = new InternshipBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withPosition("Cyber Security Analyst").withEmail("lydia@example.com").withAddress("little tokyo")
            .withStatus("Offered")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .build();
    public static final Internship GEORGE = new InternshipBuilder().withName("George Best").withPhone("9482442")
            .withPosition("Algorithm Engineer").withEmail("anna@example.com").withAddress("4th street")
            .withStatus("Progress")
            .withTasks("Application submitted /at 19-10-2022 11:38", "HR Interview /at 30-10-2022 09:00")
            .build();

    // Manually added
    public static final Internship HOON = new InternshipBuilder().withName("Hoon Meier").withPhone("8482424")
            .withPosition("Product Designer").withEmail("stefan@example.com").withStatus("Offered")
            .withAddress("little india").build();
    public static final Internship IDA = new InternshipBuilder().withName("Ida Mueller").withPhone("8482131")
            .withPosition("Data Engineer").withEmail("hans@example.com").withStatus("Offered")
            .withAddress("chicago ave").build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship AAPL = new InternshipBuilder().withName(VALID_NAME_AAPL)
            .withPosition(VALID_POSITION_AAPL).withPhone(VALID_PHONE_AAPL).withEmail(VALID_EMAIL_AAPL)
            .withStatus(VALID_STATUS_AAPL).withAddress(VALID_ADDRESS_AAPL)
            .withTasks(VALID_TASK_AAPL).withTags(VALID_TAG_REMOTE).build();
    public static final Internship MSFT = new InternshipBuilder().withName(VALID_NAME_MSFT)
            .withPosition(VALID_POSITION_MSFT).withPhone(VALID_PHONE_MSFT).withEmail(VALID_EMAIL_MSFT)
            .withStatus(VALID_STATUS_MSFT).withAddress(VALID_ADDRESS_MSFT)
            .withTasks(VALID_TASK_MSFT).withTags(VALID_TAG_REMOTE, VALID_TAG_URGENT).build();

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
