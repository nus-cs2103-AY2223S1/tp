package seedu.masslinkers.model.student;

import static seedu.masslinkers.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.util.ModComparator;

/**
 * Represents a Student in the mass linkers.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final GitHub gitHub;

    // Data fields
    private final Telegram handle;
    private final Set<Interest> interests = new HashSet<>();
    private final ObservableList<Mod> mods =
            FXCollections.observableArrayList();
    private boolean hasIncorrectPhone = false;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Telegram handle,
                  GitHub gitHub, Set<Interest> interests, ObservableList<Mod> mods) {
        requireAllNonNull(name, handle);
        this.name = name;
        this.phone = phone;
        this.hasIncorrectPhone = phone != null && phone.hasIncorrectNumber();
        this.email = email;
        this.handle = handle;
        this.interests.addAll(interests);
        this.gitHub = gitHub;
        this.mods.addAll(mods);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return handle;
    }

    public GitHub getGitHub() {
        return gitHub;
    }

    /**
     * Returns true if student has an incorrect phone number (but still valid)
     */
    public boolean hasIncorrectPhone() {
        return this.hasIncorrectPhone;
    }

    /**
     * Returns an immutable interest set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Interest> getInterests() {
        return Collections.unmodifiableSet(interests);
    }

    /**
     * Appends a list of interests to the current interests linked to this batchmate.
     *
     * @param interestsToBeAdded The list of interests to add in.
     */
    public void addInterests(Set<Interest> interestsToBeAdded) {
        this.interests.addAll(interestsToBeAdded);
    }

    /**
     * Checks if the all interests provided are found in the set of interests linked to this batchmate.
     *
     * @param interestsSet The set of interests to be deleted.
     */
    public boolean canDeleteInterests(Set<Interest> interestsSet) {
        return this.interests.containsAll(interestsSet);
    }

    /**
     * Removes all interests in {@code interestsSet} from the current list of interests linked to this batchmate.
     *
     * @param interestsSet The set of interests to be deleted.
     */
    public void deleteInterests(Set<Interest> interestsSet) {
        this.interests.removeAll(interestsSet);
    }

    /**
     * Sorts then returns an immutable mods list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Mod> getMods() {
        return FXCollections.unmodifiableObservableList(new SortedList<>(mods, new ModComparator()));
    }

    /**
     * Appends a list of mods to the current mods linked to this batchmate.
     *
     * @param mods The list of mods to add in.
     */
    public void addMods(ObservableList<Mod> mods) {
        Set<Mod> uniqueModsSet = mods
                .stream()
                .filter(mod -> !this.mods.contains(mod))
                .collect(Collectors.toSet());

        this.mods.addAll(uniqueModsSet);
    }

    /**
     * Checks if the all mods provided can be found and edited in the set of mods linked to this batchmate.
     *
     * @param mods The list of mods to be edited.
     */
    public boolean canEditMods(ObservableList<Mod> mods) {
        return this.mods.containsAll(mods);
    }

    /**
     * Removes all mods in {@code mods} from the current list of mods linked to this batchmate.
     *
     * @param mods The list of mods to be deleted.
     */
    public void deleteMods(ObservableList<Mod> mods) {
        this.mods.removeAll(mods);
    }

    /**
     * Marks all mods in {@code mods} in the current list of mods linked to this batchmate as taken.
     *
     * @param mods The list of mods to be marked.
     */
    public void markMods(ObservableList<Mod> mods) {
        for (int i = 0; i < mods.size(); i++) {
            for (int j = 0; j < this.mods.size(); j++) {

                Mod currentMod = this.mods.get(j);
                String currentModName = currentMod.getModName();
                String targetModName = mods.get(i).getModName();

                if (currentModName.equals(targetModName)) {
                    currentMod.markMod();
                    break;
                }
            }
        }
    }

    /**
     * Marks all mods of a batchmate as taken.
     */
    public void markAllMods() {
        for (Mod mod : this.mods) {
            mod.markMod();
        }
    }

    /**
     * Unmarks all mods in {@code mods} in the current list of mods linked to this batchmate as not taken.
     *
     * @param mods The list of mods to be unmarked.
     */
    public void unmarkMods(ObservableList<Mod> mods) {
        for (int i = 0; i < mods.size(); i++) {
            for (int j = 0; j < this.mods.size(); j++) {

                Mod currentMod = this.mods.get(j);
                String currentModName = currentMod.getModName();
                String targetModName = mods.get(i).getModName();

                if (currentModName.equals(targetModName)) {
                    currentMod.unmarkMod();
                    break;
                }
            }
        }
    }

    /**
     * Returns true if both students have the same Telegram handle/GitHub username/email/phone.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }
        if (otherStudent == null) {
            return false;
        }

        return (otherStudent.getTelegram() != null && otherStudent.getTelegram().equals(getTelegram()))
                || (otherStudent.getEmail() != null && otherStudent.getEmail().equals(getEmail()))
                || (otherStudent.getGitHub() != null && otherStudent.getGitHub().equals(getGitHub()))
                || (otherStudent.getPhone() != null && otherStudent.getPhone().equals(getPhone()));
    }
    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
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
                && Optional.ofNullable(otherStudent.getPhone()).equals(Optional.ofNullable(getPhone()))
                && Optional.ofNullable(otherStudent.getGitHub()).equals(Optional.ofNullable(getGitHub()))
                && Optional.ofNullable(otherStudent.getEmail()).equals(Optional.ofNullable(getEmail()))
                && otherStudent.getTelegram().equals(getTelegram())
                && otherStudent.getInterests().equals(getInterests())
                && otherStudent.getMods().equals(getMods());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, handle, gitHub, interests, getMods());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram: ")
                .append(getTelegram())
                .append("; GitHub: ")
                .append(getGitHub())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        Set<Interest> interestSet = getInterests();
        ObservableList<Mod> sortedMods = getMods();
        if (!interestSet.isEmpty()) {
            builder.append("; Interests: ");
            interestSet.forEach(builder::append);
        }
        if (!sortedMods.isEmpty()) {
            builder.append("; Mods: ");
            sortedMods.forEach(builder::append);
        }
        return builder.toString();
    }

}
