package seedu.studmap.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Module;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Participation;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.TeleHandle;
import seedu.studmap.model.tag.Tag;
/**
 * Contains utility methods for populating {@code StudMap} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {

        StudentData studentData;
        ArrayList<StudentData> data = new ArrayList<>();

        studentData = new StudentData();
        studentData.setName(new Name("Silas Yeo"));
        studentData.setPhone(new Phone("84112213"));
        studentData.setEmail(new Email());
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E0773771"));
        studentData.setGitUser(new GitName("silasysy"));
        studentData.setTeleHandle(new TeleHandle("@silas"));
        studentData.setTags(getTagSet("StrongStudent"));
        studentData.setAttendances(getAttendedSet("T01", "T02", "T03", "T04"));
        studentData.setAssignments(getMarkedAssignments("A01"));
        studentData.addAssignments(getReceivedAssignments("A02"));
        studentData.addAssignments(getNewAssignments("A03"));
        studentData.setParticipations(getParticipatedSet("T03-Sharing", "T04-Sharing"));
        studentData.addParticipations(getNotParticipatedSet("T05-Sharing"));
        data.add(studentData);


        studentData = new StudentData();
        studentData.setName(new Name("Sally"));
        studentData.setPhone(new Phone("94732221"));
        studentData.setEmail(new Email());
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E1288122"));
        studentData.setGitUser(new GitName("sallysys"));
        studentData.setTeleHandle(new TeleHandle("@sallys"));
        studentData.setTags(getTagSet("NeedsHelp"));
        studentData.setAttendances(getAttendedSet("T01", "T03", "T04"));
        studentData.addAttendances(getNotAttendedSet("T02"));
        studentData.setAssignments(getReceivedAssignments("A01", "A02"));
        studentData.addAssignments(getNewAssignments("A03"));
        studentData.setParticipations(getParticipatedSet("T03-Sharing", "T04-Sharing"));
        studentData.addParticipations(getNotParticipatedSet("T05-Sharing"));
        data.add(studentData);

        studentData = new StudentData();
        studentData.setName(new Name("Sheyuan"));
        studentData.setPhone(new Phone("98771923"));
        studentData.setEmail(new Email());
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E2345229"));
        studentData.setGitUser(new GitName("piyopp"));
        studentData.setTeleHandle(new TeleHandle("@piyo"));

        studentData = new StudentData();
        studentData.setName(new Name("Po-Hsien"));
        studentData.setPhone(new Phone("98882110"));
        studentData.setEmail(new Email("po@popo.po"));
        studentData.setModule(new Module("CS2103T"));
        studentData.setId(new StudentID("E0998281"));
        studentData.setGitUser(new GitName("popopo"));
        studentData.setTeleHandle(new TeleHandle("@popo"));

        studentData = new StudentData();
        studentData.setName(new Name("Po Taeto"));
        studentData.setPhone(new Phone("96600302"));
        studentData.setEmail(new Email());
        studentData.setModule(new Module("CS2103T"));
        studentData.setId(new StudentID("E0982113"));
        studentData.setGitUser(new GitName("potatopo"));
        studentData.setTeleHandle(new TeleHandle());

        studentData = new StudentData();
        studentData.setName(new Name("Salad Yo"));
        studentData.setPhone(new Phone("89123612"));
        studentData.setEmail(new Email("salad@yo.org"));
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E0123111"));
        studentData.setGitUser(new GitName("saladydy"));
        studentData.setTeleHandle(new TeleHandle("@salad"));

        studentData = new StudentData();
        studentData.setName(new Name("Tom Aito"));
        studentData.setPhone(new Phone("88456973"));
        studentData.setEmail(new Email());
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E1728221"));
        studentData.setGitUser(new GitName("tomatoto"));
        studentData.setTeleHandle(new TeleHandle());

        return data.stream()
                .map(Student::new)
                .toArray(Student[]::new);
    }

    public static ReadOnlyStudMap getSampleStudMap() {
        StudMap sampleSm = new StudMap();
        for (Student sampleStudent : getSampleStudents()) {
            sampleSm.addStudent(sampleStudent);
        }
        return sampleSm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an Attendance set of attended classes containing the list of strings given.
     */
    public static Set<Attendance> getAttendedSet(String... strings) {
        return Arrays.stream(strings)
                .map(className -> new Attendance(className, Boolean.TRUE))
                .collect(Collectors.toSet());
    }

    /**
     * Returns an Attendance set of classes not attended containing the list of strings given.
     */
    public static Set<Attendance> getNotAttendedSet(String... strings) {
        return Arrays.stream(strings)
                .map(className -> new Attendance(className, Boolean.FALSE))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a Participation set from the given string of participated components.
     */
    public static Set<Participation> getParticipatedSet(String... strings) {
        return Arrays.stream(strings)
                     .map(participationComponent -> new Participation(participationComponent, Boolean.TRUE))
                     .collect(Collectors.toSet());
    }

    /**
     * Returns a Participation set from the given string of not participated components.
     */
    public static Set<Participation> getNotParticipatedSet(String... strings) {
        return Arrays.stream(strings)
                     .map(participationComponent -> new Participation(participationComponent, Boolean.FALSE))
                     .collect(Collectors.toSet());
    }

    /**
     * Returns a set of unmarked assignments containing the list of strings.
     */
    public static Set<Assignment> getNewAssignments(String... strings) {
        return Arrays.stream(strings)
                .map(assignmentName -> new Assignment(assignmentName, Assignment.Status.NEW))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of received assignments containing the list of strings.
     */
    public static Set<Assignment> getReceivedAssignments(String... strings) {
        return Arrays.stream(strings)
                .map(assignmentName -> new Assignment(assignmentName, Assignment.Status.RECEIVED))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of marked assignments containing the list of strings.
     */
    public static Set<Assignment> getMarkedAssignments(String... strings) {
        return Arrays.stream(strings)
                .map(assignmentName -> new Assignment(assignmentName, Assignment.Status.MARKED))
                .collect(Collectors.toSet());
    }
}
