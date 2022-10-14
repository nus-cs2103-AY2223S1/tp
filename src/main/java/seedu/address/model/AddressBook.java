package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.UniqueProfileList;

/**
 * Wraps all data at the address-book level
 * Similar Profiles are not allowed (by .isSameName and .isSameEmail comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueProfileList profiles;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        profiles = new UniqueProfileList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Profiles in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the profile list with {@code profiles}.
     * {@code profiles} must not contain similar profiles.
     */
    public void setProfiles(List<Profile> profiles) {
        this.profiles.setProfiles(profiles);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setProfiles(newData.getProfileList());
    }

    //// profile-level operations

    /**
     * Returns true if a profile with the same name as {@code profile} exists in the address book.
     */
    public boolean hasName(Profile profile) {
        requireNonNull(profile);
        return profiles.containsName(profile);
    }

    /**
     * Returns true if a profile with the same email as {@code profile} exists in the address book.
     */
    public boolean hasEmail(Profile profile) {
        requireNonNull(profile);
        return profiles.containsEmail(profile);
    }

    /**
     * Adds a profile to the address book.
     * The profile must not already exist in the address book.
     */
    public void addProfile(Profile p) {
        profiles.add(p);
    }

    /**
     * Replaces the given profile {@code target} in the list with {@code editedProfile}.
     * {@code target} must exist in the address book.
     * The profile identity of {@code editedProfile} must not be the same as an existing profile in the address book.
     */
    public void setProfile(Profile target, Profile editedProfile) {
        requireNonNull(editedProfile);

        profiles.setProfile(target, editedProfile);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeProfile(Profile key) {
        profiles.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return profiles.asUnmodifiableObservableList().size() + " profiles";
        // TODO: refine later
    }

    @Override
    public ObservableList<Profile> getProfileList() {
        return profiles.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && profiles.equals(((AddressBook) other).profiles));
    }

    @Override
    public int hashCode() {
        return profiles.hashCode();
    }
}
