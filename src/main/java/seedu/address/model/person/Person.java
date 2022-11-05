package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Lesson;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Github github;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<CurrentModule> currModules = new HashSet<>();
    private final Set<PreviousModule> prevModules = new HashSet<>();
    private final Set<PlannedModule> planModules = new HashSet<>();
    private final Set<Lesson> lessons = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Github github, Set<Tag> tags,
                  Set<CurrentModule> currModules, Set<PreviousModule> prevModules, Set<PlannedModule> planModules) {
        requireAllNonNull(name, phone, email, address, github, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.github = github;
        this.tags.addAll(tags);
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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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

    /**
     * Returns the string representation of the person's module list.
     */
    public String getModuleList() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

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

    /**
     * Shifts all Current Modules {@code Set<CurrentModules>} into {@code Set<PreviousModules>}.
     */
    public void updatePrevMods() {
        Set<PreviousModule> temporary = new HashSet<>();
        currModules.stream().forEach(currMod -> temporary.add(new PreviousModule(currMod.moduleName)));
        prevModules.addAll(temporary);
        currModules.clear();
    }

    /**
     * Returns an immutable lesson set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Lesson> getLessons() {
        return Collections.unmodifiableSet(lessons);
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
     * Adds a lesson to a Person contact.
     *
     * @param lesson The lesson to be added to the Person.
     * @throws CommandException If lesson to be added overlaps with existing lessons of the contact.
     */
    public void addLesson(Lesson lesson) throws CommandException {
        LocalTime lessonStartTime = lesson.getStartTime();
        LocalTime lessonEndTime = lesson.getEndTime();
        for (Lesson existingLesson : lessons) {
            // base case : same day
            if (lesson.getDay() == existingLesson.getDay()) {
                LocalTime existingStartTime = existingLesson.getStartTime();
                LocalTime existingEndTime = existingLesson.getEndTime();
                int compareStartTime = lessonStartTime.compareTo(existingStartTime);
                int compareEndTime = lessonEndTime.compareTo(existingEndTime);
                int compareStartTimeToEndTime = lessonStartTime.compareTo(existingEndTime);
                int compareEndTimeToStartTime = lessonEndTime.compareTo(existingStartTime);
                // case 1 : start time is after or the same as existing end time - valid
                if (compareStartTimeToEndTime >= 0) {
                    continue;
                // case 2 : end time is before or the same as existing start time - valid
                } else if (compareEndTimeToStartTime <= 0) {
                    continue;
                // case 3 : start or end time is the same - invalid
                } else if ((compareStartTime == 0) || (compareEndTime == 0)) {
                    throw new CommandException(String.format("Lesson has same start/end time as %s", existingLesson));
                // case 4 : start time is later, end time is earlier - invalid
                } else if ((compareStartTime > 0) && (compareEndTime < 0)) {
                    throw new CommandException(String.format("Lesson timing overlaps with %s", existingLesson));
                // case 5 : start time is later, but earlier than existing end time - invalid
                } else if ((compareStartTime > 0) && (compareStartTimeToEndTime < 0)) {
                    throw new CommandException(String.format("Lesson timing overlaps with %s", existingLesson));
                // case 6 : end time is earlier, but later than existing start time - invalid
                } else if ((compareEndTime < 0) && (compareEndTimeToStartTime > 0)) {
                    throw new CommandException(String.format("Lesson timing overlaps with %s", existingLesson));
                }
            }
        }
        // no overlaps, add lesson to user
        lessons.add(lesson);
    }

    /**
     * Removes lesson from set of lessons
     *
     * @param lesson to be removed
     * @throws CommandException No lesson found in set in contact.
     */
    public void removeLesson(Lesson lesson) throws CommandException {
        boolean isRemoved = lessons.remove(lesson);

        if (!isRemoved) {
            throw new CommandException("No such lesson exists!");
        }
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
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGithub().equals(getGithub())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getCurrModules().equals(getCurrModules())
                && otherPerson.getPrevModules().equals(getPrevModules())
                && otherPerson.getPlanModules().equals(getPlanModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, github, tags, currModules, prevModules, planModules);
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

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
