package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a mod in Mass Linkers.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModName(String)}
 */
public class Mod {
    public static final String MESSAGE_CONSTRAINTS = "Mod names should be numbers prefixed with letters.";
    /** Categories for mods */
    public enum ModCategory { CS, MATH, COMMS, GE, UE };
    public static final String VALIDATION_REGEX = "[A-Z]+\\d+[A-Z]?";
    private final String modName;
    private boolean hasTaken;
    private final ModCategory modCategory;

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
        this.modCategory = getModCategory(modName);
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
        this.modCategory = getModCategory(modName);
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

    /**
     * Gets the mod category.
     *
     * @return The mod category.
     */
    public ModCategory getModCategory() {
        return this.modCategory;
    }

    /**
     * Gets mod category from the given mod name.
     *
     * @param modName The mod name.
     * @return The mod category.
     */
    private ModCategory getModCategory(String modName) {
        String modPrefix = modName.split("[0-9]")[0].substring(0, 2);
        switch (modPrefix) {
        case "CS":
            return ModCategory.CS;
        case "MA":
        case "ST":
            return ModCategory.MATH;
        case "ES":
            return ModCategory.COMMS;
        case "GE":
        case "UT":
            return ModCategory.GE;
        default:
            return ModCategory.UE;
        }
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
