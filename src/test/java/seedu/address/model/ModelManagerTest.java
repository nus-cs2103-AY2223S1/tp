package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

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
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
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
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
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
    public void sortList_executeForStudentList_sortAlpha() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new StudentBuilder().withName("Mary").build());
        studentList.add(new StudentBuilder().withName("Charlie").build());
        studentList.add(new StudentBuilder().withName("Sam").build());
        studentList.add(new StudentBuilder().withName("Wendy").build());
        studentList.add(new StudentBuilder().withName("Mike").build());

        Model studentModel = new ModelManager();
        for (Student s : studentList) {
            studentModel.addPerson(s);
        }
        studentModel.sortList(Model.ListType.STUDENT_LIST, SortCommand.SortBy.ALPHA);
        studentList.sort(Comparator.comparing(student -> student.getName().fullName));
        assertEquals(studentList, studentModel.getFilteredStudentList());
    }

    @Test
    public void sortList_executeForTutorList_sortReverse() {
        List<Tutor> tutorList = new ArrayList<>();
        tutorList.add(new TutorBuilder().withName("Mary").build());
        tutorList.add(new TutorBuilder().withName("Charlie").build());
        tutorList.add(new TutorBuilder().withName("Sam").build());
        tutorList.add(new TutorBuilder().withName("Wendy").build());
        tutorList.add(new TutorBuilder().withName("Mike").build());

        Model tutorModel = new ModelManager();
        for (Tutor s : tutorList) {
            tutorModel.addPerson(s);
        }
        tutorModel.sortList(Model.ListType.TUTOR_LIST, SortCommand.SortBy.REVERSE);
        Collections.reverse(tutorList);
        assertEquals(tutorList, tutorModel.getFilteredTutorList());
    }

    @Test
    public void sortList_executeForTuitionClassList_sortDefault() {
        List<TuitionClass> tuitionClassList = new ArrayList<>();
        tuitionClassList.add(new TuitionClassBuilder().withName("P5MATH").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P2SCIENCE").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P4ENG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("SEC2GEOG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P1ARTS").build());

        Model tuitionClassModel = new ModelManager();
        for (TuitionClass tc : tuitionClassList) {
            tuitionClassModel.addTuitionClass(tc);
        }
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.ALPHA);
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.REVERSE);
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.DEFAULT);
        assertEquals(tuitionClassList, tuitionClassModel.getFilteredTuitionClassList());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
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

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate<>(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

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
