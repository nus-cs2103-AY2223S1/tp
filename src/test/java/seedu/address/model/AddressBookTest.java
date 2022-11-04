package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXAMTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.FINAL_EXAM;
import static seedu.address.testutil.TypicalExams.MIDTERM_EXAM;
import static seedu.address.testutil.TypicalExams.getTypicalExams;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalTasks.TASK_C;
import static seedu.address.testutil.TypicalTasks.TASK_D;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.ExamBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

public class AddressBookTest {
    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void constructor_withValidReadOnlyAddressBook_createsAddressBookWithData() {
        AddressBook newData = getTypicalAddressBook();
        assertEquals(newData, new AddressBook(newData));
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }
    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }
    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }
    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }
    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }
    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        private final ObservableList<Exam> exams = FXCollections.observableArrayList();
        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }
        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return null;
        }

        @Override
        public ObservableList<Exam> getExamList() {
            return exams;
        }
    }

    //// module-level operation

    @Test
    public void addModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addModule(null));
    }

    @Test
    public void addModule_moduleAlreadyExist_throwsDuplicateModuleException() {
        addressBook.addModule(CS2030);
        Module copy = new ModuleBuilder(CS2030).build();
        assertThrows(DuplicateModuleException.class, () -> addressBook.addModule(copy));
    }

    @Test
    public void addModule_success() {
        addressBook.addModule(CS2030);
        assertEquals(CS2030, addressBook.getModuleList().get(0));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModule(CS2030));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(CS2030);
        assertTrue(addressBook.hasModule(CS2030));
    }

    @Test
    public void hasModule_moduleWithSameModuleCodeInAddressBook_returnsTrue() {
        addressBook.addModule(CS2030);
        Module editedModule = new ModuleBuilder(CS2030).withModuleName("edited name").build();
        assertTrue(addressBook.hasModule(editedModule));
    }

    @Test
    public void removeModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeModule(null));
    }

    @Test
    public void removeModule_success() {
        addressBook.addModule(CS2030);
        assertEquals(1, addressBook.getModuleList().size());
        addressBook.removeModule(new ModuleBuilder().withModuleCode("cs2030").build());
        assertEquals(0, addressBook.getModuleList().size());
    }

    @Test
    public void replaceModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.replaceModule(null, CS2030));
        assertThrows(NullPointerException.class, () -> addressBook.replaceModule(CS2030, null));
    }

    @Test
    public void replaceModule_success() {
        addressBook.addModule(CS2030);
        addressBook.replaceModule(CS2030, CS2040);
        assertEquals(CS2040, addressBook.getModuleList().get(0));
    }

    //// exam-level operations

    @Test
    public void addExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addExam(null));
    }

    @Test
    public void addExam_examAlreadyExist_throwsDuplicateExamException() {
        addressBook.addExam(MIDTERM_EXAM);
        Exam copy = new ExamBuilder(MIDTERM_EXAM).build();
        assertThrows(DuplicateExamException.class, () -> addressBook.addExam(copy));
    }

    @Test
    public void addExam_success() {
        addressBook.addExam(MIDTERM_EXAM);
        assertEquals(MIDTERM_EXAM, addressBook.getExamList().get(0));
    }
    @Test
    public void hasExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasExam(null));
    }

    @Test
    public void hasExam_examNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasExam(MIDTERM_EXAM));
    }

    @Test
    public void hasExam_examInAddressBook_returnsTrue() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        assertTrue(addressBook.hasExam(MIDTERM_EXAM));
    }

    @Test
    public void hasExamWithModule_noExamsWithModule_returnsFalse() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        assertFalse(addressBook.hasExamWithModule(CS2030));
    }

    @Test
    public void hasExamWithModule_hasExamWithModule_returnsFalse() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        assertTrue(addressBook.hasExamWithModule(CS2040));
    }

    @Test
    public void removeExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeExam(null));
    }

    @Test
    public void removeExam_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        assertEquals(1, addressBook.getExamList().size());
        addressBook.removeExam(new ExamBuilder(MIDTERM_EXAM).build());
        assertEquals(0, addressBook.getExamList().size());
    }

    @Test
    public void replaceExam_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            addressBook.replaceExam(null, MIDTERM_EXAM, false));
        assertThrows(NullPointerException.class, () ->
            addressBook.replaceExam(MIDTERM_EXAM, null, false));
    }

    @Test
    public void replaceExam_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(FINAL_EXAM);

        Exam editedExam = new ExamBuilder(FINAL_EXAM).withDescription(VALID_DESCRIPTION_EXAMTWO).build();
        addressBook.replaceExam(FINAL_EXAM, editedExam, false);
        assertEquals(editedExam, addressBook.getExamList().get(0));
    }

    //// task-level operations

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_A);
        assertTrue(addressBook.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_A);
        Task editedTask = new TaskBuilder(TASK_A).withStatus("complete").build();
        assertTrue(addressBook.hasTask(editedTask));
    }

    @Test
    public void hasTaskWithModule_noTasksWithModule_returnsFalse() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_A);
        assertFalse(addressBook.hasTaskWithModule(CS2040));
    }

    @Test
    public void hasTaskWithModule_hasTasksWithModule_returnsFalse() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_A);
        assertTrue(addressBook.hasTaskWithModule(CS2030));
    }

    @Test
    public void addTask_completedTask_success() {
        Task completedTask = new TaskBuilder(TASK_A).withStatus("complete").build();
        addressBook.addModule(CS2030);
        addressBook.addTask(completedTask);

        assertEquals(1, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertEquals(1, addressBook.getModuleList().get(0).getNumOfCompletedTasks());

        List<Task> expectedTaskList = Collections.singletonList(completedTask);
        assertEquals(expectedTaskList, addressBook.getTaskList());
    }

    @Test
    public void addTask_linkedCompletedTask_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_D);

        assertEquals(1, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertEquals(1, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(1, addressBook.getExamList().get(0).getTotalNumOfTasks());
        assertEquals(1, addressBook.getExamList().get(0).getNumOfCompletedTasks());

        List<Task> expectedTaskList = Collections.singletonList(TASK_D);
        assertEquals(expectedTaskList, addressBook.getTaskList());
    }

    @Test
    public void replaceTask_null_throwsNullPointerException() {
        // null target task
        assertThrows(NullPointerException.class, () -> addressBook.replaceTask(null, TASK_A, false));

        // null edited task
        assertThrows(NullPointerException.class, () ->
            addressBook.replaceTask(TASK_A, null, false));
    }

    @Test
    public void replaceTask_unlinkedTask() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_A);
        int initialModuleCompletedCount = addressBook.getModuleList().get(0).getNumOfCompletedTasks();
        int initialModuleTotalCount = addressBook.getModuleList().get(0).getTotalNumOfTasks();

        Task completedTaskA = new TaskBuilder(TASK_A).withStatus("complete").build();
        addressBook.replaceTask(TASK_A, completedTaskA, true);
        assertEquals(initialModuleCompletedCount + 1, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(initialModuleTotalCount, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertTrue(addressBook.getTaskList().get(0).hasAllSameFields(completedTaskA));
    }

    @Test
    public void replaceTask_taskUnlinkedFromExam() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_D);

        int initialModuleCompletedCount = addressBook.getModuleList().get(0).getNumOfCompletedTasks();
        int initialModuleTotalCount = addressBook.getModuleList().get(0).getTotalNumOfTasks();
        int initialExamCompletedCount = addressBook.getExamList().get(0).getNumOfCompletedTasks();
        int initialExamTotalCount = addressBook.getExamList().get(0).getTotalNumOfTasks();

        Task editedTask = new TaskBuilder(TASK_D).withExam(null).build();
        addressBook.replaceTask(TASK_D, editedTask, true);
        assertEquals(initialModuleCompletedCount, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(initialModuleTotalCount, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertEquals(initialExamCompletedCount - 1, addressBook.getExamList().get(0).getNumOfCompletedTasks());
        assertEquals(initialExamTotalCount - 1, addressBook.getExamList().get(0).getTotalNumOfTasks());
        assertTrue(addressBook.getTaskList().get(0).hasAllSameFields(editedTask));
    }

    @Test
    public void replaceTask_tasklinkedToExam() {
        addressBook.addModule(CS2030);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_A);
        int initialModuleCompletedCount = addressBook.getModuleList().get(0).getNumOfCompletedTasks();
        int initialModuleTotalCount = addressBook.getModuleList().get(0).getTotalNumOfTasks();
        int initialExamCompletedCount = addressBook.getExamList().get(0).getNumOfCompletedTasks();
        int initialExamTotalCount = addressBook.getExamList().get(0).getTotalNumOfTasks();

        Task editedTask = new TaskBuilder(TASK_A).withExam(MIDTERM_EXAM).build();
        addressBook.replaceTask(TASK_A, editedTask, true);
        assertEquals(initialModuleCompletedCount, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(initialModuleTotalCount, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertEquals(initialExamCompletedCount, addressBook.getExamList().get(0).getNumOfCompletedTasks());
        assertEquals(initialExamTotalCount + 1, addressBook.getExamList().get(0).getTotalNumOfTasks());
        assertTrue(addressBook.getTaskList().get(0).hasAllSameFields(editedTask));
    }

    @Test
    public void removeTask_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeTask(null));
    }

    @Test
    public void removeTask_linkedTask_success() {
        AddressBook ab = getTypicalAddressBook();
        int initialModuleCompletedCount = ab.getModuleList().get(1).getNumOfCompletedTasks();
        int initialModuleTotalCount = ab.getModuleList().get(1).getTotalNumOfTasks();
        int initialExamCompletedCount = ab.getExamList().get(0).getNumOfCompletedTasks();
        int initialExamTotalCount = ab.getExamList().get(0).getTotalNumOfTasks();

        ab.removeTask(TASK_D);
        assertEquals(initialModuleCompletedCount - 1, ab.getModuleList().get(1).getNumOfCompletedTasks());
        assertEquals(initialModuleTotalCount - 1, ab.getModuleList().get(1).getTotalNumOfTasks());
        assertEquals(initialExamCompletedCount - 1, ab.getExamList().get(0).getNumOfCompletedTasks());
        assertEquals(initialExamTotalCount - 1, ab.getExamList().get(0).getTotalNumOfTasks());
    }

    @Test
    public void removeTask_unlinkedTask_success() {
        AddressBook ab = getTypicalAddressBook();
        int initialModuleCompletedCount = ab.getModuleList().get(0).getNumOfCompletedTasks();
        int initialModuleTotalCount = ab.getModuleList().get(0).getTotalNumOfTasks();

        ab.removeTask(TASK_A);
        assertEquals(initialModuleCompletedCount, ab.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(initialModuleTotalCount - 1, ab.getModuleList().get(0).getTotalNumOfTasks());
    }


    //// util methods

    @Test
    public void resetAllTaskCount() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_D);
        assertEquals(1, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(1, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertEquals(1, addressBook.getExamList().get(0).getNumOfCompletedTasks());
        assertEquals(1, addressBook.getExamList().get(0).getTotalNumOfTasks());

        addressBook.resetAllTaskCount();
        assertEquals(0, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(0, addressBook.getModuleList().get(0).getTotalNumOfTasks());
        assertEquals(0, addressBook.getExamList().get(0).getNumOfCompletedTasks());
        assertEquals(0, addressBook.getExamList().get(0).getTotalNumOfTasks());
    }

    @Test
    public void sortTaskList_nullCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.sortTaskList(null));
    }

    @Test
    public void sortTaskList_success() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_B);
        addressBook.addTask(TASK_A);
        addressBook.sortTaskList(new Criteria("description"));
        List<Task> expectedList = new ArrayList<>(Arrays.asList(TASK_A, TASK_B));
        assertEquals(expectedList, addressBook.getTaskList());
    }

    @Test
    public void unlinkTasksFromExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.unlinkTasksFromExam(null));
    }

    @Test
    public void unlinkTasksFromExam_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_D);
        Task anotherLinkedTask = new TaskBuilder(TASK_C).withExam(MIDTERM_EXAM).build();
        addressBook.addTask(anotherLinkedTask);
        addressBook.unlinkTasksFromExam(MIDTERM_EXAM);
        addressBook.getTaskList().forEach(task -> assertFalse(task.isLinked()));
    }

    @Test
    public void updateExamFieldForTask_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.updateExamFieldForTask(null, FINAL_EXAM));
        assertThrows(NullPointerException.class, () -> addressBook.updateExamFieldForTask(FINAL_EXAM, null));
    }

    @Test
    public void updateExamFieldForTask_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_D);
        Exam editedExam = new ExamBuilder(MIDTERM_EXAM).withDescription("edited exam").build();
        addressBook.updateExamFieldForTask(MIDTERM_EXAM, editedExam);
        assertEquals(editedExam, addressBook.getTaskList().get(0).getExam());
    }

    @Test
    public void isExamLinkedToTask_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.isExamLinkedToTask(null));
    }

    @Test
    public void isExamLinkedToTask_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        assertFalse(addressBook.isExamLinkedToTask(MIDTERM_EXAM));
        addressBook.addTask(TASK_D);
        assertTrue(addressBook.isExamLinkedToTask(MIDTERM_EXAM));
    }

    @Test
    public void updateModuleFieldForTask_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.updateModuleFieldForTask(null, CS2030));
        assertThrows(NullPointerException.class, () -> addressBook.updateModuleFieldForTask(CS2030, null));
    }

    @Test
    public void updateModuleFieldForTask_success() {
        addressBook.addModule(CS2030);
        addressBook.addTask(TASK_A);

        addressBook.setModules(Collections.singletonList(CS2040));
        addressBook.updateModuleFieldForTask(CS2030, CS2040);
        assertEquals(CS2040, addressBook.getTaskList().get(0).getModule());
        assertEquals(0, addressBook.getModuleList().get(0).getNumOfCompletedTasks());
        assertEquals(1, addressBook.getModuleList().get(0).getTotalNumOfTasks());
    }

    @Test
    public void updateModuleFieldForExam_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.updateModuleFieldForExam(null, CS2030));
        assertThrows(NullPointerException.class, () -> addressBook.updateModuleFieldForExam(CS2030, null));
    }

    @Test
    public void updateModuleFieldForExam_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.addTask(TASK_D);

        addressBook.setModules(Collections.singletonList(CS2030));
        addressBook.updateModuleFieldForExam(CS2040, CS2030);
        assertEquals(CS2030, addressBook.getExamList().get(0).getModule());
        assertEquals(addressBook.getExamList().get(0), addressBook.getTaskList().get(0).getExam());

        assertEquals(1, addressBook.getExamList().get(0).getNumOfCompletedTasks());
        assertEquals(1, addressBook.getExamList().get(0).getTotalNumOfTasks());
    }

    @Test
    public void isEqual() {
        AddressBook ab = getTypicalAddressBook();

        // same object -> returns true
        assertTrue(ab.equals(ab));

        // same fields -> return true
        AddressBook abCopy = new AddressBook();
        abCopy.setModules(getTypicalModules());
        abCopy.setExams(getTypicalExams());
        abCopy.setTasks(getTypicalTasks());
        assertTrue(ab.equals(abCopy));

        // different types -> returns false
        assertFalse(ab.equals(5));

        // null -> returns false
        assertFalse(ab.equals(null));

        // different task list -> returns false
        AddressBook abWithDifferentTaskList = new AddressBook();
        abWithDifferentTaskList.setTasks(Collections.singletonList(TASK_D));
        assertFalse(ab.equals(abWithDifferentTaskList));

        // different module list-> returns false
        AddressBook abWithDifferentModuleList = new AddressBook();
        abWithDifferentModuleList.setModules(Collections.singletonList(CS2030));
        assertFalse(ab.equals(abWithDifferentModuleList));

        // different exam list -> returns false
        AddressBook abWithDifferentExamList = new AddressBook();
        abWithDifferentExamList.setExams(Collections.singletonList(MIDTERM_EXAM));
        assertFalse(ab.equals(abWithDifferentExamList));
    }

    @Test
    public void deleteTasksWithModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.deleteTasksWithModule(null));
    }

    @Test
    public void deleteTasksWithModule_success() {
        addressBook.addModule(CS2030);
        addressBook.addModule(CS2040);
        addressBook.addTask(TASK_B);
        addressBook.addTask(TASK_C);
        addressBook.deleteTasksWithModule(CS2040);
        List<Task> expectedList = Collections.singletonList(TASK_B);
        assertEquals(expectedList, addressBook.getTaskList());
    }

    @Test
    public void deleteExamsWithModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.deleteExamsWithModule(null));
    }

    @Test
    public void deleteExamsWithModule_success() {
        addressBook.addModule(CS2040);
        addressBook.addExam(MIDTERM_EXAM);
        addressBook.deleteExamsWithModule(CS2040);
        List<Exam> expectedList = new ArrayList<>();
        assertEquals(expectedList, addressBook.getExamList());
    }

}
