package seedu.rc4hdb.model.resident;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.rc4hdb.commons.util.CollectionUtil;

/**
 * Stores the details to filter the resident with. Each non-empty field value will replace the
 * corresponding field value of the resident.
 */
public class ResidentStringDescriptor {
    private String name;
    private String phone;
    private String email;
    private String room;
    private String gender;
    private String house;
    private String matricNumber;
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
        this.name = name;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Optional<String> getRoom() {
        return Optional.ofNullable(room);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Optional<String> getGender() {
        return Optional.ofNullable(gender);
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Optional<String> getHouse() {
        return Optional.ofNullable(house);
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public Optional<String> getMatricNumber() {
        return Optional.ofNullable(matricNumber);
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
        if (!(other instanceof ResidentStringDescriptor)) {
            return false;
        }

        // state check
        ResidentStringDescriptor e = (ResidentStringDescriptor) other;

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


