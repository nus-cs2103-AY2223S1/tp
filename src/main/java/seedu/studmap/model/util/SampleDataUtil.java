package seedu.studmap.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.student.Address;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Name;
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

        studentData = new StudentData();;
        studentData.setName(new Name("Alex Yeoh"));
        studentData.setPhone(new Phone("87438807"));
        studentData.setEmail(new Email("alexyeoh@example.com"));
        studentData.setId(new StudentID("A1234567A"));
        studentData.setGitUser(new GitName("user1"));
        studentData.setTeleHandle(new TeleHandle("@user1"));
        studentData.setAddress(new Address("Blk 30 Geylang Street 29, #06-40"));
        studentData.setTags(getTagSet("friends"));
        studentData.setAttendances(getAttendedSet("T01", "T02"));
        studentData.addAttendances(getNotAttendedSet("T03"));
        data.add(studentData);

        studentData = new StudentData();;
        studentData.setName(new Name("Bernice Yu"));
        studentData.setPhone(new Phone("99272758"));
        studentData.setEmail(new Email("berniceyu@example.com"));
        studentData.setId(new StudentID("A1234567B"));
        studentData.setGitUser(new GitName("user2"));
        studentData.setTeleHandle(new TeleHandle("@user2"));
        studentData.setAddress(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"));
        studentData.setTags(getTagSet("colleagues", "friends"));
        data.add(studentData);

        studentData = new StudentData();;
        studentData.setName(new Name("Charlotte Oliveiro"));
        studentData.setPhone(new Phone("93210283"));
        studentData.setEmail(new Email("charlotte@example.com"));
        studentData.setId(new StudentID("A1234567C"));
        studentData.setGitUser(new GitName("user3"));
        studentData.setTeleHandle(new TeleHandle("@user3"));
        studentData.setAddress(new Address("Blk 11 Ang Mo Kio Street 74, #11-04"));
        studentData.setTags(getTagSet("neighbours"));
        data.add(studentData);

        studentData = new StudentData();;
        studentData.setName(new Name("David Li"));
        studentData.setPhone(new Phone("91031282"));
        studentData.setEmail(new Email("lidavid@example.com"));
        studentData.setId(new StudentID("A1234567D"));
        studentData.setGitUser(new GitName("user4"));
        studentData.setTeleHandle(new TeleHandle("@user4"));
        studentData.setAddress(new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));
        studentData.setTags(getTagSet("family"));
        data.add(studentData);

        studentData = new StudentData();;
        studentData.setName(new Name("Irfan Ibrahim"));
        studentData.setPhone(new Phone("92492021"));
        studentData.setEmail(new Email("irfan@example.com"));
        studentData.setId(new StudentID("A1234567E"));
        studentData.setGitUser(new GitName("user5"));
        studentData.setTeleHandle(new TeleHandle("@user5"));
        studentData.setAddress(new Address("Blk 47 Tampines Street 20, #17-35"));
        studentData.setTags(getTagSet("classmates"));
        data.add(studentData);

        studentData = new StudentData();;
        studentData.setName(new Name("Roy Balakrishnan"));
        studentData.setPhone(new Phone("92624417"));
        studentData.setEmail(new Email("royb@example.com"));
        studentData.setId(new StudentID("A1234567F"));
        studentData.setGitUser(new GitName("user6"));
        studentData.setTeleHandle(new TeleHandle("@user6"));
        studentData.setAddress(new Address("Blk 45 Aljunied Street 85, #11-31"));
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
}
