package seedu.modquik.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.modquik.model.ModQuik;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.consultation.ConsultationDescription;
import seedu.modquik.model.consultation.ConsultationName;
import seedu.modquik.model.datetime.Datetime;
import seedu.modquik.model.datetime.DatetimeRange;
import seedu.modquik.model.datetime.WeeklyTimeslot;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.reminder.ReminderDescription;
import seedu.modquik.model.reminder.ReminderName;
import seedu.modquik.model.reminder.ReminderPriority;
import seedu.modquik.model.student.Attendance;
import seedu.modquik.model.student.Email;
import seedu.modquik.model.student.Grade;
import seedu.modquik.model.student.Name;
import seedu.modquik.model.student.Participation;
import seedu.modquik.model.student.Phone;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.StudentId;
import seedu.modquik.model.student.TelegramHandle;
import seedu.modquik.model.tag.Tag;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.model.tutorial.TutorialName;

/**
 * Contains utility methods for populating {@code ModQuik} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"),
                    new StudentId("A0000000a"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new TelegramHandle("alexyeoh"),
                    new ModuleCode("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("A"),
                getTagSet("quite")),
            new Student(new Name("Bernice Yu"),
                    new StudentId("A0000000B"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new TelegramHandle("berniceyu"),
                    new ModuleCode("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("PENDING..."),
                getTagSet("active", "strong")),
            new Student(new Name("Charlotte Oliveiro"),
                    new StudentId("A0000000C"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new TelegramHandle("charlotte"),
                    new ModuleCode("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("A"),
                getTagSet("hardworking")),
            new Student(new Name("David Li"),
                    new StudentId("A0000000D"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new TelegramHandle("lidavid"),
                    new ModuleCode("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("B"),
                getTagSet("careless")),
            new Student(new Name("Irfan Ibrahim"),
                    new StudentId("A0000000I"),
                    new Phone("92492021"), new Email("irfan@example.com"),
                    new TelegramHandle("irfan"),
                    new ModuleCode("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("C"),
                getTagSet("struggle")),
            new Student(new Name("Roy Balakrishnan"),
                    new StudentId("A0000000R"),
                    new Phone("92624417"), new Email("royb@example.com"),
                    new TelegramHandle("royb"),
                    new ModuleCode("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("PENDING..."),
                    getTagSet("newbie"))
        };
    }

    public static Reminder[] getSampleReminders() {
        return new Reminder[] {
            new Reminder(new ReminderName("Mark Midterms"),
                    Datetime.fromFormattedString("2022-10-26 20:00"),
                    new ReminderPriority("HIGH"), new ReminderDescription("300 papers to mark")),
            new Reminder(new ReminderName("Send email to TAs"),
                    Datetime.fromFormattedString("2022-10-14 21:00"),
                    new ReminderPriority("MEDIUM"), new ReminderDescription("Erratum in midterms")),
            new Reminder(new ReminderName("Meeting with Prof Lee"),
                    Datetime.fromFormattedString("2022-10-15 22:00"),
                    new ReminderPriority("LOW"), new ReminderDescription("Discuss incoming finals for CS2201")),
        };
    }

    public static Tutorial[] getSampleTutorials() {
        return new Tutorial[] {
            new Tutorial(new TutorialName("W17"), new ModuleCode("CS2103T"),
                    new Venue("COM1-0203"),
                    WeeklyTimeslot.fromFormattedString("1", "16:00", "18:00")),
            new Tutorial(new TutorialName("F01"), new ModuleCode("CS2103T"),
                    new Venue("COM1-0201"),
                    WeeklyTimeslot.fromFormattedString("2", "15:00", "17:00")),
            new Tutorial(new TutorialName("F02"), new ModuleCode("CS2103T"),
                    new Venue("COM1-0202"),
                    WeeklyTimeslot.fromFormattedString("3", "10:00", "11:00")),
        };
    }

    public static Consultation[] getSampleConsultations() {
        return new Consultation[] {
            new Consultation(new ConsultationName("Review past year paper"), new ModuleCode("CS2103T"),
                    new Venue("COM1-0203"),
                    DatetimeRange.fromFormattedString("2022-11-11", "16:00", "18:00"),
                    new ConsultationDescription("AY2021 Sem 1 Question 1,5,10 & AY2019 Sem 2 Question 2,8,9.")),
            new Consultation(new ConsultationName("Review UML diagram with Tom"), new ModuleCode("CS2103T"),
                    new Venue("COM1-0201"),
                    DatetimeRange.fromFormattedString("2022-12-01", "15:00", "17:00"),
                    new ConsultationDescription("Review tutorial week 7 question 4-10.")),
            new Consultation(new ConsultationName("Clear doubts about lecture with John"), new ModuleCode("CS2103T"),
                    new Venue("COM1-0202"),
                    DatetimeRange.fromFormattedString("2022-12-12", "10:00", "11:00"),
                    new ConsultationDescription("What's the difference between class diagram and object diagram?")),
        };
    }

    public static ReadOnlyModQuik getSampleModQuik() {
        ModQuik sampleAb = new ModQuik();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Tutorial sampleTutorial: getSampleTutorials()) {
            sampleAb.addTutorial(sampleTutorial);
        }

        for (Consultation sampleConsultation: getSampleConsultations()) {
            sampleAb.addConsultation(sampleConsultation);
        }

        for (Reminder sampleReminder: getSampleReminders()) {
            sampleAb.addReminder(sampleReminder);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
