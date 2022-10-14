package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.student.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_CLASS_GROUP = "CS2030 Lab 22";
    public static final String DEFAULT_STUDENTID = "e0707070";

    public static final String DEFAULT_ATTENDANCE = "0";
    public static final String DEFAULT_PICTURE_FILE_PATH = "profile/default-profile-pic.jpg";

    private Name name;
    private Phone phone;
    private Email email;
    private ClassGroup classGroup;
    private StudentId studentId;
    private Set<Tag> tags;

    private Attendance attendance;
    private Picture picture;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        classGroup = new ClassGroup(DEFAULT_CLASS_GROUP);
        studentId = new StudentId(DEFAULT_STUDENTID);
        tags = new HashSet<>();
        attendance = new Attendance(DEFAULT_ATTENDANCE);
        picture = new Picture(DEFAULT_PICTURE_FILE_PATH);

    }

    /**
     * Initializes the StudentBuilder with the data of {@code StudentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        classGroup = studentToCopy.getClassGroup();
        studentId = studentToCopy.getStudentId();
        tags = new HashSet<>(studentToCopy.getTags());
        attendance = studentToCopy.getAttendance();
        picture = studentToCopy.getPicture();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    /**
     * Sets the {@code ClassGroup} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassGroup(String classGroup) {
        this.classGroup = new ClassGroup(classGroup);
        return this;
    }

    /**
     * Sets the {@code Picture} of the {@code Student} that we are building.
     */
    public StudentBuilder withPicture(String pictureFilePath) {
        this.picture = new Picture(pictureFilePath);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, classGroup, studentId, tags, attendance, picture);
    }

}
