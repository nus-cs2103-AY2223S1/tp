package seedu.address.model.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Represents a Module in ProfNUS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    public static final String MESSAGE_MODULE_CODE_CONSTRAINT = "Each module of study has a unique module code "
            + "consisting of a two‐letter or three‐letter prefix that denotes the discipline, four digits the first of "
            + "which indicates the level of the module, and one or zero letter postfix.";
    private final ModuleName name;
    private final ModuleCode code;
    private final ModuleDescription description;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    private final List<Schedule> schedules;

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName name, ModuleCode code, ModuleDescription description, Set<Tag> tags) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.tags.addAll(tags);
        this.schedules = new ArrayList<>();
    }

    /**
     * Every field must be present and not null, used to edit a existing module.
     */
    public Module(ModuleName name, ModuleCode code, ModuleDescription description, Set<Tag> tags, List<Schedule> schedules) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.tags.addAll(tags);
        this.schedules = schedules;
    }

    public ModuleName getName() {
        return name;
    }

    public ModuleCode getCode() {
        return code;
    }

    public ModuleDescription getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getCode().equals(getCode());
    }

    /**
     * Checks if any of existing schedules conflicts with the new one
     * @param newSchedule new schedule to be added
     * @return true if conflicts; false otherwise
     */
    public boolean conflictAnySchedule(Schedule newSchedule) {
        return schedules.stream().anyMatch(newSchedule::isConflictWith);
    }

    /**
     * Adds a new schedule
     * @param newSchedule new schedule to be added
     */
    public void addSchedule(Schedule newSchedule) {
        schedules.add(newSchedule);
    }

    /**
     * Adds a list of new schedules
     * @param newSchedules new schedule list
     */
    public void addAllSchedules(List<Schedule> newSchedules) {
        schedules.addAll(newSchedules);
    }

    /**
     * Deletes the given schedule.
     * @param target the schedule to be deleted
     */
    public void deleteSchedule(Schedule target) {
        schedules.remove(target);
    }

    /**
     * clear all schedules of this module.
     */
    public void clearSchedules() {
        schedules.clear();
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getName().equals(getName())
                && otherModule.getCode().equals(getCode())
                && otherModule.getDescription().equals(getDescription())
                && otherModule.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, code, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Code: ")
                .append(getCode())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }



}
