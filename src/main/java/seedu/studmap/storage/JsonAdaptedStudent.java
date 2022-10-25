package seedu.studmap.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.studmap.commons.exceptions.IllegalValueException;

import seedu.studmap.model.student.Address;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.student.TeleHandle;
import seedu.studmap.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String StudentId;
    private final String gitName;
    private final String handle;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedAttendance> attended = new ArrayList<>();
    private final List<JsonAdaptedAssignment> assigned = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("StudentId") String StudentId,
                              @JsonProperty("gitName") String gitName, @JsonProperty("handle") String handle,
                              @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("attended") List<JsonAdaptedAttendance> attended,
                              @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.StudentId = StudentId;
        this.gitName = gitName;
        this.handle = handle;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (attended != null) {
            this.attended.addAll(attended);
        }
        if (assignments != null) {
            this.assigned.addAll(assignments);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        StudentId = source.getId().value;
        gitName = source.getGitName().value;
        handle = source.getTeleHandle().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attended.addAll(source.getAttendances().stream()
                .map(attendance -> new JsonAdaptedAttendance(attendance.getString()))
                .collect(Collectors.toList()));
        assigned.addAll(source.getAssignments().stream()
                .map(assignment -> new JsonAdaptedAssignment(assignment.getString()))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }

        final List<Attendance> studentAttendances = new ArrayList<>();
        for (JsonAdaptedAttendance attendance : attended) {
            studentAttendances.add(attendance.toModelType());
        }

        final List<Assignment> studentAssignments = new ArrayList<>();
        for (JsonAdaptedAssignment assignment : assigned) {
            studentAssignments.add(assignment.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (StudentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentID.class.getSimpleName()));
        }
        if (!StudentID.isValidStudentID(StudentId)) {
            throw new IllegalValueException(StudentID.MESSAGE_CONSTRAINTS);
        }
        final StudentID modelId = new StudentID(StudentId);

        if (gitName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GitName.class.getSimpleName()));
        }
        if (!GitName.isValidGitName(gitName)) {
            throw new IllegalValueException(GitName.MESSAGE_CONSTRAINTS);
        }
        final GitName modelGit = new GitName(gitName);

        if (handle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeleHandle.class.getSimpleName()));
        }
        if (!TeleHandle.isValidTeleHandle(handle)) {
            throw new IllegalValueException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        final TeleHandle modelHandle = new TeleHandle(handle);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        final Set<Tag> modelTags = new HashSet<>(studentTags);
        final Set<Attendance> modelAttendances = new HashSet<>(studentAttendances);
        final Set<Assignment> modelAssignments = new HashSet<>(studentAssignments);


        StudentData studentData = new StudentData();
        studentData.setName(modelName);
        studentData.setPhone(modelPhone);
        studentData.setEmail(modelEmail);
        studentData.setId(modelId);
        studentData.setGitUser(modelGit);
        studentData.setTeleHandle(modelHandle);
        studentData.setAddress(modelAddress);
        studentData.setTags(modelTags);
        studentData.setAttendances(modelAttendances);
        studentData.setAssignments(modelAssignments);
        return new Student(studentData);
    }

}
