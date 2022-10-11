package seedu.address.model.module;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SubjectModule {

    private final ModuleName modName;
    private final boolean isTaking;

    /**
     * Every field must be present and not null.
     */
    public SubjectModule(ModuleName modName, boolean isTaking) {
        this.modName = modName;
        this.isTaking = isTaking;
    }

    public ModuleName getModName() {
        return modName;
    }

    public boolean getTakingStatus() {
        return isTaking;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameModule(SubjectModule otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModName().equals(getModName());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SubjectModule)) {
            return false;
        }

        SubjectModule otherModule = (SubjectModule) other;
        return otherModule.getModName().equals(getModName())
                && otherModule.getModName().equals(getTakingStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(modName, isTaking);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModName())
                .append("; Status: ")
                .append(getTakingStatus());
        return builder.toString();
    }
}
