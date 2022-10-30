package seedu.classify.model.student;

import static seedu.classify.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.exceptions.ExamNotFoundException;


/**
 * Represents a Student in the record.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Student fields
    private final Name studentName;
    private final Id id;

    // Academic fields
    private final Class className;
    private final Set<Exam> exams = new HashSet<>();

    // Parent fields
    private final Name parentName;
    private final Phone phone;
    private final Email email;

    /**
     * Every field must be present and not null.
     */
    public Student(Name studentName, Id id, Class className, Name parentName, Phone phone,
                   Email email, Set<Exam> exams) {
        requireAllNonNull(studentName, id, className, parentName, phone, email, exams);
        this.studentName = studentName;
        this.id = id;
        this.className = className;
        this.parentName = parentName;
        this.phone = phone;
        this.email = email;
        this.exams.addAll(exams);
    }

    public Name getStudentName() {
        return studentName;
    }

    public Id getId() {
        return id;
    }

    public Class getClassName() {
        return className;
    }

    public Name getParentName() {
        return parentName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable exam set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Exam> getExams() {
        return Collections.unmodifiableSet(exams);
    }

    public Exam getExam(String exam) throws ExamNotFoundException {
        assert exam.equals("CA1") || exam.equals("CA2") || exam.equals("SA1") || exam.equals("SA2");
        Iterator<Exam> examIterator = exams.iterator();
        Exam currExam = null;
        while (examIterator.hasNext()) {
            Exam temp = examIterator.next();
            if (temp.getExamName().equals(exam)) {
                currExam = temp;
                break;
            }
        }
        if (currExam == null) {
            throw new ExamNotFoundException();
        }
        return currExam;
    }

    /**
     * Return the student's grade for the specified exam
     */
    public int getExamScore(String exam) {
        try {
            return getExam(exam).getScore();
        } catch (ExamNotFoundException e) {
            throw new ExamNotFoundException();
        }
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean hasSameNameOrId(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && (otherStudent.getStudentName().equals(getStudentName())
                || otherStudent.getId().equals(getId()));
    }

    /**
     * Returns true if both students have the same identity, class and parent fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getStudentName().equals(getStudentName())
                && otherStudent.getId().equals(getId())
                && otherStudent.getClassName().equals(getClassName())
                && otherStudent.getParentName().equals(getParentName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getExams().equals(getExams());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, id, className, parentName, phone, email, exams);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nName: ")
                .append(getStudentName())
                .append("\nId: ")
                .append(getId())
                .append("\nClass: ")
                .append(getClassName());
        if (!getParentName().isEmpty()) {
            builder.append("\nParent Name: ").append(getParentName());
        }
        if (!getPhone().isEmpty()) {
            builder.append("\nParent Phone: ").append(getPhone());
        }
        if (!getEmail().isEmpty()) {
            builder.append("\nParent Email: ").append(getEmail());;
        }
        Set<Exam> examSet = getExams();
        if (!examSet.isEmpty()) {
            builder.append("\nExams: ");
            examSet.forEach(exam -> builder.append("\n").append(exam));
        }
        return builder.toString();
    }

}
