package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Unmodifiable view of profNus
 */
public interface ReadOnlyProfNus {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tutors list.
     * This list will not contain any duplicate tutors.
     */
    ObservableList<Student> getTutorList();

    /**
     * Returns an unmodifiable view of the module list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the module schedule list.
     * This list will not contain any duplicate module schedules.
     * @return
     */
    ObservableList<Schedule> getScheduleList();
}
