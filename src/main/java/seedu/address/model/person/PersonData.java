package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A parameter object for Person constructor.
 */
public class PersonData {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags = new HashSet<>();
    private Set<Attendance> attendances = new HashSet<>();

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
