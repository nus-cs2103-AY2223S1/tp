package seedu.rc4hdb.model.resident;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.rc4hdb.commons.util.CollectionUtil;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;

/**
 * Stores the details to edit the resident with. Each non-empty field value will replace the
 * corresponding field value of the resident.
 */
public class ResidentDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Room room;
    private Gender gender;
    private House house;
    private MatricNumber matricNumber;
    private Set<Tag> tags;

    public ResidentDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public ResidentDescriptor(ResidentDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setRoom(toCopy.room);
        setGender(toCopy.gender);
        setHouse(toCopy.house);
        setMatricNumber(toCopy.matricNumber);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(name, phone, email, room, gender, house, matricNumber, tags);
    }

    //=========== Start of Getters and Setters ===============================================================

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Optional<Room> getRoom() {
        return Optional.ofNullable(room);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Optional<Gender> getGender() {
        return Optional.ofNullable(gender);
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Optional<House> getHouse() {
        return Optional.ofNullable(house);
    }

    public void setMatricNumber(MatricNumber matricNumber) {
        this.matricNumber = matricNumber;
    }

    public Optional<MatricNumber> getMatricNumber() {
        return Optional.ofNullable(matricNumber);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
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
