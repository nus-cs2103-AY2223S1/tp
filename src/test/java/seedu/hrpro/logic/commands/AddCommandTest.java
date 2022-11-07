package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

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
import seedu.hrpro.testutil.ProjectBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_projectAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProjectAdded modelStub = new ModelStubAcceptingProjectAdded();
        Project validProject = new ProjectBuilder().build();

        CommandResult commandResult = new AddCommand(validProject).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validProject), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProject), modelStub.projectsAdded);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() {
        Project validProject = new ProjectBuilder().build();
        AddCommand addCommand = new AddCommand(validProject);
        ModelStub modelStub = new ModelStubWithProject(validProject);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PROJECT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Project alice = new ProjectBuilder().withName("Alice").build();
        Project bob = new ProjectBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different project -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public void setFilteredStaffList(UniqueStaffList staffList) {
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
        public ObservableList<Staff> getFilteredStaffList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStaffList(Predicate<Staff> predicate) {
            throw new AssertionError("This method should not be called.");
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
        public void sortTasks() {
            throw new AssertionError(("This method should not be called."));
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
     * A Model stub that contains a single project.
     */
    private class ModelStubWithProject extends ModelStub {
        private final Project project;

        ModelStubWithProject(Project project) {
            requireNonNull(project);
            this.project = project;
        }

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return this.project.isSameProject(project);
        }
    }

    /**
     * A Model stub that always accept the project being added.
     */
    private class ModelStubAcceptingProjectAdded extends ModelStub {
        final ArrayList<Project> projectsAdded = new ArrayList<>();

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return projectsAdded.stream().anyMatch(project::isSameProject);
        }

        @Override
        public void addProject(Project project) {
            requireNonNull(project);
            projectsAdded.add(project);
        }

        @Override
        public ReadOnlyHrPro getHrPro() {
            return new HrPro();
        }
    }

}
