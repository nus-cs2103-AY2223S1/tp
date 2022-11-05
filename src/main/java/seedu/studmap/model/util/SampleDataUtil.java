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
        studentData.setName(new Name("Alex Yeoh"));
        studentData.setPhone(new Phone("87438807"));
        studentData.setEmail(new Email("alexyeoh@example.com"));
        studentData.setModule(new Module("CS2103T"));
        studentData.setId(new StudentID("E1234561"));
        studentData.setGitUser(new GitName("user1"));
        studentData.setTeleHandle(new TeleHandle("@user1"));
        studentData.setTags(getTagSet("friends"));
        studentData.setAttendances(getAttendedSet("T01", "T02"));
        studentData.addAttendances(getNotAttendedSet("T03"));
        studentData.setParticipations(getParticipatedSet("P01", "P02"));
        studentData.addParticipations(getNotParticipatedSet("P03"));
        data.add(studentData);

        studentData = new StudentData();
        studentData.setName(new Name("Bernice Yu"));
        studentData.setPhone(new Phone("99272758"));
        studentData.setEmail(new Email("berniceyu@example.com"));
        studentData.setModule(new Module("CS2103T"));
        studentData.setId(new StudentID("E1234562"));
        studentData.setGitUser(new GitName("user2"));
        studentData.setTeleHandle(new TeleHandle("@user2"));
        studentData.setTags(getTagSet("colleagues", "friends"));
        studentData.setAssignments(getMarkedAssignments("A01", "A02"));
        studentData.addAssignments(getReceivedAssignments("A03", "A04"));
        studentData.addAssignments(getNewAssignments("A05", "A06"));
        studentData.addAssignments(getNewAssignments("A05", "A06"));
        data.add(studentData);

        studentData = new StudentData();
        studentData.setName(new Name("Charlotte Oliveiro"));
        studentData.setPhone(new Phone("93210283"));
        studentData.setEmail(new Email("charlotte@example.com"));
        studentData.setModule(new Module("CS2103T"));
        studentData.setId(new StudentID("E1234563"));
        studentData.setGitUser(new GitName("user3"));
        studentData.setTeleHandle(new TeleHandle("@user3"));
        studentData.setTags(getTagSet("neighbours"));
        data.add(studentData);

        studentData = new StudentData();
        studentData.setName(new Name("David Li"));
        studentData.setPhone(new Phone("91031282"));
        studentData.setEmail(new Email("lidavid@example.com"));
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E1234564"));
        studentData.setGitUser(new GitName("user4"));
        studentData.setTeleHandle(new TeleHandle("@user4"));
        studentData.setTags(getTagSet("family"));
        data.add(studentData);

        studentData = new StudentData();
        studentData.setName(new Name("Irfan Ibrahim"));
        studentData.setPhone(new Phone("92492021"));
        studentData.setEmail(new Email("irfan@example.com"));
        studentData.setModule(new Module("CS2106"));
        studentData.setId(new StudentID("E1234565"));
        studentData.setGitUser(new GitName("user5"));
        studentData.setTeleHandle(new TeleHandle("@user5"));
        studentData.setTags(getTagSet("classmates"));
        data.add(studentData);

        studentData = new StudentData();
        studentData.setName(new Name("Roy Balakrishnan"));
        studentData.setPhone(new Phone("92624417"));
        studentData.setEmail(new Email("royb@example.com"));
        studentData.setModule(new Module("CS2103T"));
        studentData.setId(new StudentID("E1234566"));
        studentData.setGitUser(new GitName("user6"));
        studentData.setTeleHandle(new TeleHandle("@user6"));
        studentData.setTags(getTagSet("colleagues"));
        data.add(studentData);

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
                .map(className -> new Attendance(className, Attendance.Status.PRESENT))
                .collect(Collectors.toSet());
    }

    /**
     * Returns an Attendance set of classes not attended containing the list of strings given.
     */
    public static Set<Attendance> getNotAttendedSet(String... strings) {
        return Arrays.stream(strings)
                .map(className -> new Attendance(className, Attendance.Status.ABSENT))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a Participation set from the given string of participated components.
     */
    public static Set<Participation> getParticipatedSet(String... strings) {
        return Arrays.stream(strings)
                .map(participationComponent -> new Participation(participationComponent,
                        Participation.Status.PARTICIPATED))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a Participation set from the given string of not participated components.
     */
    public static Set<Participation> getNotParticipatedSet(String... strings) {
        return Arrays.stream(strings)
                .map(participationComponent -> new Participation(participationComponent,
                        Participation.Status.NOT_PARTICIPATED))
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
