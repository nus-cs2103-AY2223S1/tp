package seedu.studmap.model.student;

import java.util.HashSet;
import java.util.Set;

import seedu.studmap.model.tag.Tag;

/**
 * A parameter object for Student constructor.
 */
public class StudentData {
    private StudentID id;
    private GitName gitUser;
    private TeleHandle teleHandle;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags = new HashSet<>();
    private Set<Attendance> attendances = new HashSet<>();

    public StudentID getId() {
        return id;
    }

    public void setId(StudentID id) {
        this.id = id;
    }

    public GitName getGitUser() {
        return gitUser;
    }

    public void setGitUser(GitName gitName) {
        this.gitUser = gitName;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Set<Attendance> getAttendances() {
        return attendances;
    }
}
