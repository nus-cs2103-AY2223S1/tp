package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXTOFKIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXTOFKIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student STUDENT1 = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withSchool("Keming Primary School")
            .withLevel("PRIMARY3")
            .withNextOfKin("Teresa Lim")
            .build();

    public static final Student STUDENT2 = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withSchool("Zheng Hua Secondary School")
            .withLevel("SECONDARY2")
            .withNextOfKin("Aries Toh")
            .build();

    public static final Student AMY_STUDENT = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withSchool(VALID_SCHOOL_AMY)
            .withLevel(VALID_LEVEL_AMY).withNextOfKin(VALID_NEXTOFKIN_AMY).withTags(VALID_TAG_FRIEND).build();

    public static final Student BOB_STUDENT = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withSchool(VALID_SCHOOL_BOB)
            .withLevel(VALID_LEVEL_BOB).withNextOfKin(VALID_NEXTOFKIN_BOB).withTags(VALID_TAG_FRIEND).build();

    private TypicalStudents() {} // prevents instantiation

    public static AddressBook getTypicalStudentsAddressBook() {
        AddressBook ab = new AddressBook();

        List<Student> studentList = new ArrayList<>(Arrays.asList(TypicalStudents.STUDENT1, TypicalStudents.STUDENT2));
        for (Student s : studentList) {
            ab.addPerson(s);
        }
        return ab;
    }
}
