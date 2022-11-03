package seedu.masslinkers.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.util.AppUtil.checkArgument;

import seedu.masslinkers.logic.parser.ParserUtil;

/**
 * Represents a mod in Mass Linkers.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModName(String)}
 */
public class Mod {
    public static final String MESSAGE_CONSTRAINTS =
            "Mod names should be numbers prefixed with alphabet(s) and less than 10 characters.";

    //@@author jonasgwt
    /** Categories for mods */
    public enum ModCategory {
        COMP("Computer Science"), MATH("Mathematics"), SCI("Science"), COMMS("Communication"),
        GE("General Education"), UE("Unrestricted Elective");
        private String value;

        ModCategory(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

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
        this.modCategory = ParserUtil.parseModsToCategory(modName);
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
        this.modCategory = ParserUtil.parseModsToCategory(modName);
    }

    /**
     * Returns true if a given string is a valid mod name.
     */
    public static boolean isValidModName(String test) {
        return test.length() < 10 && test.matches(VALIDATION_REGEX);
    }

    //@@author jonasgwt
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
     * Unmarks a module and updates the status as taking.
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
