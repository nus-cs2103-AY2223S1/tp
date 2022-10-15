package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interest.Interest;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final GitHub gitHub;

    // Data fields
    private final Telegram handle;
    private final Set<Interest> tags = new HashSet<>();
    private final ObservableList<Mod> mods =
            FXCollections.observableArrayList();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Telegram handle,
                  GitHub gitHub, Set<Interest> tags, ObservableList<Mod> mods) {
        requireAllNonNull(name, handle);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.handle = handle;
        this.tags.addAll(tags);
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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Interest> getInterests() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable mods set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Mod> getMods() {
        return FXCollections.unmodifiableObservableList(mods);
    }

    /**
     * Appends a set of mods to the current mods linked to this batchmate.
     *
     * @param mods The set of mods to add in.
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
     * @param mods The set of mods to be edited.
     */
    public boolean canEditMods(ObservableList<Mod> mods) {
        return this.mods.containsAll(mods);
    }

    /**
     * Removes all mods in {@code mods} from the current set of mods linked to this batchmate.
     *
     * @param mods The set of mods to be deleted.
     */
    public void deleteMods(ObservableList<Mod> mods) {
        this.mods.removeAll(mods);
    }

    /**
     * Marks all mods in {@code mods} in the current set of mods linked to this batchmate as taken.
     *
     * @param mods The set of mods to be marked.
     */
    public void markMods(ObservableList<Mod> mods) {
        for (int i = 0; i < mods.size(); i++) {
            for (int j = 0; j < this.mods.size(); j++) {

                Mod currentMod = this.mods.get(j);
                String currentModName = currentMod.modName;
                String targetModName = mods.get(i).modName;

                if (currentModName.equals(targetModName)) {
                    currentMod.markMod();
                    break;
                }
            }
        }
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getInterests().equals(getInterests())
                && otherPerson.getMods().equals(getMods());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, handle, gitHub, tags, mods);
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
        if (!interestSet.isEmpty()) {
            builder.append("; Interests: ");
            interestSet.forEach(builder::append);
        }
        if (!mods.isEmpty()) {
            builder.append("; Mods: ");
            mods.forEach(builder::append);
        }
        return builder.toString();
    }

}
