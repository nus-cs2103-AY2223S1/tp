package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class ApplicationProcess {
    enum Stage {
        APPLY, ASSESSMENT, INTERVIEW, OFFER, ACCEPTED, REJECTED;
    }
    public static final String MESSAGE_CONSTRAINTS = "Application process can take the following values: APPLY, ASSESSMENT, INTERVIEW, OFFER, ACCEPTED, REJECTED";

    public static final ApplicationProcess DEFAULT = new ApplicationProcess("apply");


    public final Stage value;

    public ApplicationProcess(String stage) {
        requireNonNull(stage);
        value = Stage.valueOf(stage.toUpperCase());
    }


    public static boolean isValidApplicationProcess(String test) {
        String upperCasedTest = test.toUpperCase();
        try {
            Stage.valueOf(upperCasedTest);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
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
