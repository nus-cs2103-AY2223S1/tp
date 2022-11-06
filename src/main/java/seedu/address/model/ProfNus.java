package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueTutorList;

/**
 * Wraps all data at the profNus level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ProfNus implements ReadOnlyProfNus {

    private final UniquePersonList persons;
    private final UniqueModuleList modules;
    private final UniqueTutorList tutors;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    {
        modules = new UniqueModuleList();
    }

    {
        tutors = new UniqueTutorList();
    }

    public ProfNus() {}

    /**
     * Creates an ProfNus using the Persons in the {@code toBeCopied}
     */
    public ProfNus(ReadOnlyProfNus toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the contents of the tutor list with {@code tutors}.
     * {@code tutors} must not contain duplicate tutors.
     */
    public void setTutors(List<Student> tutors) {
        this.tutors.setTutors(tutors);
    }

    /**
     * Resets the existing data of this {@code ProfNus} with {@code newData}.
     */
    public void resetData(ReadOnlyProfNus newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setModules(newData.getModuleList());
        setTutors(newData.getTutorList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the ProfNUS.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a tutor with the same identity as {@code tutor} exists in the ProfNUS.
     */
    public boolean hasTutor(Student tutor) {
        requireNonNull(tutor);
        return tutors.contains(tutor);
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in the ProfNUS.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Checks if the new schedule to be added conflicts with existing ones
     * @param newSchedule new schedule to be added
     * @return true if conflicts; otherwise, false.
     */
    public boolean conflictSchedule(Schedule newSchedule) {
        requireNonNull(newSchedule);
        for (Module module: modules) {
            if (module.conflictAnySchedule(newSchedule)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the new schedule to be added conflicts with existing ones except the target
     * @param editedSchedule edited schedule
     * @param target target schedule
     * @return true if conflicts; otherwise, false.
     */
    public boolean conflictScheduleWithTarget(Schedule editedSchedule, Schedule target) {
        for (Module module: modules) {
            List<Schedule> schedules = module.getSchedules();
            for (Schedule s: schedules) {
                if (!s.equals(target) && s.isConflictWith(editedSchedule)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a person to profNUS.
     * The person must not already exist in profNUS.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a tutor to profNUS.
     * The tutor must not already exist in profNUS.
     */
    public void addTutor(Student p) {
        tutors.add(p);
    }

    /**
     * Adds a module to profNus.
     * The module must not already exist in profNUS.
     */
    public void addModule(Module m) {
        modules.add(m);
    }

    /**
     * Finds the module by the module code
     * @param moduleCode module code
     * @return target module
     */
    public Module getModuleByModuleCode(String moduleCode) {
        for (Module module: modules) {
            if (module.getCode().toString().toLowerCase().equals(moduleCode.toLowerCase())) {
                return module;
            }
        }
        return null;
    }

    /**
     * Adds a new schedule to a module
     * @param s new schedule
     */
    public void addSchedule(Schedule s) {
        Module targetModule = getModuleByModuleCode(s.getModule());
        targetModule.addSchedule(s);
    }

    /**
     * Replaces the given schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in ProfNUS.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        target.setModule(editedSchedule.getModule());
        target.setVenue(editedSchedule.getVenue());
        target.setStartTime(editedSchedule.getStartTime());
        target.setEndTime(editedSchedule.getEndTime());
        target.setWeekday(editedSchedule.getWeekday());
        target.setClassType(editedSchedule.getClassType());
        target.setClassGroup(editedSchedule.getClassGroup());
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in ProfNUS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in ProfNUS.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given tutor {@code target} in the list with {@code editedTutor}.
     * {@code target} must exist in ProfNUS.
     * The person identity of {@code editedTutor} must not be the same as another existing tutor in ProfNUS.
     */
    public void setTutor(Student target, Student editedTutor) {
        requireNonNull(editedTutor);

        tutors.setTutor(target, editedTutor);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in ProfNUS.
     * The module identity of {@code editedModule} must not be the same as another existing module in ProfNUS.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);
        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code ProfNus}.
     * {@code key} must exist in ProfNUS.
     */
    public void removeSchedule(Schedule key) {
        Module module = getModuleByModuleCode(key.getModule());
        module.deleteSchedule(key);
    }


    /**
     * Removes all selected schedules from this {@code ProfNus}.
     */
    public void clearSchedules(ArrayList<ModuleCode> modulesToClear) {
        if (modulesToClear.isEmpty()) {
            for (Module module : getModuleList()) {
                module.clearSchedules();
            }
        } else {
            for (Module module : getModuleList()) {
                for (ModuleCode moduleCode : modulesToClear) {
                    if (module.getCode().fullCode.equals(moduleCode.fullCode.toUpperCase())) {
                        module.clearSchedules();
                    }
                }
            }
        }

    }

    /**
     * Removes {@code key} from this {@code ProfNus}.
     * {@code key} must exist in ProfNUS.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code ProfNus}.
     * {@code key} must exist in ProfNUS.
     */
    public void removeTutor(Student key) {
        tutors.remove(key);
    }

    /**
     * Removes {@code key} from this {@code ProfNus}.
     * {@code key} must exist in ProfNUS.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Student> getTutorList() {
        return tutors.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        ObservableList<Schedule> l = modules.getUnmodifiableObservableScheduleList();
        return modules.getUnmodifiableObservableScheduleList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfNus // instanceof handles nulls
                && persons.equals(((ProfNus) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }


}
