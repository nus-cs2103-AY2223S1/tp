package seedu.rc4hdb.model.resident;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.rc4hdb.commons.util.CollectionUtil;
import seedu.rc4hdb.model.tag.Tag;

public class ResidentStringDescriptor {
    private Optional<String> name;
    private Optional<String> phone;
    private Optional<String> email;
    private Optional<String> room;
    private Optional<String> gender;
    private Optional<String> house;
    private Optional<String> matricNumber;
    private Set<String> tags;

    public ResidentStringDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public ResidentStringDescriptor(ResidentStringDescriptor toCopy) {
        this.name = toCopy.name;
        this.phone = toCopy.phone;
        this.email = toCopy.email;
        this.room = toCopy.room;
        this.gender = toCopy.gender;
        this.house = toCopy.house;
        this.matricNumber = toCopy.matricNumber;
        setTags(toCopy.tags);
    }

    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(name, phone, email, room, gender, house, matricNumber, tags);
    }

    //=========== Start of Getters and Setters ===============================================================

    public void setName(String name) {
        this.name = Optional.of(name);
    }

    public Optional<String> getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = Optional.of(phone);
    }

    public Optional<String> getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = Optional.of(email);
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setRoom(String room) {
        this.room = Optional.of(room);
    }

    public Optional<String> getRoom() {
        return room;
    }

    public void setGender(String gender) {
        this.gender = Optional.of(gender);
    }

    public Optional<String> getGender() {
        return gender;
    }

    public void setHouse(String house) {
        this.house = Optional.of(house);
    }

    public Optional<String> getHouse() {
        return house;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = Optional.of(matricNumber);
    }

    public Optional<String> getMatricNumber() {
        return matricNumber;
    }
    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<String> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<String>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    //=========== End of Getters and Setters =================================================================

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResidentDescriptor)) {
            return false;
        }

        // state check
        ResidentDescriptor e = (ResidentDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getRoom().equals(e.getRoom())
                && getGender().equals(e.getGender())
                && getHouse().equals(e.getHouse())
                && getMatricNumber().equals(e.getMatricNumber())
                && getTags().equals(e.getTags());
    }
}


