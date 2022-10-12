package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTITUTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTITUTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.tutor.Tutor;

/**
 * A utility class containing a list of {@code Tutor} objects to be used in tests.
 */
public class TypicalTutors {

    public static final Tutor TUTOR1 = new TutorBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withQualification("Bachelor of Computing")
            .withInstitution("National University of Singapore")
            .build();

    public static final Tutor TUTOR2 = new TutorBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withQualification("Bachelor of Engineering")
            .withInstitution("Nanyang Technological University")
            .build();

    public static final Tutor AMY_TUTOR = new TutorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withQualification(VALID_QUALIFICATION_AMY)
            .withInstitution(VALID_INSTITUTION_AMY).withTags(VALID_TAG_FRIEND).build();

    public static final Tutor BOB_TUTOR = new TutorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withQualification(VALID_QUALIFICATION_BOB)
            .withInstitution(VALID_INSTITUTION_BOB).withTags(VALID_TAG_FRIEND).build();

    private TypicalTutors() {} // prevents instantiation

    public static AddressBook getTypicalTutorsAddressBook() {
        AddressBook ab = new AddressBook();

        List<Tutor> tutorList = new ArrayList<>(Arrays.asList(TypicalTutors.TUTOR1, TypicalTutors.TUTOR2));
        for (Tutor t : tutorList) {
            ab.addPerson(t);
        }
        return ab;
    }
}
