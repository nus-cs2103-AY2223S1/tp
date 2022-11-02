package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.getTypicalProfNusWithSchedules;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyProfNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Weekdays;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditScheduleCommand.
 */
public class EditScheduleCommandTest {

    private Model model = new ModelManager(getTypicalProfNusWithSchedules(), new UserPrefs());

    @Test
    public void constructor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditScheduleCommand(null, null));
    }

    @Test
    public void execute_moduleDoesNotExist_throwCommandException() {
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptorBuilder().withModule("CS2309S")
                .build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE, editScheduleDescriptor);
        ModelStubWithModuleNotExist modelStub = new ModelStubWithModuleNotExist();
        assertThrows(CommandException.class, EditScheduleCommand.MESSAGE_MODULE_NOT_EXIST, () ->
                editScheduleCommand.execute(model));
    }

    @Test
    public void execute_conflictWithOtherSchedule_throwCommandException() throws CommandException {
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptorBuilder().withStartTime("16:00")
                .withEndTime("18:00").withWeekday(Weekdays.Friday).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE, editScheduleDescriptor);
        ModelStubWithScheduleConflict modelStub = new ModelStubWithScheduleConflict();
        // CommandResult result = editScheduleCommand.execute(modelStub);
        assertThrows(CommandException.class, EditScheduleCommand.MESSAGE_CONFLICT_SCHEDULE, () ->
                editScheduleCommand.execute(model));
    }

    @Test
    public void execute_invalidScheduleIndex_throwCommandException() {
        Index indexOutOfBound = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptorBuilder().build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(indexOutOfBound, editScheduleDescriptor);
        assertThrows(CommandException.class, EditScheduleCommand.MESSAGE_SCHEDULE_NOT_EXIST, () -> editScheduleCommand
                .execute(model));
    }

    /**
     * A default model stub that have all the methods failing.
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
        public Path getProfNusFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProfNusFilePath(Path profNusFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setSchedule(Schedule target, Schedule editedSchedule) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public Module getModuleByModuleCode(String moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProfNus(ReadOnlyProfNus newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProfNus getProfNus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean conflictSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean conflictScheduleWithTarget(Schedule schedule, Schedule target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearSchedules(ArrayList<ModuleCode> keywords) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutor(Student tutor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutor(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutor(Student tutor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutor(Student target, Student editedTutor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewModule(ModuleCode moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewModuleDetails(ModuleCode moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getAllPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredTutorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getAllTutorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getAllModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getAllScheduleList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a schedule which cannot find the target module
     */
    private class ModelStubWithModuleNotExist extends ModelStub {
        private List<Schedule> filteredScheduleList;
        ModelStubWithModuleNotExist() {
            filteredScheduleList = new ArrayList<>();
            filteredScheduleList.add(new ScheduleBuilder().build());
        }

        @Override
        public Module getModuleByModuleCode(String moduleCode) {
            requireNonNull(moduleCode);
            return null;
        }

        @Override
        public boolean conflictSchedule(Schedule schedule) {
            return false;
        }
    }

    /**
     * A Model stub that always has schedule conflict.
     */
    private class ModelStubWithScheduleConflict extends ModelStub {
        ModelStubWithScheduleConflict() {}

        @Override
        public Module getModuleByModuleCode(String moduleCode) {
            requireNonNull(moduleCode);
            return new ModuleBuilder().build();
        }

        @Override
        public boolean conflictSchedule(Schedule schedule) {
            return true;
        }
    }

    private class ModelStubWithAcceptingScheduleEdited extends ModelStub {

        ModelStubWithAcceptingScheduleEdited() {}
        @Override
        public Module getModuleByModuleCode(String moduleCode) {
            requireNonNull(moduleCode);
            return new ModuleBuilder().build();
        }

        @Override
        public boolean conflictSchedule(Schedule schedule) {
            return false;
        }

        @Override
        public void setSchedule(Schedule target, Schedule editedSchedule) {
            target.setModule(editedSchedule.getModule());
            target.setVenue(editedSchedule.getVenue());
            target.setStartTime(editedSchedule.getStartTime());
            target.setEndTime(editedSchedule.getEndTime());
            target.setWeekday(editedSchedule.getWeekday());
            target.setClassType(editedSchedule.getClassType());
        }
    }
}

