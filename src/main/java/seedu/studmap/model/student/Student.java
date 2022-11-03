package seedu.studmap.model.student;

import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.studmap.model.tag.Tag;

/**
 * Represents a Student in the student map.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Module module;
    private final StudentID id;
    private final TeleHandle teleHandle;
    private final GitName gitName;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Attendance> attendances = new HashSet<>();
    private final Set<Assignment> assignments = new HashSet<>();
    private final Set<Participation> participations = new HashSet<>();

    /**
     * Constructor using a StudentData parameter object.
     * Requires name, phone, email, id, gitName, teleHanlde,
     * studmap, tags, attendances and assignments to be non-null.
     *
     * @param studentData StudentData parameter object.
     */
    public Student(StudentData studentData) {
        requireAllNonNull(studentData.getId(), studentData.getGitUser(),
                studentData.getTeleHandle(), studentData.getName(), studentData.getPhone(),
                studentData.getEmail(), studentData.getModule(), studentData.getTags(),
                studentData.getAttendances(), studentData.getAssignments(), studentData.getParticipations());

        this.id = studentData.getId();
        this.module = studentData.getModule();
        this.teleHandle = studentData.getTeleHandle();
        this.gitName = studentData.getGitUser();
        this.name = studentData.getName();
        this.phone = studentData.getPhone();
        this.email = studentData.getEmail();
        this.tags.addAll(studentData.getTags());
        this.attendances.addAll(studentData.getAttendances());
        this.assignments.addAll(studentData.getAssignments());
        this.participations.addAll(studentData.getParticipations());
    }

    public StudentID getId() {
        return id;
    }

    public String getIdString() {
        return id.toString();
    }

    public String getCmpIdString() {
        return id.toCmpString();
    }

    public GitName getGitName() {
        return gitName;
    }

    public String getGitString() {
        return gitName.toString();
    }

    public String getCmpGitString() {
        return gitName.toCmpString();
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public String getHandleString() {
        return teleHandle.toString();
    }

    public String getCmpHandleString() {
        return teleHandle.toCmpString();
    }

    public Module getModule() {
        return module;
    }

    public String getModuleString() {
        return module.toString();
    }

    public String getCmpModuleString() {
        return module.toCmpString();
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.toString();
    }

    public String getCmpNameString() {
        return name.toCmpString();
    }

    public Phone getPhone() {
        return phone;
    }

    public String getPhoneString() {
        return phone.toString();
    }

    public String getCmpPhoneString() {
        return phone.toCmpString();
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailString() {
        return email.toString();
    }

    public String getCmpEmailString() {
        return email.toCmpString();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable Attendances set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Attendance> getAttendances() {
        return Collections.unmodifiableSet(attendances);
    }

    /**
     * Returns an immutable Assignment set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Assignment> getAssignments() {
        return Collections.unmodifiableSet(assignments);
    }

    /**
     * Returns an immutable Participation set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Participation> getParticipations() {
        return Collections.unmodifiableSet(participations);
    }

    public StudentData getStudentData() {

        StudentData studentData = new StudentData();

        studentData.setName(this.getName());
        studentData.setPhone(this.getPhone());
        studentData.setEmail(this.getEmail());
        studentData.setModule(this.getModule());
        studentData.setId(this.getId());
        studentData.setGitUser(this.getGitName());
        studentData.setTeleHandle(this.getTeleHandle());

        studentData.setTags(new HashSet<>(this.getTags()));
        studentData.setAttendances(new HashSet<>(this.getAttendances()));
        studentData.setAssignments(new HashSet<>(this.getAssignments()));
        studentData.setParticipations(new HashSet<>(this.getParticipations()));

        return studentData;
    }

    /**
     * Returns attendance in percentage.
     */
    public float getAttendancePercentage() {
        float numOfClasses = getAttendances().size();
        float presentFor = (float) getAttendances().stream().filter(x -> x.hasAttended).count();
        return presentFor / numOfClasses * 100;
    }

    /**
     * Returns attendance in percentage for descending order sorting.
     */
    public float getAttendancePercentageForDsc() {
        float numOfClasses = getAttendances().size();
        if (numOfClasses == 0) {
            return -Float.MIN_VALUE;
        }
        float presentFor = (float) getAttendances().stream().filter(x -> x.hasAttended).count();
        return presentFor / numOfClasses * 100;
    }

    /**
     * Returns attendance in percentage for ascending order sorting.
     */
    public float getAttendancePercentageForAsc() {
        float numOfClasses = getAttendances().size();
        if (numOfClasses == 0) {
            return Float.MAX_VALUE;
        }
        float presentFor = (float) getAttendances().stream().filter(x -> x.hasAttended).count();
        return presentFor / numOfClasses * 100;
    }

    /**
     * Returns assignments marked in percentage.
     */
    public float getAssignmentPercentage() {
        float numOfAssignments = getAssignmentCount();
        float marked = (float) getAssignmentMarkedCount();
        return marked / numOfAssignments * 100;
    }

    /**
     * Returns participation in percentage for descending order sorting.
     */
    public float getParticipationPercentageForDsc() {
        float numOfPart = getParticipations().size();
        if (numOfPart == 0) {
            return -Float.MIN_VALUE;
        }
        float participatedFor = (float) getParticipations().stream().filter(x -> x.hasParticipated).count();
        return participatedFor / numOfPart * 100;
    }

    /**
     * Returns participation in percentage for ascending order sorting.
     */
    public float getParticipationPercentageForAsc() {
        float numOfPart = getParticipations().size();
        if (numOfPart == 0) {
            return Float.MAX_VALUE;
        }
        float participatedFor = (float) getParticipations().stream().filter(x -> x.hasParticipated).count();
        return participatedFor / numOfPart * 100;
    }

    /**
     * Returns participation in percentage.
     */
    public float getParticipationPercentage() {
        float numOfPart = getParticipations().size();
        float participatedFor = (float) getParticipations().stream().filter(x -> x.hasParticipated).count();
        return participatedFor / numOfPart * 100;
    }

    /**
     * Returns number of assignments marked.
     */
    public int getAssignmentMarkedCount() {
        return (int) getAssignments().stream()
                .filter(x -> x.markingStatus == Assignment.Status.MARKED).count();
    }

    /**
     * Returns number of assignments marked for descending order sorting.
     */
    public int getAssignmentMarkedCountForDsc() {
        if (getAssignments().stream().count() == 0) {
            return Integer.MIN_VALUE;
        } else {
            return (int) getAssignments().stream()
                                         .filter(x -> x.markingStatus == Assignment.Status.MARKED).count();
        }
    }

    /**
     * Returns number of assignments marked for ascending order sorting.
     */
    public int getAssignmentMarkedCountForAsc() {
        if (getAssignments().stream().count() == 0) {
            return Integer.MAX_VALUE;
        } else {
            return (int) getAssignments().stream()
                                         .filter(x -> x.markingStatus == Assignment.Status.MARKED).count();
        }
    }

    /**
     * Returns number of assignments received but unmarked.
     */
    public int getAssignmentUnmarkedCount() {
        return (int) getAssignments().stream()
                .filter(x -> x.markingStatus == Assignment.Status.RECEIVED).count();
    }

    /**
     * Returns number of assignments recorded.
     */
    public int getAssignmentCount() {
        return getAssignments().size();
    }

    /**
     * Returns true if both students have the same name, studentID and module.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null && otherStudent.getName().equals(getName())
                && otherStudent.getId().equals(getId()) && otherStudent.getModule().equals(getModule());
    }


    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getId().equals(getId())
                && otherStudent.getGitName().equals(getGitName())
                && otherStudent.getTeleHandle().equals(getTeleHandle())
                && otherStudent.getModule().equals(getModule())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getAttendances().equals(getAttendances())
                && otherStudent.getAssignments().equals(getAssignments());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own

        return Objects.hash(name, phone, email, id, gitName, teleHandle, module,
                tags, attendances, assignments, participations);

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Phone: ").append(getPhone())
                .append("; Email: ").append(getEmail())
                .append("; Module: ").append(getModule())
                .append("; StudentID: ").append(getId())
                .append("; GitHub Username: ").append(getGitName())
                .append("; TeleHandle: ").append(getTeleHandle());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Attendance> attendances = getAttendances();
        if (!attendances.isEmpty()) {
            builder.append("; Attendance: ");
            attendances.forEach(builder::append);
        }

        Set<Assignment> assignments = getAssignments();
        if (!assignments.isEmpty()) {
            builder.append("; Assignment: ");
            assignments.forEach(builder::append);
        }

        Set<Participation> participations = getParticipations();
        if (!participations.isEmpty()) {
            builder.append("; Participation: ");
            participations.forEach(builder::append);
        }
        return builder.toString();
    }

}
