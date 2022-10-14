package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a mod in Mass Linkers.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModName(String)}
 */
public class Mod {
    public static final String MESSAGE_CONSTRAINTS = "Mod names should not contain any special characters.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String modName;
    private boolean hasTaken;

    /**
     * Constructs a {@code Mod}.
     *
     * @param modName A valid mod name.
     */
    public Mod(String modName) {
        requireNonNull(modName);
        checkArgument(isValidModName(modName), MESSAGE_CONSTRAINTS);
        this.modName = modName.toUpperCase();
        this.hasTaken = false;
    }

    /**
     * Constructs a {@code Mod} with name and hasTaken status.
     *
     * @param modName A valid mod name.
     * @param hasTaken The mod status, i.e. whether it has been taken.
     */
    public Mod(String modName, boolean hasTaken) {
        requireNonNull(modName);
        checkArgument(isValidModName(modName), MESSAGE_CONSTRAINTS);
        this.modName = modName.toUpperCase();
        this.hasTaken = hasTaken;
    }

    /**
     * Returns true if a given string is a valid mod name.
     */
    public static boolean isValidModName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both mods have the same name.
     * This defines a weaker notion of equality between two mods.
     */
    public boolean isSameMod(Mod otherMod) {
        if (otherMod == this) {
            return true;
        }

        return otherMod != null
                && otherMod.modName.equals((this.modName));
    }

    /**
     * Marks a module as taken.
     */
    public void markMod() {
        this.hasTaken = true;
    }

    /**
     * Unmarks a module as not taken.
     */
    public void unmarkMod() {
        this.hasTaken = false;
    }

    /**
     * Gets the module name.
     *
     * @return String representation of module name.
     */
    public String getModName() {
        return this.modName;
    }

    /**
     * Gets the status of the module.
     *
     * @return True if the batchmate has taken the mod; false otherwise.
     */
    public boolean getModStatus() {
        return this.hasTaken;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mod // instanceof handles nulls
                && modName.equals(((Mod) other).modName)); // state check
    }

    @Override
    public int hashCode() {
        return modName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + modName + ": " + hasTaken + ']';
    }
}
