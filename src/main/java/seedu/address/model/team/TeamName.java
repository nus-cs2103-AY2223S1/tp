package seedu.address.model.team;

        import seedu.address.model.common.Name;
        import seedu.address.model.task.TaskName;

        import static java.util.Objects.requireNonNull;
        import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team's name in the
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TeamName extends Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}()][\\p{Alnum}() ]*";

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TeamName(String name) {
        super(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
    }

    public static TeamName of(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        return new TeamName(name);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamName // instanceof handles nulls
                && fullName.equals(((TeamName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
