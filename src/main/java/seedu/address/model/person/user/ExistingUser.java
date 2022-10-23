package seedu.address.model.person.user;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Lesson;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Represents the User of the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ExistingUser extends User {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Github github;
    private final Set<CurrentModule> currModules = new HashSet<>();
    private final Set<PreviousModule> prevModules = new HashSet<>();
    private final Set<PlannedModule> planModules = new HashSet<>();
    private final Set<Lesson> lessons = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public ExistingUser(Name name, Phone phone, Email email, Address address, Github github,
                        Set<CurrentModule> currModules, Set<PreviousModule> prevModules,
                        Set<PlannedModule> planModules) {
        requireAllNonNull(name, phone, email, address, github);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.github = github;
        this.currModules.addAll(currModules);
        this.prevModules.addAll(prevModules);
        this.planModules.addAll(planModules);
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

    public Address getAddress() {
        return address;
    }

    public Github getGithub() {
        return github;
    }

    /**
     * Returns an immutable current module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<CurrentModule> getCurrModules() {
        return Collections.unmodifiableSet(currModules);
    }

    /**
     * Returns an immutable previous module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<PreviousModule> getPrevModules() {
        return Collections.unmodifiableSet(prevModules);
    }

    /**
     * Returns an immutable planned module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<PlannedModule> getPlanModules() {
        return Collections.unmodifiableSet(planModules);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public Set<Lesson> getLessons() {
        return Collections.unmodifiableSet(lessons);
    }

    /**
     * Removes lesson from set of lessons.
     *
     * @param lesson to be removed.
     * @throws CommandException No such lesson.
     */
    public void removeLesson(Lesson lesson) throws CommandException {
        boolean isRemoved = lessons.remove(lesson);

        if (!isRemoved) {
            throw new CommandException("No such lesson exists!");
        }
    }

    /**
     * Returns true if both users have the same identity and data fields.
     * This defines a stronger notion of equality between two users.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ExistingUser)) {
            return false;
        }

        ExistingUser otherUser = (ExistingUser) other;
        return otherUser.getName().equals(getName())
                && otherUser.getPhone().equals(getPhone())
                && otherUser.getEmail().equals(getEmail())
                && otherUser.getAddress().equals(getAddress())
                && otherUser.getGithub().equals(getGithub())
                && otherUser.getCurrModules().equals(getCurrModules())
                && otherUser.getPrevModules().equals(getPrevModules())
                && otherUser.getPlanModules().equals(getPlanModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, github);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Github: ")
                .append(getGithub());

        Set<CurrentModule> currModules = getCurrModules();
        if (!currModules.isEmpty()) {
            builder.append("; Current Modules: ");
            currModules.forEach(builder::append);
        }

        Set<PreviousModule> prevModules = getPrevModules();
        if (!prevModules.isEmpty()) {
            builder.append("; Previous Modules: ");
            prevModules.forEach(builder::append);
        }

        Set<PlannedModule> planModules = getPlanModules();
        if (!currModules.isEmpty()) {
            builder.append("; Planned Modules: ");
            planModules.forEach(builder::append);
        }

        return builder.toString();
    }

}
