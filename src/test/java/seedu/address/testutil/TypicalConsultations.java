package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CONSULT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CONSULT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CONSULT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CONSULT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_CONSULT2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.consultation.Consultation;



/**
 * A utility class containing a list of {@code Consultation} objects to be used in tests.
 */

public class TypicalConsultations {
    public static final Consultation CONSULTATION1 = new ConsultationBuilder().withName("John").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("10:00-12:00").withDescription("testing").build();
    public static final Consultation CONSULTATION2 = new ConsultationBuilder().withName("Anna").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("12:00-13:00").withDescription("testing").build();
    public static final Consultation CONSULTATION3 = new ConsultationBuilder().withName("Stu3").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("13:00-14:00").withDescription("testing").build();
    public static final Consultation CONSULTATION4 = new ConsultationBuilder().withName("Stu4").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("14:00-15:00").withDescription("testing").build();
    public static final Consultation CONSULTATION5 = new ConsultationBuilder().withName("Stu5").withModule("CS2103T")
            .withVenue("COM1-0203").withTimeslot("15:00-16:00").withDescription("testing").build();

    // Manually added
    public static final Consultation FIRST = new ConsultationBuilder().withName("W03").withModule("CS2101")
            .withVenue("ST17").withTimeslot("9:00-11:00").withDescription("testing").build();
    public static final Consultation SECOND = new ConsultationBuilder().withName("W04").withModule("CS2101")
            .withVenue("ST17").withTimeslot("13:00-15:00").withDescription("testing").build();

    // Manually added - Consultation's details found in {@code CommandTesCONSULTil}
    public static final Consultation CONSULT1 = new ConsultationBuilder().withName(VALID_NAME_CONSULT1)
            .withModule(VALID_MODULE_CONSULT1).withVenue(VALID_VENUE_CONSULT1).withTimeslot(VALID_TIMESLOT_CONSULT1)
            .withDescription(VALID_DESCRIPTION_CONSULT1).build();
    public static final Consultation TU2 = new ConsultationBuilder().withName(VALID_NAME_CONSULT2)
            .withModule(VALID_MODULE_CONSULT2).withVenue(VALID_VENUE_CONSULT2).withTimeslot(VALID_TIMESLOT_CONSULT2)
            .withDescription(VALID_DESCRIPTION_CONSULT2).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalConsultations() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Consultations.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Consultation consultation : getTypicalConsultations()) {
            ab.addConsultation(consultation);
        }
        return ab;
    }

    public static List<Consultation> getTypicalConsultations() {
        return new ArrayList<>(Arrays.asList(CONSULTATION1, CONSULTATION2, CONSULTATION3, CONSULTATION4,
                CONSULTATION5));
    }
}
