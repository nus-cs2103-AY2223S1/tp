package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_TUTORS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' ProfNUS file path.
     */
    Path getProfNusFilePath();

    /**
     * Sets the user prefs' ProfNUS file path.
     */
    void setProfNusFilePath(Path addressBookFilePath);

    /**
     * Replaces ProfNUS data with the data in {@code profNus}.
     */
    void setProfNus(ReadOnlyProfNus profNus);

    /** Returns the ProfNus */
    ReadOnlyProfNus getProfNus();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the ProfNUS.
     */
    boolean hasPerson(Person person);

    /**
     * Checks if the new schedule conflicts with existing schedules.
     */
    boolean conflictSchedule(Schedule schedule);

    /**
     * Checks if the new schedule conflicts with existing schedules except the target.
     */
    boolean conflictScheduleWithTarget(Schedule schedule, Schedule target);

    /**
     * Deletes the given person.
     * The person must exist in the ProfNUS.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the ProfNUS.
     */
    void addPerson(Person person);

    /**
     * Adds a new schedule.
     */
    void addSchedule(Schedule schedule);

    /**
     * Updates the {@code target} with information given by the {@code editedSchedule}.
     */
    void setSchedule(Schedule target, Schedule editedSchedule);

    /**
     * Deletes the given schedule.
     * The schedule must exist in the ProfNUS.
     */
    void deleteSchedule(Schedule target);

    /**
     * Clears all schedules.
     */
    void clearSchedules(ArrayList<ModuleCode> keywords);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the ProfNUS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the ProfNUS.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if a module with the same identity as {@code module} exists in ProfNUS.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in ProfNUS.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in ProfNUS.
     */
    void addModule(Module module);

    /**
     * Finds the module by the given module code.
     */
    Module getModuleByModuleCode(String moduleCode);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in profNUS.
     * The module identity of {@code editedModule} must not be the same as another existing module in ProfNUS.
     */
    void setModule(Module target, Module editedModule);

    /**
     * Returns true if a tutor with the same identity as {@code tutor} exists in ProfNUS.
     */
    boolean hasTutor(Student tutor);

    /**
     * Deletes the given tutor.
     * The tutor must exist in ProfNUS.
     */
    void deleteTutor(Student target);

    /**
     * Adds the given tutor.
     * {@code tutor} must not already exist in ProfNUS.
     */
    void addTutor(Student tutor);

    /**
     * Replaces the given tutor {@code target} with {@code editedTutor}.
     * {@code target} must exist in profNUS.
     * The tutor identity of {@code editedTutor} must not be the same as another existing tutor in ProfNUS.
     */
    void setTutor(Student target, Student editedTutor);

    /**
     * Views the module with {@code ModuleCode}.
     */
    void viewModule(ModuleCode moduleCode);

    /**
     * Views the module details with {@code ModuleCode}.
     */
    void viewModuleDetails(ModuleCode moduleCode);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the whole person list */

    ObservableList<Person> getAllPersonList();

    /** Returns an unmodifiable view of the filtered tutor list */
    ObservableList<Student> getFilteredTutorList();

    /** Returns an unmodifiable view of the all tutor list */
    ObservableList<Student> getAllTutorList();

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the whole module list */

    ObservableList<Module> getAllModuleList();

    /** Returns an unmodifiable view of the filtered module schedule list */
    ObservableList<Schedule> getFilteredScheduleList();

    /** Returns an unmodifiable view of the whole module schedule list */
    ObservableList<Schedule> getAllScheduleList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered tutor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates the filter of the filtered module schedule list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Schedule> predicate);



}
