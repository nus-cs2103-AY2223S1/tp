package seedu.studmap.model.student;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.studmap.model.tag.Tag;

/**
 * A parameter object for Student constructor.
 */
public class StudentData {
    private StudentID studentID;
    private GitName gitName = new GitName();
    private TeleHandle teleHandle = new TeleHandle();
    private Name name;
    private Phone phone = new Phone();
    private Email email = new Email();
    private Module module;
    private Set<Tag> tags = new HashSet<>();
    private Set<Attendance> attendances = new HashSet<>();
    private Set<Assignment> assignments = new HashSet<>();
    private Set<Participation> participations = new HashSet<>();

    public StudentID getId() {
        return studentID;
    }

    public void setId(StudentID id) {
        this.studentID = id;
    }

    public GitName getGitUser() {
        return gitName;
    }

    public void setGitUser(GitName gitName) {
        this.gitName = gitName;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public void setTeleHandle(TeleHandle teleHandle) {
        this.teleHandle = teleHandle;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttendances(Set<Attendance> attendances) {
        this.attendances.addAll(attendances);
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public void addParticipations(Set<Participation> participations) {
        this.participations.addAll(participations);
    }

    public void addAssignments(Set<Assignment> assignments) {
        this.assignments.addAll(assignments);
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentData that = (StudentData) o;
        return Objects.equals(name, that.name)
                && Objects.equals(phone, that.phone)
                && Objects.equals(email, that.email)
                && Objects.equals(studentID, that.studentID)
                && Objects.equals(gitName, that.gitName)
                && Objects.equals(teleHandle, that.teleHandle)
                && Objects.equals(module, that.module)
                && Objects.equals(tags, that.tags)
                && Objects.equals(attendances, that.attendances)
                && Objects.equals(assignments, that.assignments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, studentID, gitName,
                teleHandle, module, tags, attendances, assignments);
    }

}
