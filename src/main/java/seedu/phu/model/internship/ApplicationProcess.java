package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

/**
 * Represents an internship's application process state in the list.
 */
public class ApplicationProcess extends ComparableModel {

    /**
     * Represents the possible state of application process of an internship.
     */
    enum ApplicationProcessState {
        APPLY, ASSESSMENT, INTERVIEW, OFFER, ACCEPTED, REJECTED;

        /**
         * Returns true if a given string is a valid application state.
         */
        public static boolean isInApplicationProcessState(String test) {
            return Arrays.stream(ApplicationProcessState.values()).anyMatch(stage -> stage.name().equals(test));
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Application process can take the following values: "
            + "APPLY, ASSESSMENT, INTERVIEW, OFFER, ACCEPTED, REJECTED";

    public final ApplicationProcessState value;

    /**
     * Constructs an {@code ApplicationProcess}.
     *
     * @param state A valid state of application process.
     */
    public ApplicationProcess(String state) {
        requireNonNull(state);
        value = ApplicationProcessState.valueOf(state.toUpperCase());
    }


    /**
     * Returns true if a given string is a valid application state.
     */
    public static boolean isValidApplicationProcess(String test) {
        String upperCasedTest = test.toUpperCase();
        return ApplicationProcessState.isInApplicationProcessState(upperCasedTest);
    }

    @Override
    public int compareTo(ComparableModel other) {
        if (other instanceof ApplicationProcess) {
            return this.toString().compareTo(((ApplicationProcess) other).toString());
        }
        return 0;
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationProcess // instanceof handles nulls
                && value.equals(((ApplicationProcess) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
