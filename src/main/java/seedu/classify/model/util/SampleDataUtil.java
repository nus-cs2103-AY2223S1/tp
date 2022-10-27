package seedu.classify.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.StudentRecord;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;

/**
 * Contains utility methods for populating {@code StudentRecord} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[]{
            new Student(new Name("Alex Yeoh"), new Id("123A"), new Class("1A"), new Name("Bernice Yu"),
                    new Phone("99272758"), new Email("berniceyu@gmail.com"),
                    getExamSet("CA1 50", "CA2 70")),
            new Student(new Name("David Yeoh"), new Id("456B"), new Class("1A"), new Name("Bernice Yu"),
                    new Phone("87438807"), new Email("berniceyu@gmail.com"),
                    getExamSet("SA1 20", "SA2 50")),
            new Student(new Name("Charlotte Oliveiro"), new Id("435A"), new Class("2A"),
                    new Name("Dean Oliveiro"), new Phone("93210283"), new Email("deanoliveiro@hotmail.com"),
                    getExamSet("CA1 80")),
            new Student(new Name("Irfan Ibrahim"), new Id("189A"), new Class("3A"),
                    new Name("Roy Balakrishnan"), new Phone("91031282"),
                    new Email("RoyBalakrishnan@gmail.com"), getExamSet("CA1 60", "CA2 100", "SA1 60", "SA2 70")),
        };
    }

    public static ReadOnlyStudentRecord getSampleStudentRecord() {
        StudentRecord sampleSr = new StudentRecord();
        for (Student sampleStudent : getSampleStudents()) {
            sampleSr.addStudent(sampleStudent);
        }
        return sampleSr;
    }

    /**
     * Returns a exam set containing the list of strings given.
     */
    public static Set<Exam> getExamSet(String... strings) {
        return Arrays.stream(strings)
                .map(Exam::new)
                .collect(Collectors.toSet());
    }

}
