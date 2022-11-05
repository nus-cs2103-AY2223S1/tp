package seedu.studmap.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_HANDLE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.studmap.testutil.Assert.assertThrows;
import static seedu.studmap.testutil.TypicalStudents.ALICE;
import static seedu.studmap.testutil.TypicalStudents.BOB;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.studmap.model.tag.Tag;
import seedu.studmap.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSamestudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, module, studentid, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withGitName(VALID_NAME_BOB).withTeleHandle(VALID_HANDLE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void getstudentData() {
        // reconstruction student from getStudentData returns same student
        assertTrue(ALICE.isSameStudent(new Student(ALICE.getStudentData())));
        // studentData returned from getStudentData() should be equal across invocations
        assertEquals(ALICE.getStudentData(), ALICE.getStudentData());
    }
    @Test
    public void studentDataConstructor() {
        StudentData studentData = new StudentData();
        Name name = new Name(StudentBuilder.DEFAULT_NAME);
        Phone phone = new Phone(StudentBuilder.DEFAULT_PHONE);
        Email email = new Email(StudentBuilder.DEFAULT_EMAIL);
        Module module = new Module(StudentBuilder.DEFAULT_MODULE);
        StudentID id = new StudentID(StudentBuilder.DEFAULT_ID);
        GitName gitName = new GitName(StudentBuilder.DEFAULT_GIT);
        TeleHandle handle = new TeleHandle(StudentBuilder.DEFAULT_TELE);
        Set<Tag> tags = Set.of(new Tag("Friends"));
        Set<Attendance> attendances = Set.of(new Attendance("T01", Attendance.Status.PRESENT));
        studentData.setName(name);
        studentData.setPhone(phone);
        studentData.setEmail(email);
        studentData.setModule(module);
        studentData.setId(id);
        studentData.setGitUser(gitName);
        studentData.setTeleHandle(handle);
        studentData.setTags(tags);
        studentData.setAttendances(attendances);

        Student student = new Student(studentData);
        assertEquals(student.getStudentData(), studentData);
        assertEquals(student.getName(), name);
        assertEquals(student.getPhone(), phone);
        assertEquals(student.getEmail(), email);
        assertEquals(student.getModule(), module);
        assertEquals(student.getId(), id);
        assertEquals(student.getGitName(), gitName);
        assertEquals(student.getTeleHandle(), handle);
        assertEquals(student.getTags(), tags);
        assertEquals(student.getAttendances(), attendances);
    }
}
