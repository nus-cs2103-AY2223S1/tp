package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Module in ProfNUS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleName name;
    private final ModuleCode code;
    private final ModuleDescription description;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final ArrayList<Person> students = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName name, ModuleCode code, ModuleDescription description, Set<Tag> tags,
                  ArrayList<Person> students) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.tags.addAll(tags);
        this.students.addAll(students);
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

    public ArrayList<Person> getStudents() {
        return students;
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
