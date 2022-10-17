package seedu.address.model.person;

import java.util.UUID;
/**
 * Uid that uniquely defines a person.
 * Uid is an unmodifiable field that is created every time with the creation of a new Person.
 */
public class Uid {
    private final String uid;

    public Uid() {
        this.uid = String.valueOf(UUID.randomUUID());
    }
    public Uid(String uid) {
        this.uid = uid;
    }
    public String getUid() {
        return uid;
    }
    /**
     * Returns true if both uids have the same name. This method should always return false value.
     */
    public boolean isSameUid(Uid otherUid) {
        if (otherUid == this) {
            return true;
        }
        return otherUid != null && otherUid.getUid().equals(getUid());
    }
}
