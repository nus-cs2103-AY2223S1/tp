package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Professor/Teaching Assistant's module code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode implements Comparable<ModuleCode> {

    public static final String MESSAGE_CONSTRAINTS = "Module code must fulfill the following requirements:\n"
            + "- length between 5 and 7 characters\n"
            + "- begin with either 2 or 3 letters (case insensitive)\n"
            + "- followed by 4 digits\n"
            + "- optional of 1 letter behind the 4 digits\n"
            + "example, ABC1234S";

    /*
     * The first character of the module code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(?=\\S{5,7}$)([a-zA-Z]{2,3})(\\d{4})([a-zA-Z]{0,1})$";

    public final String value;

    /**
     * Constructs an {@code ModuleCode}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        value = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return '[' + value + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && value.equals(((ModuleCode) other).value)); // state check
    }

    /**
     * Returns true if a given string is a valid moduleCode name.
     */
    public static boolean isValidModuleCodeName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public int getLevel() {
        return Integer.parseInt(value.replaceAll("\\D", ""));
    }
    public String getPrefix() {
        return value.replaceAll("\\d", "").toUpperCase();
    }
    /**
     * Used as a comparator between two modules. The module code with a higher level higher precedence, tie
     * breaker is implemented by comparing the prefix of moduleCode
     * @param moduleCode target of comparison
     * @return int
     */
    @Override
    public int compareTo(ModuleCode moduleCode) {
        return this.getLevel() - moduleCode.getLevel() > 0
                ? 1
                : this.getLevel() == moduleCode.getLevel()
                ? getPrefix().compareTo(moduleCode.getPrefix())
                : -1;
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
