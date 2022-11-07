package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalTasks.TASK_ALPHA;
import static seedu.hrpro.testutil.TypicalTasks.TASK_BRAVO;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.hrpro.commons.core.GuiSettings;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ReadOnlyHrPro;
import seedu.hrpro.model.ReadOnlyUserPrefs;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.UniqueStaffList;
import seedu.hrpro.model.task.Task;
import seedu.hrpro.testutil.TaskBuilder;

public class AddTaskCommandTest {
    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded model = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(model);

        assertEquals(String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, validTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), model.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        ModelStubWithTask model = new ModelStubWithTask(validTask);
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        Task taskAlpha = new TaskBuilder(TASK_ALPHA).build();
        Task taskBravo = new TaskBuilder(TASK_BRAVO).build();
        AddTaskCommand addTaskAlpha = new AddTaskCommand(taskAlpha);
        AddTaskCommand addTaskBravo = new AddTaskCommand(taskBravo);

        // same object -> returns true
        assertTrue(addTaskAlpha.equals(addTaskAlpha));

        // same values -> returns true
        AddTaskCommand addTaskAlphaCopy = new AddTaskCommand(taskAlpha);
        assertTrue(addTaskAlpha.equals(addTaskAlphaCopy));

        // different types -> returns false
        assertFalse(addTaskAlpha.equals(1));

        // null -> returns false
        assertFalse(addTaskAlpha.equals(null));

        // different task -> returns false
        assertFalse(addTaskAlpha.equals(addTaskBravo));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHrProFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHrProFilePath(Path hrProFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHrPro(ReadOnlyHrPro newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHrPro getHrPro() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortProjects() {
            throw new AssertionError(("This method should not be called."));
        }

        @Override
        public void setProject(Project target, Project editedProject) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Project> getProjectWithName(ProjectName projectName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Project> getProjectWithIndex(Index projectIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean targetProjectContainsStaff(Index projectIndex, Staff toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean projectHasDuplicateStaff(ProjectName projectName, Staff toEdit, Staff editWith) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFilteredStaffList(UniqueStaffList staffList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Staff> getFilteredStaffList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStaffList(Predicate<Staff> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Staff> getStaffFromProjectAtIndex(ProjectName projectName, Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isSuccessStaffDelete(Project targetProject, Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStaffToProject(Index projectIndex, Staff toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isSuccessStaffEdit(Project targetProject, Staff toEdit, Staff editWith) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTasks() {
            throw new AssertionError(("This method should not be called."));
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortComplete() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markTask(Index index) {
            throw new AssertionError("this mehtod should not be called.");
        }

        @Override
        public void unmarkTask(Index index) {
            throw new AssertionError("this mehtod should not be called.");
        }

        @Override
        public Optional<Task> getTaskWithIndex(Index taskIndex) {
            throw new AssertionError(("This method should not be called."));
        }

        @Override
        public boolean isValidTaskIndex(Index taskIndex) {
            throw new AssertionError(("This method should not be called."));
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends AddTaskCommandTest.ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends AddTaskCommandTest.ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public ReadOnlyHrPro getHrPro() {
            return new HrPro();
        }
    }

}
