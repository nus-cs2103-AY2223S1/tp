package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.link.Link;
import seedu.address.model.task.Task;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleCode moduleCode;

    // Data fields
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Link> links = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleCode moduleCode, Set<Task> tasks, Set<Link> links) {
        requireAllNonNull(moduleCode, tasks, links);
        this.moduleCode = moduleCode;
        this.tasks.addAll(tasks);
        this.links.addAll(links);
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns an immutable links set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Link> getLinks() {
        return Collections.unmodifiableSet(links);
    }

    /**
     * Returns an immutable task set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    /**
     * Returns true if both modules have the same moduleCode.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode());
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
        return otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getLinks().equals(getLinks())
                && otherModule.getTasks().equals(getTasks());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, links, tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode());

        Set<Link> links = getLinks();
        if (!links.isEmpty()) {
            builder.append("; Links: ");
            links.forEach(builder::append);
        }

        Set<Task> tasks = getTasks();
        if (!tasks.isEmpty()) {
            builder.append("; Tasks: ");
            tasks.forEach(builder::append);
        }

        return builder.toString();
    }

}

