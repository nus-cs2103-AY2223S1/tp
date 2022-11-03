package seedu.taassist.testutil;

import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;

import java.util.List;

import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withModuleClasses(CS1231S).build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withModuleClasses(CS1101S, CS1231S).build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withModuleClasses(CS1101S).build()
            .updateGrade(CS1101S, LAB_1, 100);
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withModuleClasses(CS1231S).build()
            .updateGrade(CS1231S, TUTORIAL_1, 1);
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withModuleClasses(new ModuleClass(VALID_CLASS_CS1231S)).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withModuleClasses(new ModuleClass(VALID_CLASS_CS1101S), new ModuleClass(VALID_CLASS_CS1231S))
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code TaAssist} with all the typical students.
     */
    public static TaAssist getTypicalTaAssist() {
        TaAssist taAssist = new TaAssist();
        getTypicalModuleClasses().forEach(taAssist::addModuleClass);
        getTypicalStudents().forEach(taAssist::addStudent);
        return taAssist;
    }

    public static List<Student> getTypicalStudents() {
        return List.of(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
    }

    public static List<ModuleClass> getTypicalModuleClasses() {
        return List.of(CS1101S, CS1231S);
    }
}
