package seedu.modquik.testutil;

import static seedu.modquik.logic.commands.CommandTestUtil.VALID_MODULE_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_MODULE_TUT2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_TUT2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TIMESLOT_TUT1_END;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TIMESLOT_TUT1_START;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TIMESLOT_TUT2_END;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TIMESLOT_TUT2_START;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_VENUE_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_VENUE_TUT2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modquik.model.ModQuik;
import seedu.modquik.model.tutorial.Tutorial;

/**
 * A utility class containing a list of {@code Tutorial} objects to be used in tests.
 */
public class TypicalTutorials {

    public static final Tutorial TUTORIAL1 = new TutorialBuilder().withName("F01").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("10:00", "12:00").build();
    public static final Tutorial TUTORIAL2 = new TutorialBuilder().withName("F02").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("12:00", "13:00").build();
    public static final Tutorial TUTORIAL3 = new TutorialBuilder().withName("F03").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("13:00", "14:00").build();
    public static final Tutorial TUTORIAL4 = new TutorialBuilder().withName("F04").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("14:00", "15:00").build();
    public static final Tutorial TUTORIAL5 = new TutorialBuilder().withName("F05").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("15:00", "16:00").build();

    // Manually added
    public static final Tutorial FIRST = new TutorialBuilder().withName("W03").withModule("CS2101")
            .withVenue("ST17").withTimeslot("09:00", "11:00").build();
    public static final Tutorial SECOND = new TutorialBuilder().withName("W04").withModule("CS2101")
            .withVenue("ST17").withTimeslot("13:00", "15:00").build();

    // Manually added - Tutorial's details found in {@code CommandTestUtil}
    public static final Tutorial TUT1 = new TutorialBuilder().withName(VALID_NAME_TUT1).withModule(VALID_MODULE_TUT1)
            .withVenue(VALID_VENUE_TUT1).withTimeslot(VALID_TIMESLOT_TUT1_START, VALID_TIMESLOT_TUT1_END).build();
    public static final Tutorial TU2 = new TutorialBuilder().withName(VALID_NAME_TUT2).withModule(VALID_MODULE_TUT2)
            .withVenue(VALID_VENUE_TUT2).withTimeslot(VALID_TIMESLOT_TUT2_START, VALID_TIMESLOT_TUT2_END).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTutorials() {} // prevents instantiation

    /**
     * Returns an {@code ModQuik} with all the typical tutorials.
     */
    public static ModQuik getTypicalModQuik() {
        ModQuik ab = new ModQuik();
        for (Tutorial tutorial : getTypicalTutorials()) {
            ab.addTutorial(tutorial);
        }
        return ab;
    }

    public static List<Tutorial> getTypicalTutorials() {
        return new ArrayList<>(Arrays.asList(TUTORIAL1, TUTORIAL2, TUTORIAL3, TUTORIAL4, TUTORIAL5));
    }
}
