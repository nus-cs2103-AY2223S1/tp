package seedu.classify.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;
import seedu.classify.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Amy Bee";
    public static final String DEFAULT_ID = "123Z";
    public static final String DEFAULT_CLASS = "1A";
    public static final String DEFAULT_PARENT_NAME = "John Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amybee@gmail.com";
    public static final String DEFAULT_EXAM_1 = "CA2 90";
    public static final String DEFAULT_EXAM_2 = "SA1 60";

    private Name studentName;
    private Id id;
    private Class className;
    private Name parentName;
    private Phone phone;
    private Email email;
    private Set<Exam> exams;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new Name(DEFAULT_STUDENT_NAME);
        id = new Id(DEFAULT_ID);
        className = new Class(DEFAULT_CLASS);
        parentName = new Name(DEFAULT_PARENT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        exams = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getStudentName();
        id = studentToCopy.getId();
        className = studentToCopy.getClassName();
        parentName = studentToCopy.getParentName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        exams = new HashSet<>(studentToCopy.getExams());
    }

    /**
     * Sets the student name {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentName(String name) {
        this.studentName = new Name(name);
        return this;
    }

    /**
     * Sets the parent name {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentName(String name) {
        if (name == "") {
            this.parentName = new Name();
        } else {
            this.parentName = new Name(name);
        }
        return this;
    }

    /**
     * Parses the {@code exams} into a {@code Set<Exam>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withExams(String ... exams) {
        this.exams.addAll(SampleDataUtil.getExamSet(exams));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        if (email == "") {
            this.email = new Email();
        } else {
            this.email = new Email(email);
        }
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        if (phone == "") {
            this.phone = new Phone();
        } else {
            this.phone = new Phone(phone);
        }
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Student} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the class {@code Class} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassName(String className) {
        this.className = new Class(className);
        return this;
    }
    public Student build() {
        return new Student(studentName, id, className, parentName, phone, email, exams);
    }

}
