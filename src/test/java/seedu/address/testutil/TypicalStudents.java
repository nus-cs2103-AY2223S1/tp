package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ProfNus;
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder(new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build())
            .withId("A0121212W")
            .withTelegramHandle("@good_user12")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();
    public static final Student BENSON = new StudentBuilder(new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build())
            .withId("A0123456W")
            .withTelegramHandle("@good_user")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();
    public static final Student CARL = new StudentBuilder(new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build())
            .withId("A0126532W")
            .withTelegramHandle("@bad_user")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();
    public static final Student DANIEL = new StudentBuilder(new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build())
            .withId("A0123423F")
            .withTelegramHandle("@good123_user")
            .withStudentInfo("CS1101S").build();
    public static final Student ELLE = new StudentBuilder(new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build())
            .withId("A0124223G")
            .withTelegramHandle("@good321_user")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();
    public static final Student FIONA = new StudentBuilder(new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build())
            .withId("A0126393U")
            .withTelegramHandle("@good_use")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();
    public static final Student GEORGE = new StudentBuilder(new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build())
            .withId("A0123431A")
            .withTelegramHandle("@good_user69")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();

    // Manually added
    public static final Student HOON = new StudentBuilder(new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build())
            .withId("A0123999G")
            .withTelegramHandle("@HOON_user69")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();
    public static final Student IDA = new StudentBuilder(new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build())
            .withId("A0353431G")
            .withTelegramHandle("@IDA_user69")
            .withStudentInfo("CS1101S")
            .withTeachingInfo("CS2030S").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder(new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build())
            .withId(VALID_ID_AMY)
            .withTelegramHandle(VALID_TELEGRAM_AMY).build();
    public static final Student BOB = new StudentBuilder(new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build())
            .withId(VALID_ID_BOB)
            .withTelegramHandle(VALID_TELEGRAM_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    public static final Module CS2030S = new ModuleBuilder().withName("Programming Methodology 2")
            .withModuleCode("CS2030S").withModuleDescription("Good Mod").build();

    public static final Module CS1101S = new ModuleBuilder().withName("Programming Methodology 1")
            .withModuleCode("CS1101S").withModuleDescription("Decent Mod").build();

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code ProfNus} with all the typical persons.
     */
    public static ProfNus getTypicalProfNus() {
        ProfNus ab = new ProfNus();
        for (Student student : getTypicalStudents()) {
            ab.addPerson(student);
            if (student.isTeachingAssistant()) {
                ab.addTutor(student);
            }
        }
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS1101S, CS2030S));
    }
}
