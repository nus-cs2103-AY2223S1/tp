package seedu.address.testutil;

import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_NAME_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_NAME_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_STATUS_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_STATUS_BOBBY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALICE = new InternshipBuilder()
            .withInternshipId(0)
            .withCompanyName("Alice Pte Ltd")
            .withRole("Cleaner")
            .withStatus("PENDING")
            .withInterviewDate("2021-10-10 10:10")
            .build();
    public static final Internship BENSON = new InternshipBuilder()
            .withInternshipId(1)
            .withCompanyName("Benson Electronics Pte Ltd")
            .withRole("Sales Admin Assistant")
            .withStatus("ACCEPTED")
            .withInterviewDate("2021-10-11 10:10")
            .build();
    public static final Internship CARL = new InternshipBuilder()
            .withInternshipId(2)
            .withCompanyName("Carl Automobiles Pte Ltd")
            .withRole("Cashier")
            .withStatus("PENDING")
            .withInterviewDate("2021-11-10 12:10")
            .build();
    public static final Internship DANIEL = new InternshipBuilder()
            .withInternshipId(3)
            .withCompanyName("Daniel Consultancy Pte Ltd")
            .withRole("Receptionist")
            .withStatus("ACCEPTED")
            .withInterviewDate("2021-12-22 13:40")
            .build();
    public static final Internship ELLE = new InternshipBuilder()
            .withInternshipId(4)
            .withCompanyName("Elle Massage Pte Ltd")
            .withRole("Data Analyst")
            .withStatus("COMPLETED")
            .withInterviewDate("2021-02-08 15:30")
            .build();
    public static final Internship FIONA = new InternshipBuilder()
            .withInternshipId(5)
            .withCompanyName("Fiona Constructions Pte Ltd")
            .withRole("Architecture Designer")
            .withStatus("PENDING")
            .withInterviewDate("2021-12-22 11:10")
            .build();
    public static final Internship GEORGE = new InternshipBuilder()
            .withInternshipId(6)
            .withCompanyName("George Consultancy Pte Ltd")
            .withRole("Cleaner")
            .withStatus("REJECTED")
            .withInterviewDate("2021-05-25 08:10")
            .build();
    public static final Internship HOON = new InternshipBuilder()
            .withInternshipId(7)
            .withCompanyName("Hoon Logistics Pte Ltd")
            .withRole("Deliveryman")
            .withStatus("REJECTED")
            .withInterviewDate("2021-12-04 14:22")
            .build();
    public static final Internship IDA = new InternshipBuilder()
            .withInternshipId(8)
            .withCompanyName("Ida Ideas Pte Ltd")
            .withRole("Planning Assistant")
            .withStatus("ACCEPTED")
            .withInterviewDate("2022-03-05 14:48")
            .build();

    // Manually added - Internship's details found in {@code InternshipCommandTestUtil}
    public static final Internship ABC = new InternshipBuilder()
            .withCompanyName(VALID_NAME_ABC).withRole(VALID_ROLE_ABC)
            .withStatus(VALID_STATUS_ABC)
            .withInterviewDate(VALID_INTERVIEW_ABC)
            .build();
    public static final Internship BOBBY = new InternshipBuilder()
            .withCompanyName(VALID_NAME_BOBBY).withRole(VALID_ROLE_BOBBY)
            .withStatus(VALID_STATUS_BOBBY)
            .withInterviewDate(VALID_INTERVIEW_BOBBY)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical internships.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Internship internship : getTypicalInternships()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA));
    }
}
