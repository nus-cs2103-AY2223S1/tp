package seedu.studmap.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.studmap.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "";
    public static final String DEFAULT_EMAIL = "";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_ID = "E1234567";
    public static final String DEFAULT_GIT = "";
    public static final String DEFAULT_TELE = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Module module;
    private StudentID id;
    private GitName gitName;
    private TeleHandle handle;
    private Set<Tag> tags;
    private Set<Attendance> attendances;
    private Set<Assignment> assignments;
    private Set<Participation> participations;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        module = new Module(DEFAULT_MODULE);
        id = new StudentID(DEFAULT_ID);
        gitName = new GitName(DEFAULT_GIT);
        handle = new TeleHandle(DEFAULT_TELE);
        tags = new HashSet<>();
        attendances = new HashSet<>();
        assignments = new HashSet<>();
        participations = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        module = studentToCopy.getModule();
        id = studentToCopy.getId();
        gitName = studentToCopy.getGitName();
        handle = studentToCopy.getTeleHandle();
        tags = new HashSet<>(studentToCopy.getTags());
        attendances = new HashSet<>(studentToCopy.getAttendances());
        assignments = new HashSet<>(studentToCopy.getAssignments());
        participations = new HashSet<>(studentToCopy.getParticipations());
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
     * Parses the {@code classNames} which the student has attended into a
     * {@code Set<Attendance>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAttended(String ... classNames) {
        this.attendances.addAll(SampleDataUtil.getAttendedSet(classNames));
        return this;
    }

    /**
     * Adds attendances which the student has.
     */
    public StudentBuilder addAttendances(Attendance ... attendances) {
        this.attendances.addAll(Set.of(attendances));
        return this;
    }

    /**
     * Parses the {@code classNames} which the student has attended into a
     * {@code Set<Attendance>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addNotAttended(String ... classNames) {
        this.attendances.addAll(SampleDataUtil.getNotAttendedSet(classNames));
        return this;
    }

    /**
     * Parses the {@code assignmentNames} which the user has not marked into a
     * {@code Set<Assignment>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAssignedNew(String ... assignmentNames) {
        this.assignments.addAll(SampleDataUtil.getNewAssignments(assignmentNames));
        return this;
    }

    /**
     * Parses the {@code assignmentNames} which the user has received into a
     * {@code Set<Assignment>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAssignedReceived(String ... assignmentNames) {
        this.assignments.addAll(SampleDataUtil.getReceivedAssignments(assignmentNames));
        return this;
    }

    /**
     * Parses the {@code assignmentNames} which the user has marked into a
     * {@code Set<Assignment>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAssignedMarked(String ... assignmentNames) {
        this.assignments.addAll(SampleDataUtil.getMarkedAssignments(assignmentNames));
        return this;
    }

    /**
     * Adds participations which the student has.
     */
    public StudentBuilder addAssignments(Assignment ... assignments) {
        this.assignments.addAll(Set.of(assignments));
        return this;
    }

    public StudentBuilder setAttended(Set<Attendance> attendances) {
        this.attendances = attendances;
        return this;
    }

    public StudentBuilder setAssigned(Set<Assignment> assignments) {
        this.assignments = assignments;
        return this;
    }

    public StudentBuilder setParticipated(Set<Participation> participations) {
        this.participations = participations;
        return this;
    }

    /**
     * Parses the {@code participationComponent} which the student has participated into a
     * {@code Set<Participation>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addParticipations(String ... participationComponent) {
        this.participations.addAll(SampleDataUtil.getParticipatedSet(participationComponent));
        return this;
    }

    /**
     * Parses the {@code participationComponent} which the student has not participated into a
     * {@code Set<Participation>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addNotParticipated(String ... notParticipatedComponent) {
        this.participations.addAll(SampleDataUtil.getNotParticipatedSet(notParticipatedComponent));
        return this;
    }

    /**
     * Adds participations which the student has.
     */
    public StudentBuilder addParticipation(Participation ... participations) {
        this.participations.addAll(Set.of(participations));
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
     * Sets the {@code Module} of the {@code Student} that we are building.
     */
    public StudentBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code StudentID} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new StudentID(id);
        return this;
    }

    /**
     * Sets the {@code gitName} of the {@code GitName} that we are building.
     */
    public StudentBuilder withGitName(String name) {
        this.gitName = new GitName(name);
        return this;
    }

    /**
     * Sets the {@code handle} of the {@code TeleHandle} that we are building.
     */
    public StudentBuilder withTeleHandle(String teleHandle) {
        this.handle = new TeleHandle(teleHandle);
        return this;
    }

    /**
     * Builds the student using the given parameters.
     * @return New Student.
     */
    public Student build() {
        StudentData studentData = new StudentData();
        studentData.setName(this.name);
        studentData.setPhone(this.phone);
        studentData.setEmail(this.email);
        studentData.setModule(this.module);
        studentData.setId(this.id);
        studentData.setGitUser(this.gitName);
        studentData.setTeleHandle(this.handle);
        studentData.setTags(this.tags);
        studentData.setAttendances(this.attendances);
        studentData.setAssignments(this.assignments);
        studentData.setParticipations(this.participations);
        return new Student(studentData);
    }

}
