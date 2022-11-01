package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.question.Description;
import seedu.address.model.question.ImportantTag;
import seedu.address.model.question.Question;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.HelpTag;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new StuName("Alex Yeoh"), new Telegram("@AlexYeoh"),
                new StuEmail("alex@example.com"),
                new Response("2"), new Attendance("1"), new HelpTag(true )),
            new Student(new StuName("Alice Pauline"), new Telegram("@AlicePauline"),
                new StuEmail("alice@example.com"),
                new Response("1"), new Attendance("2"), new HelpTag(false)),
            new Student(new StuName("Benson Kurtz"), new Telegram("@BensonKurtz"),
                new StuEmail("benson@example.com"),
                new Response("4"), new Attendance("3"), new HelpTag(true ))
        };
    }

    public static Question[] getSampleQuestions() {
        return new Question[] {
            new Question(new Description("What is the difference between Sequence and Activity Diagrams?"),
                new ImportantTag(true)),
            new Question(new Description("What are UML Diagrams?"),
                new ImportantTag(false))
        };
    }

    public static Tutorial[] getSampleTutorials() {
        return new Tutorial[] {
            new Tutorial(new Group("T08"), new Content("UML Diagrams"),
                new Time("2022-10-01 0800"), true),
            new Tutorial(new Group("T09"), new Content("Types of bugs"),
                new Time("2022-10-01 0900"), false)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Question sampleQuestion: getSampleQuestions()) {
            sampleAb.addQuestion(sampleQuestion);
        }
        for (Tutorial sampleTutorial: getSampleTutorials()) {
            sampleAb.addTutorial(sampleTutorial);
        }
        return sampleAb;
    }

}
