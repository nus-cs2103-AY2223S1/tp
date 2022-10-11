package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ScheduleBuilder;


public class AddScheduleCommandTest {

    @Test
    public void constructor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddScheduleCommand(null));
    }

    @Test
    public void execute_moduleDoesNotExist_throwCommandException() {
        Schedule schedule = new ScheduleBuilder().build();
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(schedule);
        ModelStubWithModuleNotExist modelStub = new ModelStubWithModuleNotExist(schedule);
        assertThrows(CommandException.class, AddScheduleCommand.MESSAGE_MODULE_NOT_EXIST, () ->
                addScheduleCommand.execute(modelStub));
    }

    @Test
    public void execute_conflictWithOtherSchedule_throwCommandException() {
        Schedule schedule = new ScheduleBuilder().build();
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(schedule);
        ModelStubWithScheduleConflict modelStub = new ModelStubWithScheduleConflict(schedule);
        assertThrows(CommandException.class, AddScheduleCommand.MESSAGE_CONFLICT_SCHEDULE, () ->
                addScheduleCommand.execute(modelStub));
    }

    @Test
    public void execute_scheduleAcceptedByModel_addSuccessful() throws CommandException {
        Module module = new ModuleBuilder().build();
        Schedule schedule = new ScheduleBuilder().build();
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(schedule);
        ModelStubWithAcceptingScheduleAdded modelStub = new ModelStubWithAcceptingScheduleAdded(module);
        CommandResult commandResult = addScheduleCommand.execute(modelStub);
        assertEquals(String.format(AddScheduleCommand.MESSAGE_SUCCESS, schedule), commandResult.getFeedbackToUser());
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
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
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public Module getModuleByModuleCode(String moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
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
        private final Schedule schedule;

        ModelStubWithModuleNotExist(Schedule schedule) {
            requireNonNull(schedule);
            this.schedule = schedule;
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
        private final Schedule schedule;

        ModelStubWithScheduleConflict(Schedule schedule) {
            requireNonNull(schedule);
            this.schedule = schedule;
        }

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

    private class ModelStubWithAcceptingScheduleAdded extends ModelStub {
        private final Module module;

        ModelStubWithAcceptingScheduleAdded(Module module) {
            this.module = module;
        }
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
        public void addSchedule(Schedule schedule) {
            module.addSchedule(schedule);
        }
    }
}
