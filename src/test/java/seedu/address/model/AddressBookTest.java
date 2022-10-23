package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProjects.APPLE;
import static seedu.address.testutil.TypicalStaff.STAFF_1;
import static seedu.address.testutil.TypicalTasks.TASK_1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.exceptions.DuplicateStaffException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.TaskBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getProjectList());
        assertEquals(Collections.emptyList(), addressBook.getTaskList());
        assertEquals(Collections.emptyList(), addressBook.getStaffList());
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
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        // Two projects with the same identity fields
        Project editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Project> newProjects = Arrays.asList(APPLE, editedApple);
        AddressBookProjectsStub newData = new AddressBookProjectsStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateStaff_throwsDuplicateStaffException() {
        // Two staffs with the same identity fields
        Staff editedStaff = new StaffBuilder(STAFF_1).withTags(VALID_TAG_FRIEND).build();
        List<Staff> newStaff = Arrays.asList(STAFF_1, editedStaff);
        AddressBookStaffStub newData = new AddressBookStaffStub(newStaff);

        assertThrows(DuplicateStaffException.class, () -> addressBook.resetData(newData));
    }
    
    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task task = new TaskBuilder(TASK_1).withDeadline(VALID_TASKDEADLINE_ALPHA).build();
        List<Task> newTasks = Arrays.asList(TASK_1, task);
        AddressBookTasksStub newData = new AddressBookTasksStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProject(null));
    }

    @Test
    public void hasStaff_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStaff(null));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasProject_projectNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasProject(APPLE));
    }

    @Test
    public void hasStaff_staffNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStaff(STAFF_1));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTask(TASK_1));
    }

    @Test
    public void hasProject_projectInAddressBook_returnsTrue() {
        addressBook.addProject(APPLE);
        assertTrue(addressBook.hasProject(APPLE));
    }

    @Test
    public void hasStaff_staffInAddressBook_returnsTrue() {
        addressBook.addStaff(STAFF_1);
        assertTrue(addressBook.hasStaff(STAFF_1));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        addressBook.addTask(TASK_1);
        assertTrue(addressBook.hasTask(TASK_1));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addProject(APPLE);
        Project editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasProject(editedApple));
    }

    @Test
    public void hasStaff_staffWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStaff(STAFF_1);
        Staff editedStaff = new StaffBuilder(STAFF_1).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasStaff(editedStaff));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTask(TASK_1);
        Task editedTask = new TaskBuilder(TASK_1).withDeadline(VALID_TASKDEADLINE_BRAVO)
                .build();
        assertTrue(addressBook.hasTask(editedTask));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProjectList().remove(0));
    }

    @Test
    public void getStaffList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStaffList().remove(0));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose projects list can violate interface constraints.
     */
    private static class AddressBookProjectsStub implements ReadOnlyAddressBook {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Staff> staff = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        AddressBookProjectsStub(Collection<Project> projects) {
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Staff> getStaffList() {
            return staff;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose staff list can violate interface constraints.
     */
    private static class AddressBookStaffStub implements ReadOnlyAddressBook {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Staff> staff = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        AddressBookStaffStub(Collection<Staff> staff) {
            this.staff.setAll(staff);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Staff> getStaffList() {
            return staff;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose task list can violate interface constraints.
     */
    private static class AddressBookTasksStub implements ReadOnlyAddressBook {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Staff> staff = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        AddressBookTasksStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Staff> getStaffList() {
            return staff;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }
}
