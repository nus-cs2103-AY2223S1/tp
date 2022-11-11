package seedu.modquik.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.consultation.UniqueConsultationList;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.reminder.UniqueReminderList;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.UniqueStudentList;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.model.tutorial.UniqueTutorialList;

/**
 * Wraps all data at the modquik level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ModQuik implements ReadOnlyModQuik {

    private final UniqueStudentList students;
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
        students = new UniqueStudentList();
        reminders = new UniqueReminderList();
        tutorials = new UniqueTutorialList();
        consultations = new UniqueConsultationList();
    }

    public ModQuik() {}

    /**
     * Creates an ModQuik using the Persons in the {@code toBeCopied}
     */
    public ModQuik(ReadOnlyModQuik toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the students list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
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
     * Resets the existing data of this {@code ModQuik} with {@code newData}.
     */
    public void resetData(ReadOnlyModQuik newData) {
        requireNonNull(newData);

        setStudents(newData.getPersonList());

        setReminders(newData.getReminderList());

        setTutorials(newData.getTutorialList());

        setConsultations(newData.getConsultationList());

    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in ModQuik.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to ModQuik.
     * The student must not already exist in ModQuik.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in ModQuik.
     * The student identity of {@code editedStudent} must not be the same as another existing student in ModQuik.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code ModQuik}.
     * {@code key} must exist in the ModQuik.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// reminder-level operations

    /**
     * Returns true if a reminder with the same identity as {@code reminder} exists in the ModQuik.
     */
    public boolean hasReminder(Reminder reminder) {
        requireNonNull(reminder);
        return reminders.contains(reminder);
    }

    /**
     * Adds a reminder to ModQuik.
     * The reminder must not already exist in ModQuik.
     */
    public void addReminder(Reminder r) {
        reminders.add(r);
    }

    /**
     * Replaces the given reminder {@code target} in the list with {@code editedReminder}.
     * {@code target} must exist in ModQuik.
     * The reminder identity of {@code editedReminder} must not be the same as another existing person in ModQuik.
     */
    public void setReminder(Reminder target, Reminder editedReminder) {
        requireNonNull(editedReminder);

        reminders.setReminder(target, editedReminder);
    }

    /**
     * Removes {@code key} from this {@code ModQuik}.
     * {@code key} must exist in ModQuik.
     */
    public void removeReminder(Reminder key) {
        reminders.remove(key);
    }

    /**
     * Sorts reminder by priority in the list. Reminders with the same priority will be sorted lexicographically by
     * their names.
     */
    public void sortRemindersByPriority() {
        reminders.sortRemindersByPriority();
    }

    public void sortRemindersByDeadline() {
        reminders.sortRemindersByDeadline();
    }

    public void markReminder(Reminder target) {
        reminders.mark(target);
    }

    public void unmarkReminder(Reminder target) {
        reminders.unmark(target);
    }

    //// tutorial-level operations

    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in the ModQuik.
     */
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.contains(tutorial);
    }

    /**
     * Returns the true if there exists tutorials in ModQuik having timeslot overlapping with an {@code tutorial}.
     */
    public boolean hasClashingTutorial(Tutorial toCheck) {
        requireNonNull(toCheck);
        return tutorials.hasClashingTutorial(toCheck);
    }

    /**
     * Returns true if there exists tutorials exclude {@code exception} in ModQuik having timeslot overlapping with
     * an {@code tutorial}
     */
    public boolean hasClashingTutorialExcept(Tutorial toCheck, Tutorial exception) {
        requireNonNull(toCheck);
        requireNonNull(exception);
        return tutorials.hasClashingTutorialExcept(toCheck, exception);
    }

    /**
     * Adds a tutorial to ModQuik.
     * The tutorial must not already exist in ModQuik.
     */
    public void addTutorial(Tutorial t) {
        tutorials.add(t);
    }

    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireNonNull(editedTutorial);

        tutorials.setTutorial(target, editedTutorial);
    }

    /**
     * Removes {@code key} from this {@code ModQuik}.
     * {@code key} must exist in the ModQuik.
     */
    public void removeTutorial(Tutorial key) {
        tutorials.remove(key);
    }
    //// consultation-level operations
    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in ModQuik.
     */
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return consultations.contains(consultation);
    }

    /**
     * Returns the true if there exists consultations in ModQuik having timeslot overlapping
     * with an {@code consultation}.
     */
    public boolean hasClashingConsultation(Consultation toCheck) {
        requireNonNull(toCheck);
        return consultations.hasClashingConsultation(toCheck);
    }

    /**
     * Returns true if there exists consultations exclude {@code exception} in ModQuik having timeslot overlapping with
     * an {@code consultation}
     */
    public boolean hasClashingConsultationExcept(Consultation toCheck, Consultation exception) {
        requireNonNull(toCheck);
        requireNonNull(exception);
        return consultations.hasClashingConsultationExcept(toCheck, exception);
    }

    /**
     * Adds a Consultation to ModQuik.
     * The consultation must not already exist in ModQuik.
     */
    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

    /**
     * Removes {@code key} from this {@code ModQuik}.
     * {@code key} must exist in the ModQuik.
     */
    public void removeConsultation(Consultation key) {
        consultations.remove(key);
    }

    /**
     * Replaces the given consultation {@code target} in the list with {@code editedConsultation}.
     * {@code target} must exist in ModQuik.
     * The consultation identity of {@code editedConsultation} must not be the same as another existing consultation
     * in ModQuik.
     */
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireNonNull(editedConsultation);
        consultations.setConsultation(target, editedConsultation);
    }

    /**
     * Returns an array containing number of specific grade.
     */
    public int[] getGradeData() {
        int[] gradeArr = new int[5];
        for (Student student : students) {
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
        String result = students.asUnmodifiableObservableList().size() + " students, "
                + tutorials.asUnmodifiableObservableList().size() + " tutorials"
                + consultations.asUnmodifiableObservableList().size() + " consultations"
                + reminders.asUnmodifiableObservableList().size() + " reminders";

        return result;
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getPersonList() {
        return students.asUnmodifiableObservableList();
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
                || (other instanceof ModQuik // instanceof handles nulls
                && students.equals(((ModQuik) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
