package seedu.address.model.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.profile.exceptions.ProfileNotFoundException;
import seedu.address.model.profile.exceptions.SimilarProfileException;

/**
 * A list of profiles that enforces uniqueness between its elements and does not allow nulls.
 * A profile is unique by comparing using {@code Profile#isSameEmail(Profile)}, {@code Profile#isSamePhone(Profile)}
 * and {@code Profile#isSameTelegram(Profile)}.
 * As such, adding and updating of profiles uses {@code Profile#isSameEmail(Profile)},
 * {@code Profile#isSamePhone(Profile)} and {@code Profile#isSameTelegram(Profile)} for equality so as to ensure
 * that the profile being added or updated is unique in terms of identity in the UniqueProfileList. However,
 * removal of a profile uses Profile#equals(Object) so as to ensure that the profile with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Profile#isSameEmail(Profile)
 * @see Profile#isSamePhone(Profile)
 * @see Profile#isSameTelegram(Profile)
 */
public class UniqueProfileList implements Iterable<Profile> {

    private final ObservableList<Profile> internalList = FXCollections.observableArrayList();
    private final ObservableList<Profile> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a profile with an equivalent email as the given argument.
     */
    public boolean containsEmail(Profile toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEmail);
    }

    /**
     * Returns true if the list contains a profile with an equivalent phone as the given argument.
     */
    public boolean containsPhone(Profile toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePhone);
    }

    /**
     * Returns true if the list contains a profile with an equivalent telegram as the given argument.
     */
    public boolean containsTelegram(Profile toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTelegram);
    }

    /**
     * Adds a profile to the list.
     * The profile must not already exist in the list. The email, phone and telegram must also not exist in the list.
     */
    public void add(Profile toAdd) {
        requireNonNull(toAdd);
        if (containsEmail(toAdd) || containsPhone(toAdd) || containsTelegram(toAdd)) {
            throw new SimilarProfileException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the profile {@code target} in the list with {@code editedProfile}.
     * {@code target} must exist in the list.
     * The profile email, phone and telegram of {@code editedProfile} must not be the same as another existing
     * profile in the list.
     */
    public void setProfile(Profile target, Profile editedProfile) {
        requireAllNonNull(target, editedProfile);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ProfileNotFoundException();
        }

        if ((!target.isSameEmail(editedProfile) && containsEmail(editedProfile))
                || (!target.isSamePhone(editedProfile) && containsPhone(editedProfile))
                || (!target.isSameTelegram(editedProfile) && containsTelegram(editedProfile))) {
            throw new SimilarProfileException();
        }

        internalList.set(index, editedProfile);
    }

    /**
     * Removes the equivalent profile from the list.
     * The profile must exist in the list.
     */
    public void remove(Profile toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ProfileNotFoundException();
        }
    }

    public void setProfiles(UniqueProfileList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code profiles}.
     * {@code profiles} must not contain similar profiles.
     */
    public void setProfiles(List<Profile> profiles) {
        requireAllNonNull(profiles);
        if (!profilesAreUnique(profiles)) {
            throw new SimilarProfileException();
        }

        internalList.setAll(profiles);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Profile> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Profile> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueProfileList // instanceof handles nulls
                        && internalList.equals(((UniqueProfileList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code profiles} contains only unique profiles.
     */
    private boolean profilesAreUnique(List<Profile> profiles) {
        for (int i = 0; i < profiles.size() - 1; i++) {
            Profile currentProfile = profiles.get(i);
            for (int j = i + 1; j < profiles.size(); j++) {
                Profile otherProfile = profiles.get(j);
                if (currentProfile.isSameEmail(otherProfile)
                        || currentProfile.isSamePhone(otherProfile)
                        || currentProfile.isSameTelegram(otherProfile)) {
                    return false;
                }

            }
        }
        return true;
    }
}
