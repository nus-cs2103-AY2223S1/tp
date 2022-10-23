package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.UniqueConsultationList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.UniqueReminderList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.UniqueTutorialList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList persons;
    private final UniqueReminderList reminders;
    private final UniqueTutorialList tutorials;
    private final UniqueConsultationList consultations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueStudentList();
        reminders = new UniqueReminderList();
        tutorials = new UniqueTutorialList();
        consultations = new UniqueConsultationList();
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
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Student> students) {
        this.persons.setPersons(students);
    }

    /**
     * Replaces the contents of the reminders list with {@code reminders}.
     * {@code reminders} must not contain duplicate reminders.
     */
    public void setReminders(List<Reminder> reminders) {
        this.reminders.setReminders(reminders);
    }


    /**
     * Replaces the contents of the tutorial list with {@code tutorials}.
     * {@code tutorials} must not contain duplicate tutorials.
     */
    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials.setTutorials(tutorials);
    }

    /**
     * Replaces the contents of the consultation list with {@code consultations}.
     * {@code tutorials} must not contain duplicate consultations.
     */
    public void setConsultations(List<Consultation> consultations) {
        this.consultations.setConsultations(consultations);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());

        setReminders(newData.getReminderList());

        setTutorials(newData.getTutorialList());

        setConsultations(newData.getConsultationList());

    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return persons.contains(student);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Student p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        persons.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Student key) {
        persons.remove(key);
    }

    //// reminder-level operations

    /**
     * Returns true if a reminder with the same identity as {@code reminder} exists in the ModQuilk.
     */
    public boolean hasReminder(Reminder reminder) {
        requireNonNull(reminder);
        return reminders.contains(reminder);
    }

    /**
     * Adds a reminder to the ModQuik.
     * The reminder must not already exist in the ModQuik.
     */
    public void addReminder(Reminder r) {
        reminders.add(r);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeReminder(Reminder key) {
        reminders.remove(key);
    }


    //// tutorial-level operations

    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in the ModQuilk.
     */
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.contains(tutorial);
    }

    /**
     * Returns true if a tutorial with the same venue and timeslot as {@code tutorial} exists in the ModQuik.
     */
    public boolean hasTutorialClashingWith(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.containsClashingWith(tutorial);
    }

    /**
     * Adds a tutorial to the ModQuik.
     * The tutorial must not already exist in the ModQuik.
     */
    public void addTutorial(Tutorial t) {
        tutorials.add(t);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTutorial(Tutorial key) {
        tutorials.remove(key);
    }
    //// consultation-level operations
    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in the ModQuilk.
     */
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return consultations.contains(consultation);
    }

    /**
     * Returns true if a Consultation with the same venue and timeslot as {@code Consultation} exists in the ModQuik.
     */
    public boolean hasConsultationClashingWith(Consultation consultation) {
        requireNonNull(consultation);
        return consultations.containsClashingWith(consultation);
    }

    /**
     * Adds a Consultation to the ModQuik.
     * The consultation must not already exist in the ModQuik.
     */
    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeConsultation(Consultation key) {
        consultations.remove(key);
    }

    /**
     * Returns an array containing number of specific grade.
     */
    public int[] getGradeData() {
        int[] gradeArr = new int[5];
        for (Student student : persons) {
            String grade = student.getGrade().value;
            switch (grade) {
            case "A":
                gradeArr[0]++;
                break;
            case "B":
                gradeArr[1]++;
                break;
            case "C":
                gradeArr[2]++;
                break;
            case "D":
                gradeArr[3]++;
                break;
            case "F":
                gradeArr[4]++;
                break;
            default:
                break;
            }
        }
        return gradeArr;
    }

    //// util methods

    @Override
    public String toString() {
        String result = persons.asUnmodifiableObservableList().size() + " persons, "
                + tutorials.asUnmodifiableObservableList().size() + " tutorials"
                + consultations.asUnmodifiableObservableList().size() + " consultations"
                + reminders.asUnmodifiableObservableList().size() + " reminders";

        return result;
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Reminder> getReminderList() {
        return reminders.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tutorial> getTutorialList() {
        return tutorials.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Consultation> getConsultationList() {
        return consultations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
