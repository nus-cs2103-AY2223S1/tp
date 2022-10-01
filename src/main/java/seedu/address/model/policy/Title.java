package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Title {

        public static final String MESSAGE_CONSTRAINTS =
                "Names should only contain alphanumeric characters and spaces, and it should not be blank";

        /*
         * The first character of the address must not be a whitespace,
         * otherwise " " (a blank string) becomes a valid input.
         */
        public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

        public final String fullName;

        /**
         * Constructs a {@code Name}.
         *
         * @param title A valid name.
         */
        public Title(String title) {
            requireNonNull(title);
            checkArgument(isValidName(title), MESSAGE_CONSTRAINTS);
            fullName = title;
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
                    || (other instanceof seedu.address.model.person.Name // instanceof handles nulls
                    && fullName.equals(((seedu.address.model.person.Name) other).fullName)); // state check
        }

        @Override
        public int hashCode() {
            return fullName.hashCode();
        }

    }

}
