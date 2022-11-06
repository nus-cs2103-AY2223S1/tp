package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN1;
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN2;
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN3;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;

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
            .withNextOfKin(NEXTOFKIN1)
            .withTuitionClasses(TUITIONCLASS1, TUITIONCLASS2)
            .build();

    public static final Student STUDENT2 = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withSchool("Zheng Hua Secondary School")
            .withLevel("SECONDARY2")
            .withNextOfKin(NEXTOFKIN2)
            .withTuitionClasses(TUITIONCLASS1, TUITIONCLASS2)
            .build();

    public static final Student STUDENT3 = new StudentBuilder().withName("John Appleseed")
            .withAddress("118, Changi Road, #03-45")
            .withEmail("johnapple@example.com").withPhone("91235252")
            .withTags("applefanboy")
            .withSchool("Apple Primary School")
            .withLevel("PRIMARY6")
            .withNextOfKin(NEXTOFKIN3)
            .withTuitionClasses(TUITIONCLASS2)
            .build();

    public static final Student STUDENT4 = new StudentBuilder().withName("Peter James")
            .withAddress("300, Woodlands Road, #01-45")
            .withEmail("peterj@example.com").withPhone("90021252")
            .withTags("friend")
            .withSchool("Apple Primary School")
            .withLevel("PRIMARY6")
            .withNextOfKin(NEXTOFKIN3)
            .withTuitionClasses(TUITIONCLASS2)
            .build();

    public static final Student AMY_STUDENT = new StudentBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withSchool(VALID_SCHOOL_AMY)
            .withLevel(VALID_LEVEL_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();

    public static final Student BOB_STUDENT = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withSchool(VALID_SCHOOL_BOB)
            .withLevel(VALID_LEVEL_BOB)
            .withTags(VALID_TAG_FRIEND)
            .build();

    private TypicalStudents() {} // prevents instantiation

    //    public static AddressBook getTypicalStudentsAddressBook() {
    //        AddressBook ab = new AddressBook();
    //
    //        List<Student> studentList = new ArrayList<>(Arrays.asList(TypicalStudents.STUDENT1,
    //        TypicalStudents.STUDENT2));
    //        for (Student s : studentList) {
    //            ab.addPerson(s);
    //        }
    //        return ab;
    //    }

    //todo: temporary fix for problem: the tests that use the method may make changes to STUDENT1 and STUDENT2
    public static AddressBook getTypicalStudentsAddressBook() {
        AddressBook ab = new AddressBook();

        List<Student> studentList = new ArrayList<>(Arrays.asList(new StudentBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253")
                .withTags("friends")
                .withSchool("Keming Primary School")
                .withLevel("PRIMARY3")
                .withNextOfKin(NEXTOFKIN1)
                .withTuitionClasses(TUITIONCLASS1, TUITIONCLASS2)
                .build(), new StudentBuilder().withName("Benson Meier")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withTags("owesMoney", "friends")
                .withSchool("Zheng Hua Secondary School")
                .withLevel("SECONDARY2")
                .withNextOfKin(NEXTOFKIN2)
                .withTuitionClasses(TUITIONCLASS1, TUITIONCLASS2)
                .build()));
        for (Student s : studentList) {
            ab.addPerson(s);
        }
        return ab;
    }
}
