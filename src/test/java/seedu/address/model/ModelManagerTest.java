package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT2;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    // Test is only for tutor file path, can add student and tuition class also.
    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTutorAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTutorAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTutorAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTutorAddressBookFilePath(null));
    }

    @Test
    public void setStudentAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setStudentAddressBookFilePath(null));
    }

    @Test
    public void setTuitionClassAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTuitionClassAddressBookFilePath(null));
    }

    @Test
    public void setTutorAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTutorAddressBookFilePath(path);
        assertEquals(path, modelManager.getTutorAddressBookFilePath());
    }

    @Test
    public void setStudentAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setStudentAddressBookFilePath(path);
        assertEquals(path, modelManager.getStudentAddressBookFilePath());
    }

    @Test
    public void setTuitionClassAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTuitionClassAddressBookFilePath(path);
        assertEquals(path, modelManager.getTuitionClassAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(STUDENT1));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(STUDENT1);
        assertTrue(modelManager.hasPerson(STUDENT1));
    }

    @Test
    public void hasTuitionClass_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTuitionClass(null));
    }

    @Test
    public void hasTuitionClass_personNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasTuitionClass(TUITIONCLASS1));
    }

    @Test
    public void hasTuitionClass_personInDatabase_returnsTrue() {
        modelManager.addTuitionClass(TUITIONCLASS1);
        assertTrue(modelManager.hasTuitionClass(TUITIONCLASS1));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void getFilteredTutorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTutorList().remove(0));
    }

    @Test
    public void getFilteredTuitionClassList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTuitionClassList().remove(0));
    }

    @Test
    public void getCurrentListType_getListType() {
        modelManager.updateCurrentListType(Model.ListType.STUDENT_LIST);
        assertEquals(Model.ListType.STUDENT_LIST, modelManager.getCurrentListType());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(STUDENT1).withPerson(STUDENT2).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();

        differentUserPrefs.setTutorAddressBookFilePath(Paths.get("differentTutorFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));

        differentUserPrefs.setStudentAddressBookFilePath(Paths.get("differentStudentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));

        differentUserPrefs.setTuitionClassAddressBookFilePath(Paths.get("differentTuitionClassFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
