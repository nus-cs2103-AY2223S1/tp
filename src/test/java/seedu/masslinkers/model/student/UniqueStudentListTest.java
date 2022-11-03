package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalStudents.ALICE;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.student.exceptions.DuplicateStudentException;
import seedu.masslinkers.model.student.exceptions.StudentNotFoundException;
import seedu.masslinkers.testutil.StudentBuilder;
//@@author sltsheryl
//@@author jonasgwt
public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_studentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB)
                .withInterests(VALID_INTEREST_SWE)
                .build();
        //editedAlice contains same GitHub, email and phone number as ALICE.
        assertTrue(uniqueStudentList.contains(editedAlice));
    }

    @Test
    public void contains_studentWithSameNameButDifferentIdentityFields_returnsFalse() {
        uniqueStudentList.add(ALICE);
        Student editedALice = new StudentBuilder(ALICE)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withGitHub(VALID_GITHUB_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertFalse(uniqueStudentList.contains(editedALice));
    }

    @Test
    public void containsTelegram_listContainsStudentWithGivenTelegram_returnsTrue() {
        Telegram aliceTelegram = ALICE.getTelegram();
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.containsTelegram(aliceTelegram));
    }

    @Test
    public void containsTelegram_listWithoutStudentWithGivenTelegram_returnsFalse() {
        Telegram bobTelegram = BOB.getTelegram();
        uniqueStudentList.add(ALICE);
        assertFalse(uniqueStudentList.containsTelegram(bobTelegram));
    }

    @Test
    public void containsGitHub_listContainsStudentWithGivenGitHub_returnsTrue() {
        GitHub aliceGitHub = ALICE.getGitHub();
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.containsGitHub(aliceGitHub));
    }

    @Test
    public void containsGitHub_listWithoutStudentWithGivenGitHub_returnsFalse() {
        GitHub bobGitHub = BOB.getGitHub();
        uniqueStudentList.add(ALICE);
        assertFalse(uniqueStudentList.containsGitHub(bobGitHub));
    }

    @Test
    public void containsEmail_listContainsStudentWithGivenEmail_returnsTrue() {
        Email aliceEmail = ALICE.getEmail();
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.containsEmail(aliceEmail));
    }

    @Test
    public void containsEmail_listWithoutStudentWithGivenEmail_returnsFalse() {
        Email bobEmail = BOB.getEmail();
        uniqueStudentList.add(ALICE);
        assertFalse(uniqueStudentList.containsEmail(bobEmail));
    }

    @Test
    public void containsPhoneNumber_listContainsStudentWithGivenPhoneNumber_returnsTrue() {
        Phone alicePhone = ALICE.getPhone();
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.containsPhone(alicePhone));
    }

    @Test
    public void containsPhoneNumber_listWithoutStudentWithGivenPhoneNumber_returnsFalse() {
        Phone bobPhone = BOB.getPhone();
        uniqueStudentList.add(ALICE);
        assertFalse(uniqueStudentList.containsPhone(bobPhone));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.add(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.add(ALICE));
    }

    @Test
    public void setStudent_nullTargetStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null, ALICE));
    }

    @Test
    public void setStudent_nullEditedStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(ALICE, null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.setStudent(ALICE, ALICE));
    }

    @Test
    public void setStudent_editedStudentIsSameStudent_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(ALICE);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB)
                .withInterests(VALID_INTEREST_SWE)
                .build();
        uniqueStudentList.setStudent(ALICE, editedAlice);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedAlice);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, BOB);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.add(BOB);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.remove(ALICE));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.remove(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setStudents_uniqueStudentList_replacesOwnListWithProvidedUniqueStudentList() {
        uniqueStudentList.add(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        uniqueStudentList.setStudents(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueStudentList.setStudents(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStudentList.asUnmodifiableObservableList().remove(0));
    }
}
