package seedu.uninurse.testutil;

import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_TAG_RISK;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_TAG_ROOM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.NonRecurringTask;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Patient ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").build();
    public static final Patient BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withConditions("Crohn's disease", "H1N1")
            .withMedications(new Medication("Amoxicillin", "0.5 g every 8 hours"))
            .withTasks(new NonRecurringTask("Insert urinary catheter", new DateTime("16-12-22 1245")),
                    new NonRecurringTask("Check vitals", new DateTime("16-10-22 1015")))
            .withRemarks("Allergic to Amoxicillin")
            .withTags("high-risk", "2A nursing home").build();
    public static final Patient CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withConditions("multiple sclerosis")
            .withTasks(new NonRecurringTask("Update health records", new DateTime("28-10-22 1730")))
            .withRemarks("Requires wheelchair to move around")
            .withTags("high-risk").build();
    public static final Patient DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Patient ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Patient FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Patient GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Patient HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Patient IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_RISK).build();
    public static final Patient BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_ROOM, VALID_TAG_RISK)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code UninurseBook} with all the typical persons.
     */
    public static UninurseBook getTypicalUninurseBook() {
        UninurseBook ab = new UninurseBook();
        for (Patient person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Patient> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
