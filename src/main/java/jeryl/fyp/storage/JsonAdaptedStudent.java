package jeryl.fyp.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String studentName;
    private final String studentId;
    private final String email;
    private final String projectName;
    private final String projectStatus;
    private final List<JsonAdaptedDeadline> deadlines = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String studentName, @JsonProperty("studentId") String studentId,
                              @JsonProperty("email") String email, @JsonProperty("projectName") String projectName,
                              @JsonProperty("projectStatus") String projectStatus,
                              @JsonProperty("deadlines") List<JsonAdaptedDeadline> deadlines,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.email = email;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        if (deadlines != null) {
            this.deadlines.addAll(deadlines);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        studentName = source.getStudentName().fullStudentName;
        studentId = source.getStudentId().id;
        email = source.getEmail().value;
        projectName = source.getProjectName().fullProjectName;
        projectStatus = source.getProjectStatus().projectStatus;
        deadlines.addAll(source.getDeadlineList().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedDeadline::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        final List<Deadline> studentDeadlines = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }
        for (JsonAdaptedDeadline ddl : deadlines) {
            studentDeadlines.add(ddl.toModelType());
        }

        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentName.class.getSimpleName()));
        }
        if (!StudentName.isValidStudentName(studentName)) {
            throw new IllegalValueException(StudentName.MESSAGE_CONSTRAINTS);
        }
        final StudentName modelStudentName = new StudentName(studentName);

        if (studentId == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (projectName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidProjectName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modelProjectName = new ProjectName(projectName);

        if (projectStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectStatus.class.getSimpleName()));
        }
        if (!ProjectStatus.isValidProjectStatus(projectStatus)) {
            throw new IllegalValueException(ProjectStatus.MESSAGE_CONSTRAINTS);
        }
        final ProjectStatus modelProjectStatus = new ProjectStatus(projectStatus);

        final Set<Tag> modelTags = new HashSet<>(studentTags);

        Student student = new Student(modelStudentName, modelStudentId, modelEmail,
                modelProjectName, modelProjectStatus, modelTags);
        // restore deadlines
        student.getDeadlineList().setDeadlines(studentDeadlines);
        return student;
    }

}
