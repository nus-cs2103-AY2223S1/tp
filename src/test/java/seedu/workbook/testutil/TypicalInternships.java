package seedu.workbook.testutil;

import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_DATETIME_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_DATETIME_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_STAGE_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_STAGE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Internship;


/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALICE = new InternshipBuilder()
        .withCompany("Alice Pauline")
        .withRole("Software Engineer")
        .withEmail("alice@example.com")
        .withStage("Technical Interview")
        .withDateTime("12-Oct-2022 12:00")
        .withTags("friends").build();
    public static final Internship BENSON = new InternshipBuilder()
        .withCompany("Benson Meier")
        .withRole("Software Engineer")
        .withEmail("johnd@example.com")
        .withStage("HR Interview")
        .withDateTime("12-Dec-2022 14:00")
        .withLanguageTags("Java", "Python")
        .withTags("owesMoney", "friends").build();
    public static final Internship CARL = new InternshipBuilder()
        .withCompany("Carl Kurz")
        .withRole("Software Engineer")
        .withEmail("heinz@example.com")
        .withStage("Rejected").build();

    public static final Internship DANIEL = new InternshipBuilder()
        .withCompany("Daniel Meier")
        .withRole("Software Engineer")
        .withEmail("cornelia@example.com")
        .withStage("Technical Interview")
        .withTags("friends").build();
    public static final Internship ELLE = new InternshipBuilder()
        .withCompany("Elle Meyer")
        .withRole("Software Engineer")
        .withEmail("werner@example.com")
        .withStage("Application Sent").build();
    public static final Internship FIONA = new InternshipBuilder()
        .withCompany("Fiona Kunz")
        .withRole("Software Engineer")
        .withEmail("lydia@example.com")
        .withStage("Online Assessment").build();
    public static final Internship GEORGE = new InternshipBuilder()
        .withCompany("George Best")
        .withRole("Software Engineer")
        .withEmail("anna@example.com")
        .withStage("Team Lead Interview").build();


    // Manually added
    public static final Internship HOON = new InternshipBuilder()
        .withCompany("Hoon Meier")
        .withRole("Software Engineer")
        .withEmail("stefan@example.com").build();
    public static final Internship IDA = new InternshipBuilder()
        .withCompany("Ida Mueller")
        .withRole("Software Engineer")
        .withEmail("hans@example.com").build();


    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship AMY = new InternshipBuilder()
        .withCompany(VALID_COMPANY_AMY)
        .withRole(VALID_ROLE_AMY)
        .withEmail(VALID_EMAIL_AMY)
        .withStage(VALID_STAGE_AMY)
        .withDateTime(VALID_DATETIME_AMY)
        .withTags(VALID_TAG_FRIEND).build();
    public static final Internship BOB = new InternshipBuilder()
        .withCompany(VALID_COMPANY_BOB)
        .withRole(VALID_ROLE_BOB)
        .withEmail(VALID_EMAIL_BOB)
        .withStage(VALID_STAGE_BOB)
        .withDateTime(VALID_DATETIME_BOB)
        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code WorkBook} with all the typical internships.
     */
    public static WorkBook getTypicalWorkBook() {
        WorkBook ab = new WorkBook();
        for (Internship internship : getTypicalInternships()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
