package seedu.hrpro.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;
import static seedu.hrpro.testutil.TypicalProjects.APPLE;
import static seedu.hrpro.testutil.TypicalStaff.STAFF_1;
import static seedu.hrpro.testutil.TypicalTasks.TASK_1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.exceptions.DuplicateProjectException;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.exceptions.DuplicateStaffException;
import seedu.hrpro.model.task.Task;
import seedu.hrpro.model.task.exceptions.DuplicateTaskException;
import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;
import seedu.hrpro.testutil.TaskBuilder;

public class HrProTest {

    private final HrPro hrPro = new HrPro();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hrPro.getProjectList());
        assertEquals(Collections.emptyList(), hrPro.getTaskList());
        assertEquals(Collections.emptyList(), hrPro.getStaffList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrPro.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHrPro_replacesData() {
        HrPro newData = getTypicalHrPro();
        hrPro.resetData(newData);
        assertEquals(newData, hrPro);
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        // Two projects with the same identity fields
        Project editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Project> newProjects = Arrays.asList(APPLE, editedApple);
        HrProProjectsStub newData = new HrProProjectsStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> hrPro.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateStaff_throwsDuplicateStaffException() {
        // Two staffs with the same identity fields
        Staff editedStaff = new StaffBuilder(STAFF_1).withTags(VALID_TAG_FRIEND).build();
        List<Staff> newStaff = Arrays.asList(STAFF_1, editedStaff);
        HrProStaffStub newData = new HrProStaffStub(newStaff);

        assertThrows(DuplicateStaffException.class, () -> hrPro.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task task = new TaskBuilder(TASK_1).withDeadline(VALID_TASKDEADLINE_ALPHA).build();
        List<Task> newTasks = Arrays.asList(TASK_1, task);
        HrProTasksStub newData = new HrProTasksStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> hrPro.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrPro.hasProject(null));
    }

    @Test
    public void hasStaff_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrPro.hasStaff(null));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrPro.hasTask(null));
    }

    @Test
    public void hasProject_projectNotInHrPro_returnsFalse() {
        assertFalse(hrPro.hasProject(APPLE));
    }

    @Test
    public void hasStaff_staffNotInHrPro_returnsFalse() {
        assertFalse(hrPro.hasStaff(STAFF_1));
    }

    @Test
    public void hasTask_taskNotInHrPro_returnsFalse() {
        assertFalse(hrPro.hasTask(TASK_1));
    }

    @Test
    public void hasProject_projectInHrPro_returnsTrue() {
        hrPro.addProject(APPLE);
        assertTrue(hrPro.hasProject(APPLE));
    }

    @Test
    public void hasStaff_staffInHrPro_returnsTrue() {
        hrPro.addStaff(STAFF_1);
        assertTrue(hrPro.hasStaff(STAFF_1));
    }

    @Test
    public void hasTask_taskInHrPro_returnsTrue() {
        hrPro.addTask(TASK_1);
        assertTrue(hrPro.hasTask(TASK_1));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInHrPro_returnsTrue() {
        hrPro.addProject(APPLE);
        Project editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hrPro.hasProject(editedApple));
    }

    @Test
    public void hasStaff_staffWithSameIdentityFieldsInHrPro_returnsTrue() {
        hrPro.addStaff(STAFF_1);
        Staff editedStaff = new StaffBuilder(STAFF_1).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hrPro.hasStaff(editedStaff));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInHrPro_returnsTrue() {
        hrPro.addTask(TASK_1);
        Task editedTask = new TaskBuilder(TASK_1).withDeadline(VALID_TASKDEADLINE_BRAVO)
                .build();
        assertTrue(hrPro.hasTask(editedTask));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrPro.getProjectList().remove(0));
    }

    @Test
    public void getStaffList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrPro.getStaffList().remove(0));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrPro.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyHrPro whose projects list can violate interface constraints.
     */
    private static class HrProProjectsStub implements ReadOnlyHrPro {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Staff> staff = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        HrProProjectsStub(Collection<Project> projects) {
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
     * A stub ReadOnlyHrPro whose staff list can violate interface constraints.
     */
    private static class HrProStaffStub implements ReadOnlyHrPro {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Staff> staff = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        HrProStaffStub(Collection<Staff> staff) {
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
     * A stub ReadOnlyHrPro whose task list can violate interface constraints.
     */
    private static class HrProTasksStub implements ReadOnlyHrPro {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Staff> staff = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        HrProTasksStub(Collection<Task> tasks) {
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
