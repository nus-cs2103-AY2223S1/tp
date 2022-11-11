package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.SortCommand.SortBy;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.person.tutor.UniqueTutorList;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.tuitionclass.UniqueTuitionClassList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniqueStudentList students;
    private final UniqueTutorList tutors;
    private final UniqueTuitionClassList tuitionClasses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        tutors = new UniqueTutorList();
        tuitionClasses = new UniqueTuitionClassList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the tutor list with {@code tutors}.
     * {@code tutors} must not contain duplicate tutors.
     */
    public void setTutors(List<Tutor> tutors) {
        this.tutors.setTutors(tutors);
    }


    /**
     * Replaces the contents of the tuition class list with {@code tuitionClasses}.
     * {@code tuitionClasses} must not contain duplicate tuition classes.
     */
    public void setTuitionClasses(List<TuitionClass> tuitionClasses) {
        this.tuitionClasses.setTuitionClasses(tuitionClasses);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setTutors(newData.getTutorList());
        setTuitionClasses(newData.getTuitionClassList());
    }

    //// person-level operations

    /**
     * Returns true if the student or tutor with the same identity as {@code person}
     * exists in the respective list in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        if (person instanceof Student) {
            return students.contains((Student) person);
        } else {
            assert(person instanceof Tutor);
            return tutors.contains((Tutor) person);
        }
    }

    /**
     * Adds a student or tutor to their respective list in the address book.
     * The student or tutor must not already exist in the address book.
     */
    public void addPerson(Person p) {
        if (p instanceof Student) {
            students.add((Student) p);
        } else {
            assert(p instanceof Tutor);
            tutors.add((Tutor) p);
        }
    }

    /**
     * Replaces the given student or tutor {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        if (target instanceof Student && editedPerson instanceof Student) {
            Student editedStudent = (Student) editedPerson;
            editedStudent.updateTimeAddedToList();
            students.setStudent((Student) target, editedStudent);
        } else {
            assert(target instanceof Tutor && editedPerson instanceof Tutor);
            Tutor editedTutor = (Tutor) editedPerson;
            editedTutor.updateTimeAddedToList();
            tutors.setTutor((Tutor) target, editedTutor);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        if (key instanceof Student) {
            Student student = (Student) key;
            student.minusId();
            students.remove(student);
        } else {
            assert(key instanceof Tutor);
            Tutor tutor = (Tutor) key;
            tutor.minusId();
            tutors.remove(tutor);
        }
    }

    // tuition class level operations

    /**
     * Returns true if a tuition class with the same identity as {@code tuitionClass}
     * exists in the address book.
     */
    public boolean hasTuitionClass(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return tuitionClasses.contains(tuitionClass);
    }

    /**
     * Adds a tuition class to the address book.
     * The tuition class must not already exist in the address book.
     */
    public void addTuitionClass(TuitionClass tuitionClass) {
        tuitionClasses.add(tuitionClass);
    }

    /**
     * Replaces the given tuition class {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the address book.
     * The tuition class identity of {@code editedClass} must not be the same as another existing
     * tuition class in the address book.
     */
    public void setTuitionClass(TuitionClass target, TuitionClass editedClass) {
        requireNonNull(editedClass);
        editedClass.updateTimeAddedToList();
        tuitionClasses.setTuitionClass(target, editedClass);

        //find and edit every person in this class
        for (Student student : students) {
            List<TuitionClass> classList = student.getTuitionClasses();
            int targetIndex = classList.indexOf(target);
            if (targetIndex < 0) {
                continue;
            }
            classList.set(targetIndex, editedClass);
        }

        for (Tutor tutor : tutors) {
            List<TuitionClass> classList = tutor.getTuitionClasses();
            int targetIndex = classList.indexOf(target);
            if (targetIndex < 0) {
                continue;
            }
            classList.set(targetIndex, editedClass);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTuitionClass(TuitionClass key) {
        key.minusId();
        tuitionClasses.remove(key);

        //find and edit every person in this class
        for (Student student : students) {
            List<TuitionClass> classList = student.getTuitionClasses();
            int targetIndex = classList.indexOf(key);
            if (targetIndex < 0) {
                continue;
            }
            classList.remove(targetIndex);
        }

        for (Tutor tutor : tutors) {
            List<TuitionClass> classList = tutor.getTuitionClasses();
            int targetIndex = classList.indexOf(key);
            if (targetIndex < 0) {
                continue;
            }
            classList.remove(targetIndex);
        }
    }

    /**
     * Returns the tuition class from the tuition class list if the tuition class name
     * matches the specified {@code name}.
     * @return the tuition class that has the same name as the specified {@code name}.
     */
    public TuitionClass getTuitionClass(Name name) {
        return tuitionClasses.getTuitionClass(name);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableStudentList().size() + " students, "
                + tutors.asUnmodifiableObservableTutorList().size() + " tutors and "
                + tuitionClasses.asUnmodifiableObservableList().size() + " tuition classes.";
        // TODO: refine later
    }


    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableStudentList();
    }

    @Override
    public ObservableList<Tutor> getTutorList() {
        return tutors.asUnmodifiableObservableTutorList();
    }

    @Override
    public ObservableList<TuitionClass> getTuitionClassList() {
        return tuitionClasses.asUnmodifiableObservableList();
    }

    public void sortStudentList(SortBy method) {
        students.sort(method);
    }

    public void sortTutorList(SortBy method) {
        tutors.sort(method);
    }

    public void sortTuitionClassList(SortBy method) {
        tuitionClasses.sort(method);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students)
                && tutors.equals(((AddressBook) other).tutors)
                && tuitionClasses.equals(((AddressBook) other).tuitionClasses));
    }

    @Override
    public int hashCode() {
        return students.hashCode() * tutors.hashCode() * tuitionClasses.hashCode() * 29;
    }
}
