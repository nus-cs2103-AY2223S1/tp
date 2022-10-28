package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a Lesson in JARVIS.
 * Guarantees: details are present and not null.
 */
public abstract class Lesson {

    // Identity fields
    private final LessonDesc lessonDesc;
    private final TimePeriod timePeriod;
    private final ArrayList<Student> studentList;
    private final ObservableList<Student> observableStudentList;

    // Data fields
    private final LessonAttendance attendance;
    private final LessonNotes notes;
    private boolean isCompleted = false;
    private boolean hasClash = false;

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonDesc lessonDesc, TimePeriod timePeriod, Collection<Student> students) {
        requireAllNonNull(timePeriod, students);
        this.lessonDesc = lessonDesc;
        this.timePeriod = timePeriod;
        this.studentList = new ArrayList<>(students);
        this.observableStudentList = FXCollections.observableArrayList(studentList);
        this.attendance = new LessonAttendance(students);
        this.notes = new LessonNotes(students);
    }

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonDesc lessonDesc, TimePeriod timePeriod, Collection<Student> students,
                  LessonAttendance attendance, LessonNotes notes) {
        requireAllNonNull(timePeriod, students, attendance, notes);
        // check if list of students is the same
        assert attendance.getAllStudents().containsAll(notes.getAllStudents());
        assert notes.getAllStudents().containsAll(students);
        assert students.containsAll(attendance.getAllStudents());

        this.lessonDesc = lessonDesc;
        this.timePeriod = timePeriod;
        this.studentList = new ArrayList<>(students);
        this.observableStudentList = FXCollections.observableArrayList(studentList);
        this.attendance = attendance;
        this.notes = notes;
    }

    public LocalDateTime startDateTime() {
        return timePeriod.getStart();
    }

    public LocalDateTime endDateTime() {
        return timePeriod.getEnd();
    }

    public boolean hasTimingConflict() {
        return hasClash;
    }

    public boolean hasTimingConflict(Lesson other) {
        return timePeriod.hasOverlap(other.timePeriod);
    }

    public Student getStudent(Index studentIndex) throws CommandException {
        int index = studentIndex.getZeroBased();
        if (index >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        return studentList.get(index);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public ObservableList<Student> getObservableStudentList() {
        return observableStudentList;
    }

    public void setStudent(Student targetStudent, Student editedStudent) {
        if (studentList.contains(targetStudent)) {
            attendance.setStudent(targetStudent, editedStudent);
            notes.setStudent(targetStudent, editedStudent);
            studentList.remove(targetStudent);
            studentList.add(editedStudent);
            studentList.sort(Comparator.comparing(s -> s.getName().toString()));
            observableStudentList.setAll(studentList);
        }
    }

    public String getStudentsName() {
        return attendance.getAllStudentsName();
    }

    public String isPresent(Student student) {
        return attendance.isPresent(student) ? "Present" : "Absent";
    }

    public void markAsPresent(Student student) {
        attendance.markAsPresent(student);
    }

    public void markAsAbsent(Student student) {
        attendance.markAsAbsent(student);
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void markAsNotCompleted() {
        isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean hasDesc() {
        return lessonDesc != null;
    }

    public LessonDesc getDesc() {
        return hasDesc() ? lessonDesc : null;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Map<Integer, Boolean> getAttendance() {
        Map<Integer, Boolean> resMap = new TreeMap<>();
        for (Student student : studentList) {
            resMap.put(studentList.indexOf(student), attendance.isPresent(student));
        }
        return resMap;
    }

    public String getStudentNotesString(Student student) {
        return notes.getStudentNotesString(student);
    }

    public String getGeneralNotesString() {
        return notes.getGeneralNotesString();
    }

    public ArrayList<String> getGeneralNotes() {
        return notes.getGeneralNotes();
    }

    public Map<Integer, ArrayList<String>> getStudentNotes() {
        TreeMap<Integer, ArrayList<String>> resMap = new TreeMap<>();
        for (Student student: studentList) {
            resMap.put(studentList.indexOf(student), notes.getStudentNotes(student));
        }
        return resMap;
    }

    public abstract LessonType getLessonType();

    public void addOverallNote(String overallNotes) {
        notes.addNote(overallNotes);
    }

    public void addStudentNote(String studentNotes, Student student) {
        notes.addNote(student, studentNotes);
    }

    public String deleteOverallNote(Index index) {
        return notes.deleteNote(index.getZeroBased());
    }

    public String deleteStudentNote(Student student, Index index) {
        return notes.deleteNote(student, index.getZeroBased());
    }

    public void markClash() {
        hasClash = true;
    }

    public void unmarkClash() {
        hasClash = false;
    }
}
