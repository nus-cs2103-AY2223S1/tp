package friday.testutil;

import static friday.logic.commands.CommandTestUtil.VALID_CONSULTATION_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_CONSULTATION_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_MASTERYCHECK_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_MASTERYCHECK_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static friday.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static friday.logic.commands.CommandTestUtil.VALID_TELEGRAMHANDLE_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_TELEGRAMHANDLE_BOB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import friday.model.Friday;
import friday.model.student.Student;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline")
            .withMasteryCheck(LocalDate.of(2022, 2, 17))
            .withConsultation(LocalDate.of(2022, 2, 22))
            .withTelegramHandle("4lice").withRemark("She likes aardvarks.")
            .withTags("friends").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier")
            .withMasteryCheck(LocalDate.of(2022, 8, 17)).withRemark("He can't take beer!")
            .withConsultation(LocalDate.of(2022, 9, 17)).withTelegramHandle("benson123")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz").withTelegramHandle("carl_isle")
            .withConsultation(LocalDate.of(2022, 11, 17))
            .withMasteryCheck(LocalDate.of(2022, 2, 17)).build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier").withTelegramHandle("D4NI3L")
            .withConsultation(LocalDate.of(2022, 1, 21))
            .withMasteryCheck(LocalDate.of(2022, 4, 2)).withTags("friends").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer").withTelegramHandle("33elle33")
            .withConsultation(LocalDate.of(2022, 9, 7))
            .withMasteryCheck(LocalDate.of(2022, 1, 1)).build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz").withTelegramHandle("fionakunz")
            .withConsultation(LocalDate.of(2022, 2, 20))
            .withMasteryCheck(LocalDate.of(2022, 3, 27)).build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best").withTelegramHandle("imthe_best")
            .withConsultation(LocalDate.of(2022, 12, 17))
            .withMasteryCheck(LocalDate.of(2023, 2, 17)).build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier").withTelegramHandle("hoonigan1")
            .withConsultation(LocalDate.of(2022, 2, 23))
            .withMasteryCheck(LocalDate.of(2020, 2, 17)).build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller").withTelegramHandle("id4mueller")
            .withConsultation(LocalDate.of(2024, 2, 17))
            .withMasteryCheck(LocalDate.of(2024, 2, 7)).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withTelegramHandle(VALID_TELEGRAMHANDLE_AMY).withConsultation(VALID_CONSULTATION_AMY)
            .withMasteryCheck(VALID_MASTERYCHECK_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTelegramHandle(VALID_TELEGRAMHANDLE_BOB).withConsultation(VALID_CONSULTATION_BOB)
            .withMasteryCheck(VALID_MASTERYCHECK_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Friday getTypicalAddressBook() {
        Friday ab = new Friday();
        for (Student student : getTypicalPersons()) {
            ab.addPerson(student);
        }
        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
